package com.mycv.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResponseType {

    USER_NOT_FOUND(1000, "user not found", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(1001, "internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    OPERATION_SUCCESS(1002, "success", HttpStatus.OK),
    JWT_ERROR(1003, "access token error", HttpStatus.FORBIDDEN),
    ACCESS_DENIED(1004, "access denied", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS(1005, "username or password does not match", HttpStatus.FORBIDDEN),
    REQUEST_VALIDATION_ERROR(1006, "errors in request body", HttpStatus.BAD_REQUEST),
    INVALID_OPERATION(1007, "invalid operation", HttpStatus.BAD_REQUEST),
    INACTIVE_USER(1008, "user is inactive", HttpStatus.FORBIDDEN),
    CV_NOT_FOUND(1009, "cv not found", HttpStatus.BAD_REQUEST),
    NO_ACCESS_TO_RESOURCE(1010, "use has no access to the resource", HttpStatus.BAD_REQUEST)
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

}
