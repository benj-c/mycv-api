package com.mycv.model.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class EduEntryRequest {
    private String institution_name;
    private String location;
    private LocalDate awarded_date;
    private int edu_field_id;
    private int cv_id;
}
