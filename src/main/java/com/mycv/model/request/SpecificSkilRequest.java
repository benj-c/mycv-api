package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SpecificSkilRequest {
    private String title;
    private String field;
    private String description;
}
