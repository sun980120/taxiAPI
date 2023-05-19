package com.example.taxi.service.userInformation.service;

import com.example.taxi.entity.UserInformation;
import com.example.taxi.repository.UserInformationRepository;
import com.example.taxi.service.userInformation.dto.UserInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {
    private final UserInformationRepository userInformationRepository;
    @Override
    public List<UserInformation> findAll() {
        try {
            return userInformationRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserInformation findById(Long id) {
        try {
            return userInformationRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String saveUserInformation(UserInformation userInformation) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            userInformation.setUserPassword(passwordEncoder.encode(userInformation.getUserPassword()));
            userInformationRepository.save(userInformation);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @Override
    public String updateUserInformation(UserInformation userInformation) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            UserInformation result = userInformationRepository.findById(userInformation.getId()).get();
            Optional.ofNullable(userInformation)
                            .ifPresent(info -> {
                                Optional.ofNullable(info.getLanguage()).ifPresent(result::setLanguage);
                                Optional.ofNullable(info.getPhone()).ifPresent(result::setPhone);
                                Optional.ofNullable(info.getNickname()).ifPresent(result::setNickname);
                                Optional.ofNullable(info.getUserPassword()).map(password -> passwordEncoder.encode(password)).ifPresent(result::setUserPassword);
                                Optional.ofNullable(info.getUserPaymentList()).ifPresent(result::setUserPaymentList);
                                Optional.ofNullable(info.getDrivingRecordList()).ifPresent(result::setDrivingRecordList);
                                Optional.ofNullable(info.getTaxiReserveList()).ifPresent(result::setTaxiReserveList);
                            });
            userInformationRepository.save(result);
            return "변경완료";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            if(e.getMessage().equals("No value present"))
                return "계정정보 없음";
            return "fail";
        }
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
    public String login(UserInformationDto userInformationDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            UserInformation userInformation = userInformationRepository.findByUserEmail(userInformationDto.getUserEmail());
            if (!passwordEncoder.matches(userInformationDto.getUserPassword(), userInformation.getUserPassword())) {
                throw new UsernameNotFoundException("Invalid password");
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}