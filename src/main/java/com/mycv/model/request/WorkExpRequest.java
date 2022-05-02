package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@ToString
public class WorkExpRequest {
    @NotEmpty(message = "please provide job title")
    @NotBlank(message = "please provide job title")
    private String jobTitle;
    @NotEmpty(message = "please provide country")
    @NotBlank(message = "please provide country")
    private String country;
    @NotEmpty(message = "please provide employer")
    @NotBlank(message = "please provide employer")
    private String employer;
    @NotEmpty(message = "please provide city")
    @NotBlank(message = "please provide city")
    private String city;
    @NotNull(message = "please provide start date")
    private LocalDate startDate;
    private LocalDate endDate;

    @Min(value = 1, message = "please select an employment type")
    @Max(value = Integer.MAX_VALUE, message = "please select an employment type")
    private int employmentTypeId;
}
