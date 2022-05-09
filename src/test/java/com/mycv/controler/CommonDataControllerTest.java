package com.mycv.controler;

import com.mycv.controller.CommonDataController;
import com.mycv.model.CommonData;
import com.mycv.service.CommonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CommonDataController.class)
@EnableWebMvc
class CommonDataControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    @MockBean
    private CommonService commonService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext)
                .build();
    }

    @Test
    void should_return_OK_when_DegreeLevelsExists() throws Exception {
        when(commonService.getDegreeLevels()).thenReturn(Arrays.asList(
                new CommonData(1, "sample"),
                new CommonData(2, "sample-1")
        ));
        mockMvc.perform(get("/degree-level"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }

    @Test
    void should_return_empty_list_when_DegreeLevelsNotFound() throws Exception {
        when(commonService.getDegreeLevels()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/degree-level"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andDo(print());
    }

    @Test
    void should_return_OK_when_EducationStudyFields() throws Exception {
        when(commonService.getEducationStudyFields()).thenReturn(Arrays.asList(
                new CommonData(1, "sample"),
                new CommonData(2, "sample-1")
        ));
        mockMvc.perform(get("/education-study-field"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }

    @Test
    void should_return_empty_list_when_EducationStudyFieldsNotFound() throws Exception {
        when(commonService.getDegreeLevels()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/education-study-field"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andDo(print());
    }

    @Test
    void should_return_OK_when_EmploymentTypes() throws Exception {
        when(commonService.getEducationStudyFields()).thenReturn(Arrays.asList(
                new CommonData(1, "sample"),
                new CommonData(2, "sample-1")
        ));
        mockMvc.perform(get("/employee-type"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }

    @Test
    void should_return_empty_list_when_EmploymentTypesNotFound() throws Exception {
        when(commonService.getDegreeLevels()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/employee-type"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resCode").value(1002))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andDo(log());
    }

}
