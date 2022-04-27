package com.mycv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@ToString
@Builder
public class AuthData {
    private String username;
    private String email;
    private LocalDate createdDate;
    private boolean isAvtive;
    private String scope;
    private String token;
}
