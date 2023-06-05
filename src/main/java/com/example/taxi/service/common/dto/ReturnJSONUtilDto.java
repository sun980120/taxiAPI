package com.example.taxi.service.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnJSONUtilDto {
    private Boolean response;
    private ReturnDataDto result;
    private String accessToken;
    private String message;
}
