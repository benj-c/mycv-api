package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.CvData;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.CvEntity;
import com.mycv.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@Slf4j
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(
            path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({UserRoles.AGENT, UserRoles.ADMIN})
    public ResponseEntity<Response> search(
            @RequestParam("q") String query
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|search");
        log.info("ReqParam|q:{}", query);
        try {
            List<CvEntity> results = this.searchService.search(query);
            Response response = Response.success(results)
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|search|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }
}
