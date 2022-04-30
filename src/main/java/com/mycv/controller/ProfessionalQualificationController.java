package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.request.EduEntryRequest;
import com.mycv.model.request.ProQualRequest;
import com.mycv.model.request.ProfQualUpdateRequest;
import com.mycv.service.ProfessionalQualificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@Slf4j
public class ProfessionalQualificationController {

    private ProfessionalQualificationService qualificationService;

    public ProfessionalQualificationController(ProfessionalQualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    /**
     * creates new professional qualification entry
     *
     * @param request
     * @return
     */
    @PostMapping(
            path = "/proqual",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> createProQual(
            @Valid @RequestBody ProQualRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createProQual");
        log.info("ReqBody|{}", request.toString());
        try {
            this.qualificationService.create(request);
            Response response = Response.success("Professional qualification has successfully added")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createProQual|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * creates new professional qualification entry
     *
     * @param request
     * @return
     */
    @PutMapping(
            path = "/proqual",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> updateProQual(
            @Valid @RequestBody ProfQualUpdateRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|updateProQual");
        log.info("ReqBody|{}", request.toString());
        try {
            this.qualificationService.update(request);
            Response response = Response.success("Professional qualification has successfully updated")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|updateProQual|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

}