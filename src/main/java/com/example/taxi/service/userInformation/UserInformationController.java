package com.example.taxi.service.userInformation;

import com.example.taxi.service.common.dto.ReturnJSONUtilDto;
import com.example.taxi.service.userInformation.dto.InsertUserInformationDto;
import com.example.taxi.service.userInformation.dto.UpdateUserInformationDto;
import com.example.taxi.service.userInformation.service.UserInformationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "회원관리")
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserInformationController {

    private final ObjectMapper objectMapper;

    private final UserInformationService userInformationService;

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUserInformation(@RequestBody InsertUserInformationDto insertUserInformationDto) {
        ReturnJSONUtilDto result = userInformationService.saveUserInformation(insertUserInformationDto);
        try {
            String json = objectMapper.writeValueAsString(result);
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/update/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUserInformation(@PathVariable int type, @RequestBody UpdateUserInformationDto updateUserInformationDto) {
        ReturnJSONUtilDto result = userInformationService.updateUserInformation(updateUserInformationDto, type);
        try {
            String json = objectMapper.writeValueAsString(result);
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
