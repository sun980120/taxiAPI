package com.example.taxi.service.userInformation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationDto {
    private String userEmail;
    private String userPassword;
}
