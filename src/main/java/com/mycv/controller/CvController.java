package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.CvData;
import com.mycv.model.GeneratedDocxDocDetail;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.request.CvUpdateRequest;
import com.mycv.model.request.NewCv;
import com.mycv.service.CvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

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
            CvEntity entity = this.cvService.createNewCv(newCv);
            Response response = Response.success(entity).build(ResponseType.OPERATION_SUCCESS);
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
            @Valid @RequestBody CvUpdateRequest request
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|updateCv");
        log.info("ReqBody|{}", request.toString());
        try {
            this.cvService.update(request);
            Response response = Response.success("CV has successfully updated").build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|updateCv|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
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
    @RolesAllowed({UserRoles.JOB_SEEKER, UserRoles.AGENT})
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

    /**
     * deletes CV object of given cv id
     * @param cvId
     * @return
     */
    @DeleteMapping(
            path = "/cv/{cvId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({UserRoles.JOB_SEEKER, UserRoles.AGENT, UserRoles.ADMIN})
    public ResponseEntity<Response> deleteCv(
            @PathVariable int cvId
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|deleteCv");
        log.info("PathVars|{}", cvId);
        try {
            this.cvService.permaDeleteCv(cvId);
            Response response = Response.success("CV has successfully deleted")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|deleteCv|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * creates new CV
     *
     * @param newCv
     * @return
     */
    @PostMapping(
            path = "/cv/{cvId}/submit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> submitCv(
            @RequestParam("isDraft") boolean isDraft,
            @PathVariable("cvId") int cvId
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|submitCv");
        log.info("ReqParam|isDraft:{}", isDraft);
        log.info("PathVar|cvId:{}", cvId);
        try {
            this.cvService.submit(cvId, isDraft);
            Response response = Response.success("CV has successfully " + (isDraft ? "saved as draft" : "submitted"))
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|submitCv|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * @param cvId
     * @return
     */
    @GetMapping(
            path = "/cv/{cvId}/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @RolesAllowed({UserRoles.AGENT})
    public ResponseEntity<Resource> generateAndDownloadDocx(
            @PathVariable int cvId
    ) throws IOException {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|generateAndDownloadDocx");
        log.info("PathVars|{}", cvId);
        try {
            GeneratedDocxDocDetail data = this.cvService.createDocx(cvId);
            log.info("Res|file");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + data.getFileName() + "\"")
                    .body(data.getResource());
        } finally {
            log.info("Completed|generateAndDownloadDocx|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

}
