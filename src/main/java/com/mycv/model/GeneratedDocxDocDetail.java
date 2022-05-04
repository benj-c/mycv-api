package com.mycv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;

@Data
@AllArgsConstructor
public class GeneratedDocxDocDetail {
    private ByteArrayResource resource;
    private String fileName;
}
