package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class NewCv {

    private String summery;
    private String first_name;
    private String surname;
    private String country;
    private String city;
    private String email;
    private String contact_number;
    private int job_field_id;

}
