package com.konasl.scoreupdate.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiResponse {
    private String message;
    private Integer statusCode;
    private Object object;

    public ApiResponse(String message, Integer statusCode, Object object) {
        this.message = message;
        this.statusCode = statusCode;
        this.object = object;
    }

    public ApiResponse(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
