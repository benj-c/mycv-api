package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CvUpdateRequest {
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
