package com.mycv.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private ResponseType responseType;
    private String msg;
}
