package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.CommonData;
import com.mycv.model.Response;
import com.mycv.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CommonDataController {

    private CommonService commonService;

    public CommonDataController(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     *
     * @return
     */
    @GetMapping(
            path = "/degree-level",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> getDegreeLevels() {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|getDegreeLevels");
        try {
            List<CommonData> degreeLevels = commonService.getDegreeLevels();
            Response response = Response.success(degreeLevels).build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|getDegreeLevels|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(
            path = "/education-study-field",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> getEducationStudyFields() {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|getEducationStudyFields");
        try {
            List<CommonData> educationStudyFields = commonService.getEducationStudyFields();
            Response response = Response.success(educationStudyFields).build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|getEducationStudyFields|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(
            path = "/employee-type",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> getEmploymentTypes() {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|getEmploymentTypes");
        try {
            List<CommonData> employmentTypeEntities = commonService.getEmploymentTypes();
            Response response = Response.success(employmentTypeEntities).build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|getEmploymentTypes|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
