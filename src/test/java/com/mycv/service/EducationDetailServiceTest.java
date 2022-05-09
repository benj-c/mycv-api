package com.mycv.service;

import com.mycv.controller.CommonDataController;
import com.mycv.exception.ApiException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EducationDetailService.class)
class EducationDetailServiceTest {

    @MockBean
    private EducationDetailService educationDetailService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
    }

    @Test
    void should_return_error_if_cv_NotFound() {

        try {
            doThrow(ApiException.class).when(educationDetailService).create(any());
        } catch (ApiException e) {
//            Ass
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getEducationDetailRepository() {
    }

    @Test
    void getCvRepository() {
    }
}