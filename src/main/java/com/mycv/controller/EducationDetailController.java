package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.request.EduEntryRequest;
import com.mycv.model.request.EduEntryUpdateRequest;
import com.mycv.service.EducationDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@Slf4j
public class EducationDetailController {

    private final EducationDetailService educationDetailService;

    public EducationDetailController(EducationDetailService educationDetailService) {
        this.educationDetailService = educationDetailService;
    }

    /**
     * creates new edu entry
     *
     * @param eduEntryRequest
     * @return
     */
    @PostMapping(
            path = "/education",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> createEduEntry(
            @Valid @RequestBody EduEntryRequest eduEntryRequest
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createEduEntry");
        log.info("ReqBody|{}", eduEntryRequest.toString());
        try {
            this.educationDetailService.create(eduEntryRequest);
            Response response = Response.success("Educational qualification has successfully added")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createEduEntry|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * updates given edu entry
     *
     * @param request
     * @return
     */
    @PutMapping(
            path = "/education",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> updateEduEntry(
            @Valid @RequestBody EduEntryUpdateRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|updateEduEntry");
        log.info("ReqBody|{}", request.toString());
        try {
            this.educationDetailService.update(request);
            Response response = Response.success("Educational qualification has successfully updated")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|updateEduEntry|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * deletes given edu entry
     *
     * @param id
     * @return
     */
    @DeleteMapping(
            path = "/education/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> deleteEduEntry(
            @PathVariable int id
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|deleteEduEntry");
        log.info("PathVars|id:{}", id);
        try {
            this.educationDetailService.delete(id);
            Response response = Response.success("Educational qualification has successfully deleted")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|deleteEduEntry|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
