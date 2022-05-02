package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@ToString
public class EduEntryRequest {

    @NotEmpty(message = "please provide institution name")
    @NotBlank(message = "please provide institution name")
    private String institutionName;

    @NotEmpty(message = "please provide a title for the qualification")
    @NotBlank(message = "please provide a title for the qualification")
    private String title;

    @NotEmpty(message = "please provide location")
    @NotBlank(message = "please provide location")
    private String location;

    @NotNull(message = "please provide start date")
    private LocalDate startDate;

    @NotNull(message = "please provide end date")
    private LocalDate endDate;

    @Min(value = 1, message = "please select a valid education field")
    @Max(value = Integer.MAX_VALUE, message = "please select a valid education field")
    private int eduFieldId;

    @Min(value = 1, message = "please select a valid education level")
    @Max(value = Integer.MAX_VALUE, message = "please select a valid education level")
    private int degreeLevelId;
}
