package com.example.taxi.service.userInformation.service;

import com.example.taxi.entity.UserInformation;
import com.example.taxi.service.userInformation.dto.UserInformationDto;

import java.util.List;

public interface UserInformationService {
    List<UserInformationDto> findAll();

    UserInformationDto findById(Long id);

    String saveUserInformation(UserInformation userInformation);

    String updateUserInformation(UserInformation userInformation);

    String deleteUserInformation(UserInformation userInformation);

    String login(UserInformationDto userInformationDto);
}
