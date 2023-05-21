package com.example.taxi.service.userInformation.dto;

import com.example.taxi.entity.UserInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationDto {
    private Long id;
    private String userEmail;
    private String phone;
    private String nickname;
    private String language;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
    private String userPassword;

    public UserInformationDto EntityToDto(UserInformation userInformation) {
        UserInformationDto dto = new UserInformationDto();
        dto.id = userInformation.getId();
        dto.userEmail = userInformation.getUserEmail();
        dto.phone = userInformation.getPhone();
        dto.nickname = userInformation.getNickname();
        dto.language = userInformation.getLanguage();
        dto.registerDateTime = userInformation.getRegisterDateTime();
        dto.updateDateTime = userInformation.getUpdateDateTime();
        return dto;
    }
}
