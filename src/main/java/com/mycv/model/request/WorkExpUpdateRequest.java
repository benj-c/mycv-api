package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class WorkExpUpdateRequest {
    private int id;
    private String jobTitle;
    private String country;
    private String employer;
    private String city;
    private Boolean isCurrentJob;
    private LocalDate startDate;
    private LocalDate endDate;
    private int employmentTypeId;
}
