package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class CvUpdateRequest {
    @Min(value = 1, message = "cv id is needed")
    @Max(value = Integer.MAX_VALUE, message = "invalid cv id")
    private int id;
    private String summery;
    private String firstName;
    private String surname;
    private String country;
    private String city;
    private String email;
    private String contactNumber;
    private int jobFieldId;
}
