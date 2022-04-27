package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.CvData;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.request.NewCv;
import com.mycv.service.CvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@Slf4j
public class CvController {

    private CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    /**
     * creates new CV
     *
     * @param newCv
     * @return
     */
    @PostMapping(
            path = "/cv",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> createCv(
            @Valid @RequestBody NewCv newCv
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createCv");
        log.info("ReqBody|{}", newCv.toString());
        try {
            this.cvService.createNewCv(newCv);
            Response response = Response.success("CV has successfully created as a draft").build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createCv|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * updates new CV
     *
     * @param newCv
     * @return
     */
    @PutMapping(
            path = "/cv",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> updateCv(
            @Valid @RequestBody NewCv newCv
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createCv");
        log.info("ReqBody|{}", newCv.toString());
        try {
//            this.cvService.createNewCv(newCv);
            Response response = Response.success("CV has successfully created as a draft").build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createCv|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * returns CV object of given cv id
     * @param cvId
     * @return
     */
    @GetMapping(
            path = "/cv/{cvId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({UserRoles.JOB_SEEKER, UserRoles.AGENT, UserRoles.ADMIN})
    public ResponseEntity<Response> retrieveCv(
            @PathVariable int cvId
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|retrieveCv");
        log.info("PathVars|{}", cvId);
        try {
            CvData cvData = this.cvService.findCv(cvId);
            Response response = Response.success(cvData).build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|retrieveCv|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
