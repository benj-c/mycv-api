package com.mycv.controller;

import com.mycv.exception.ResponseType;
import com.mycv.model.AuthData;
import com.mycv.model.Response;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.EducationHistoryEntity;
import com.mycv.model.entity.UserEntity;
import com.mycv.model.request.UserCredentialsRequest;
import com.mycv.service.UserService;
import com.mycv.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(
            UserService userService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * authenticate user credentials and create jwt access token
     * @param userCredentialsRequest
     * @return
     * @throws JoseException
     */
    @PostMapping(
            path = "/authenticate",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> doAuthenticate(
            @Valid @RequestBody UserCredentialsRequest userCredentialsRequest
    ) throws JoseException {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|doAuthenticate");
        log.info("ReqBody|{}", userCredentialsRequest.toString());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCredentialsRequest.getUsername(),
                            userCredentialsRequest.getPassword()
                    )
            );

            AuthData data = userService.createAccessToken(userCredentialsRequest.getUsername());
            Response response = Response.success(data).build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|doAuthenticate|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * activation of deactivation of user
     * @return
     */
    @PatchMapping(
            path = "/user/act-deact/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({UserRoles.ADMIN, UserRoles.AGENT})
    public ResponseEntity<Response> actDeactUser(
            @PathVariable int userId
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|actDeactUser");
        log.info("PathVars|userId={}", userId);
        try {
            boolean b = userService.actDeatUser(userId);
            Response response = Response.success("User has successfully " + (b ? "activated" : "de-activated"))
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response);
            return ResponseEntity.ok().body(response);
        } finally {
            log.info("Completed|actDeactUser|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }


    /**
     * register new user, job_seeker
     * @param userCredentialsRequest
     * @return
     */
    @PostMapping(
            path = "/user/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> registerUser(
            @Valid @RequestBody UserCredentialsRequest userCredentialsRequest
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|registerUser");
        log.info("ReqBody|{}", userCredentialsRequest.toString());
        try {
            userCredentialsRequest.setRole(UserRoles.JOB_SEEKER);
            userService.createUser(userCredentialsRequest);
            Response response = Response.success("User has successfully created")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|registerUser|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * create new user
     * @param userCredentialsRequest
     * @return
     */
    @PostMapping(
            path = "/user/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed({UserRoles.ADMIN})
    public ResponseEntity<Response> createUser(
            @Valid @RequestBody UserCredentialsRequest userCredentialsRequest
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|createUser");
        log.info("ReqBody|{}", userCredentialsRequest.toString());
        try {
            userService.createUser(userCredentialsRequest);
            Response response = Response.success("User has successfully created")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|createUser|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RolesAllowed(UserRoles.ADMIN)
    public ResponseEntity<Response> getUsers() {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|getUsers");
        try {
            List<UserEntity> allUsers = this.userService.getAllUsers();
            Response response = Response.success(allUsers)
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|getUsers|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(
            path = "/user/{id}/delete",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    @RolesAllowed(UserRoles.JOB_SEEKER)
    public ResponseEntity<Response> deleteUser(
            @PathVariable int id
    ) {
        long startTime = System.currentTimeMillis();
        log.info("Initiating|deleteUser");
        log.info("PathVars|id:{}", id);
        try {
            this.userService.delete(id);
            Response response = Response.success("User has successfully deleted")
                    .build(ResponseType.OPERATION_SUCCESS);
            log.info("Res|{}", response.toString());
            return ResponseEntity.ok(response);
        } finally {
            log.info("Completed|deleteUser|ProcessingTime:{}ms", System.currentTimeMillis() - startTime);
        }
    }

}
