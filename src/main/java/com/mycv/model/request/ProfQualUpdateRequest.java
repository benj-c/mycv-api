package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class ProfQualUpdateRequest {

    @Min(value = 1, message = "professional qualification id is needed")
    @Max(value = Integer.MAX_VALUE, message = "invalid professional qualification id")
    private int id;
    private String title;
    private String field;
    private String description;
}
