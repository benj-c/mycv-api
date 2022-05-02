package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class SpecificSkilUpdateRequest {

    @Min(value = 1, message = "specific skill id is needed")
    @Max(value = Integer.MAX_VALUE, message = "invalid specific skill id")
    private int id;
    private String title;
    private String field;
    private String description;
}
