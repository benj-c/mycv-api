package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class SpecificSkilRequest {
    @NotEmpty(message = "please provide title")
    @NotBlank(message = "please provide title")
    private String title;
    @NotEmpty(message = "please provide field")
    @NotBlank(message = "please provide field")
    private String field;
    @NotEmpty(message = "please provide description")
    @NotBlank(message = "please provide description")
    private String description;
}
