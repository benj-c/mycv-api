package com.mycv.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtData {
    private Long userId;
    private String role;
    private String userName;
}
