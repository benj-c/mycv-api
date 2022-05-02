package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@ToString
public class WorkExpUpdateRequest {
    @Min(value = 1, message = "work experience id is needed")
    @Max(value = Integer.MAX_VALUE, message = "invalid work experience id")
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
