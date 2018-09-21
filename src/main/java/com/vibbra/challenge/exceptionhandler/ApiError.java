package com.vibbra.challenge.exceptionhandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "API error handled by the application")
public class ApiError {

    @ApiModelProperty(value = "The error message for the operation requested.")
    private String message;

    public ApiError() {
        // serialization
    }

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
