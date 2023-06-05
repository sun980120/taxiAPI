package com.example.taxi.service.userInformation.service;

import com.example.taxi.entity.UserInformation;
import com.example.taxi.service.common.dto.ReturnJSONUtilDto;
import com.example.taxi.service.userInformation.dto.InsertUserInformationDto;
import com.example.taxi.service.userInformation.dto.UpdateUserInformationDto;
import com.example.taxi.service.userInformation.dto.UserInformationDto;

import java.util.List;

public interface UserInformationService {
    List<UserInformationDto> findAll();

    UserInformationDto findById(Long id);

    ReturnJSONUtilDto saveUserInformation(InsertUserInformationDto insertUserInformationDto);

    ReturnJSONUtilDto updateUserInformation(UpdateUserInformationDto updateUserInformationDto, int type);

    String deleteUserInformation(UserInformation userInformation);

    ReturnJSONUtilDto login(UserInformationDto userInformationDto);
}
