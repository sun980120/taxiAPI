package com.example.taxi.service.userInformation;

import com.example.taxi.entity.UserInformation;
import com.example.taxi.service.userInformation.dto.UserInformationDto;
import com.example.taxi.service.userInformation.service.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserInformationController {
    private final UserInformationService userInformationService;

    @SchemaMapping(typeName = "Query", value = "findAll")
    public List<UserInformation> findAll() {
        return userInformationService.findAll();
    }

    @SchemaMapping(typeName = "Query", value = "findById")
    public UserInformation findById(@Argument Long id) {
        return userInformationService.findById(id);
    }

    @SchemaMapping(typeName = "Query", value = "login")
    public String login(@Arguments UserInformationDto userInformationDto) {
        return userInformationService.login(userInformationDto);
    }

    @SchemaMapping(typeName = "Mutation", value = "saveUserInformation")
    public String saveUserInformation(@Argument UserInformation userInformation) {
        System.out.println(userInformation.getUserEmail());
        System.out.println(userInformation.getUserPassword());
        System.out.println(userInformation.getLanguage());
        return userInformationService.saveUserInformation(userInformation);
    }

    @SchemaMapping(typeName = "Mutation", value = "updateUserInformation")
    public String updateUserInformation(@Argument UserInformation userInformation) {
        return userInformationService.updateUserInformation(userInformation);
    }

    @SchemaMapping(typeName = "Mutation", value = "deleteUserInformation")
    public String deleteUserInformation(@Argument UserInformation userInformation) {
        return userInformationService.deleteUserInformation(userInformation);
    }
}
