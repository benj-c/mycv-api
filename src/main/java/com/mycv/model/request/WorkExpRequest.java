package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class WorkExpRequest {
    private String jobTitle;
    private String country;
    private String employer;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private int employmentTypeId;
}
