package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@ToString
public class EduEntryUpdateRequest {
    @Min(value = 1, message = "education entry id is needed")
    @Max(value = Integer.MAX_VALUE, message = "invalid education entry id")
    private int id;
    private String institutionName;
    private String title;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private int eduFieldId;
    private int degreeLevelId;
}
