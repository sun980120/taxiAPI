package com.example.taxi.service.userInformation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInformationDto {
    private Long id;
    private String userEmail;
    private String phone;
    private String nickname;
    private String language;
    private String userPassword;
}
