package com.mycv.controler;

import com.mycv.controller.CvController;
import com.mycv.model.request.CvUpdateRequest;
import com.mycv.model.request.NewCv;
import com.mycv.service.CvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest(classes = CvController.class)
@EnableWebMvc
class CvControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    @MockBean
    private CvService cvService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext)
                .build();
    }

    @Test
    void should_CreateCv_When_ValidRequest() throws Exception {
        NewCv newCv = NewCv.builder()
                .city("testCity")
                .contact_number("TestCn")
                .country("testCo")
                .email("testEm")
                .first_name("testFn")
                .job_field_id(2)
                .surname("testSun")
                .build();
        this.mockMvc.perform(post("/cv")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(newCv.toJsonString())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }

    @Test
    void should_CreateCv_Fail_When_InValidRequestBody() throws Exception {
        NewCv newCv = NewCv.builder()
                .city("testCity")
                .contact_number("TestCn")
                .country("testCo")
                .email("testEm")
                .first_name("testFn")
                .job_field_id(2)
                .build();
        this.mockMvc.perform(post("/cv")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(newCv.toJsonString())
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void should_UpdateCv_When_ValidRequest() throws Exception {
        CvUpdateRequest newCv = CvUpdateRequest.builder()
                .id(1)
                .build();
        this.mockMvc.perform(post("/cv")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(newCv.toJsonString())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }
}
