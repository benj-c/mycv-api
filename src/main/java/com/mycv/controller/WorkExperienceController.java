package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.WorkExperienceEntity;
import com.mycv.model.request.WorkExpRequest;
import com.mycv.model.request.WorkExpUpdateRequest;
import com.mycv.service.WorkExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class WorkExperienceController {

    private WorkExperienceService workExperienceService;

    public WorkExperienceController(
            WorkExperienceService workExperienceService
    ) {
        this.workExperienceService = workExperienceService;
    }

    /**
     * creates new work experience entry
     *
     * @param request
     * @return
     */
    @PostMapping(
            path = "/work-experience",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> createWorkExp(
            @Valid @RequestBody WorkExpRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createWorkExp");
        log.info("ReqBody|{}", request.toString());
        try {
            this.workExperienceService.create(request);
            Response response = Response.success("success")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createWorkExp|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(
        path = "/work-experience",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> getWorkExp() {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|getWorkExp");
        try {
            List<WorkExperienceEntity> workExperienceEntities = this.workExperienceService.get();
            Response response = Response.success(workExperienceEntities)
                .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|getWorkExp|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * update work experience entry
     *
     * @param request
     * @return
     */
    @PutMapping(
            path = "/work-experience",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> updateWorkExp(
            @Valid @RequestBody WorkExpUpdateRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|updateWorkExp");
        log.info("ReqBody|{}", request.toString());
        try {
            this.workExperienceService.update(request);
            Response response = Response.success("work experience has successfully updated")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|updateWorkExp|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * deletes given work experience
     *
     * @param id
     * @return
     */
    @DeleteMapping(
            path = "/work-experience/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> deleteWorkExp(
            @PathVariable int id
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|deleteWorkExp");
        log.info("PathVars|id:{}", id);
        try {
            workExperienceService.delete(id);
            Response response = Response.success("work experience has successfully deleted")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|deleteWorkExp|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
