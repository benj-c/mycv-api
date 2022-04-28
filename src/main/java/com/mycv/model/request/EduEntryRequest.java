package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class EduEntryRequest {
    private String institutionName;
    private String location;
    private LocalDate awardedDate;
    private int eduFieldId;
    private int cvId;
}
