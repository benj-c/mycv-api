package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.request.ProfQualUpdateRequest;
import com.mycv.model.request.SpecificSkilRequest;
import com.mycv.model.request.SpecificSkilUpdateRequest;
import com.mycv.service.SpecificSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@Slf4j
public class SpecificSkillController {

    private SpecificSkillService specificSkillService;

    public SpecificSkillController(
            SpecificSkillService specificSkillService
    ) {
        this.specificSkillService = specificSkillService;
    }

    /**
     * creates new specific skill entry
     *
     * @param request
     * @return
     */
    @PostMapping(
            path = "/specific-skill",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> createSpecificSkill(
            @Valid @RequestBody SpecificSkilRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createSpecificSkill");
        log.info("ReqBody|{}", request.toString());
        try {
            this.specificSkillService.create(request);
            Response response = Response.success("specific skill has successfully added")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createSpecificSkill|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * update specific skill entry
     *
     * @param request
     * @return
     */
    @PutMapping(
            path = "/specific-skill",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> updateSpecificSkill(
            @Valid @RequestBody SpecificSkilUpdateRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|updateSpecificSkill");
        log.info("ReqBody|{}", request.toString());
        try {
            this.specificSkillService.update(request);
            Response response = Response.success("Specific skill has successfully updated")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|updateSpecificSkill|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * deletes given specific skill
     *
     * @param id
     * @return
     */
    @DeleteMapping(
            path = "/specific-skill/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> deleteSpecificSkill(
            @PathVariable int id
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|deleteSpecificSkill");
        log.info("PathVars|id:{}", id);
        try {
            specificSkillService.delete(id);
            Response response = Response.success("Specific skill has successfully deleted")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|deleteSpecificSkill|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
