package com.mycv.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseType {

    USER_NOT_FOUND(1000, "USER_NOT_FOUND", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(1001, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    OPERATION_SUCCESS(1002, "OPERATIO_SUCCESS", HttpStatus.OK),
    JWT_ERROR(1003, "jwt error", HttpStatus.FORBIDDEN),
    ACCESS_DENIED(1004, "ACCESS_DENIED", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS(1005, "BAD_CREDENTIALS", HttpStatus.FORBIDDEN),
    REQUEST_VALIDATION_ERROR(1006, "REQUEST_VALIDATION_ERROR", HttpStatus.BAD_REQUEST),
    INVALID_OPERATION(1007, "INVALID_OPERATION", HttpStatus.BAD_REQUEST),
    INACTIVE_USER(1008, "INACTIVE_USER", HttpStatus.FORBIDDEN),
    CV_NOT_FOUND(1009, "CV_NOT_FOUND", HttpStatus.BAD_REQUEST)
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

}
