package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@ToString
public class NewCv {

    private String summery;
    @NotEmpty(message = "please provide first name")
    @NotBlank(message = "please provide first name")
    private String first_name;
    @NotEmpty(message = "please provide surname")
    @NotBlank(message = "please provide surname")
    private String surname;
    @NotEmpty(message = "please provide country")
    @NotBlank(message = "please provide country")
    private String country;
    @NotEmpty(message = "please provide city")
    @NotBlank(message = "please provide city")
    private String city;
    @NotEmpty(message = "please provide email")
    @NotBlank(message = "please provide email")
    private String email;
    @NotEmpty(message = "please provide contact number")
    @NotBlank(message = "please provide contact number")
    private String contact_number;

    @Min(value = 1, message = "please select a valid job field")
    @Max(value = Integer.MAX_VALUE, message = "please select a valid job field")
    private int job_field_id;

}
