package com.example.taxi.service.userInformation.service;

import com.example.taxi.entity.UserInformation;
import com.example.taxi.repository.UserInformationRepository;
import com.example.taxi.service.common.EmailMaskingUtil;
import com.example.taxi.service.common.dto.ReturnDataDto;
import com.example.taxi.service.common.dto.ReturnJSONUtilDto;
import com.example.taxi.service.userInformation.dto.InsertUserInformationDto;
import com.example.taxi.service.userInformation.dto.UpdateUserInformationDto;
import com.example.taxi.service.userInformation.dto.UserInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {
    private final UserInformationRepository userInformationRepository;
//    private final EmailMaskingUtil emailMaskingUtil;
    @Override
    public List<UserInformationDto> findAll() {
        try {
            List<UserInformation> userInformationList = userInformationRepository.findAll();
            List<UserInformationDto> userInformationDtoList = new ArrayList<>();
            for (UserInformation i : userInformationList
            ) {
                UserInformationDto userInformationDto = new UserInformationDto().EntityToDto(i);
                System.out.println(i.getUserEmail());
                String maskedUserEmail = EmailMaskingUtil.maskUserEmail(i.getUserEmail());
                System.out.println(maskedUserEmail);
                userInformationDto.setUserEmail(maskedUserEmail);
                userInformationDtoList.add(userInformationDto);
            }
            return userInformationDtoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserInformationDto findById(Long id) {
        try {
            UserInformation userInformation = userInformationRepository.findById(id).get();
            UserInformationDto userInformationDto = new UserInformationDto().EntityToDto(userInformation);
            String maskedUserEmail = EmailMaskingUtil.maskUserEmail(userInformation.getUserEmail());
            userInformationDto.setUserEmail(maskedUserEmail);
            return userInformationDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReturnJSONUtilDto saveUserInformation(InsertUserInformationDto insertUserInformationDto) {
        ReturnJSONUtilDto result = new ReturnJSONUtilDto();
        ReturnDataDto returnDataDto = new ReturnDataDto();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            System.out.println(insertUserInformationDto);
            UserInformation userInformation = UserInformation.builder()
                    .userEmail(insertUserInformationDto.getUserEmail())
                    .userPassword(passwordEncoder.encode(insertUserInformationDto.getUserPassword()))
                    .language(insertUserInformationDto.getLanguage())
                    .phone(insertUserInformationDto.getPhone())
                    .nickname(insertUserInformationDto.getNickname())
                    .build();
            userInformationRepository.save(userInformation);
            returnDataDto.setUserInformation(userInformation);
            result.setResponse(true);
            result.setResult(returnDataDto);
            result.setAccessToken("123123");
            result.setMessage("Success");
        } catch (Exception e) {
            result.setResponse(false);
            result.setResult(returnDataDto);
            result.setAccessToken("123123");
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ReturnJSONUtilDto updateUserInformation(UpdateUserInformationDto updateUserInformationDto, int type) {
        ReturnJSONUtilDto result = new ReturnJSONUtilDto();
        ReturnDataDto returnDataDto = new ReturnDataDto();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            UserInformation userInformation = userInformationRepository.findById(updateUserInformationDto.getId()).get();
            Optional.ofNullable(updateUserInformationDto)
                            .ifPresent(info -> {
                                Optional.ofNullable(info.getLanguage()).ifPresent(userInformation::setLanguage);
                                Optional.ofNullable(info.getPhone()).ifPresent(userInformation::setPhone);
                                Optional.ofNullable(info.getNickname()).ifPresent(userInformation::setNickname);
                                Optional.ofNullable(info.getUserPassword()).map(password -> passwordEncoder.encode(password)).ifPresent(userInformation::setUserPassword);
                            });

            userInformationRepository.save(userInformation);

            returnDataDto.setUserInformation(userInformation);
            result.setResponse(true);
            result.setResult(returnDataDto);
            result.setAccessToken("123123");
            result.setMessage(type == 1 ? "프로필 변경 성공" : "비밀번호 변경 성공");
        } catch (Exception e) {
            e.printStackTrace();
            result.setResponse(false);
            result.setAccessToken("123123");
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public String deleteUserInformation(UserInformation userInformation) {
        try {
            userInformationRepository.delete(userInformation);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public ReturnJSONUtilDto login(UserInformationDto userInformationDto) {
        ReturnJSONUtilDto result = new ReturnJSONUtilDto();
        ReturnDataDto returnDataDto = new ReturnDataDto();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            UserInformation userInformation = userInformationRepository.findByUserEmail(userInformationDto.getUserEmail());
            if (!passwordEncoder.matches(userInformationDto.getUserPassword(), userInformation.getUserPassword())) {
                throw new UsernameNotFoundException("Invalid password");
            }

            returnDataDto.setUserInformation(userInformation);
            result.setResult(returnDataDto);
            result.setAccessToken("123123");
            result.setResponse(true);
            result.setMessage("로그인 성공");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setResult(returnDataDto);
            result.setAccessToken("123123");
            result.setResponse(true);
            e.printStackTrace();
        }
        return result;
    }
}
