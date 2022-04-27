package com.mycv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycv.exception.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Response<T> {

    private int resCode;
    private int status;
    private T data;
    private String error;

    public Response(int resCode, int status, T data, String error) {
        this.resCode = resCode;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> SuccessResponseBuilder success(T data) {
        return new SuccessResponseBuilder(data);
    }

    public static ErrorResponseBuilder error(String error) {
        return new ErrorResponseBuilder(error);
    }

    public static class SuccessResponseBuilder<T> {
        private T data;
        public SuccessResponseBuilder(T data) {
            this.data = data;
        }

        public Response build(ResponseType responseType) {
            return new Response(
                    responseType.getCode(),
                    responseType.getHttpStatus().value(),
                    data,
                    null
            );
        }
    }

    public static class ErrorResponseBuilder {
        private String error;
        public ErrorResponseBuilder(String error) {
            this.error = error;
        }

        public Response build(ResponseType responseType) {
            return new Response(
                    responseType.getCode(),
                    responseType.getHttpStatus().value(),
                    null,
                    this.error == null ? responseType.getMessage() : this.error
            );
        }
    }
}
