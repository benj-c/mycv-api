package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.model.CvData;
import com.mycv.model.entity.EducationHistoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CvServiceTest {

    @MockBean
    private CvService cvService;

    private MockMvc mockMvc;

    @Test
    void should_throw_error_when_create_if_user_NotFound() {
        doThrow(ApiException.class).when(cvService).createNewCv(any());
        assertThrows(ApiException.class, () -> cvService.createNewCv(any()));
    }

    @Test
    void should_create_if_valid_user() {
        doNothing().when(cvService).createNewCv(any());
        assertDoesNotThrow(() -> cvService.createNewCv(any()));
    }

    @Test
    void throw_error_if_id_eq_0_when_findCv() {
        doThrow(Exception.class).when(cvService).findCv(0);
        assertThrows(Exception.class, () -> cvService.findCv(any()));
    }

    @Test
    void return_Obj_if_id_found_when_findCv() {
        doReturn(CvData.builder().build()).when(cvService).findCv(4);
        CvData cvData = cvService.findCv(4);
        assertNotNull(cvData);
    }

    @Test
    void should_delete_if_valid_cv_id() {
        assertDoesNotThrow(() -> cvService.permaDeleteCv(anyInt()));
    }

    @Test
    void should_throw_error_when_submit_if_cvNotFound() {
        doThrow(ApiException.class).when(cvService).submit(4, true);
        assertThrows(ApiException.class, () -> cvService.submit(4, true));
    }

}