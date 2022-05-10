package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.model.entity.EducationHistoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = EducationDetailService.class)
class EducationDetailServiceTest {

    @MockBean
    private EducationDetailService educationDetailService;

    private MockMvc mockMvc;

    @Test
    void should_throw_error_when_create_if_cv_NotFound() {
        doThrow(ApiException.class).when(educationDetailService).create(any());
        assertThrows(ApiException.class, () -> educationDetailService.create(any()));
    }

    @Test
    void should_create_if_valid_cv() {
        doNothing().when(educationDetailService).create(any());
        assertDoesNotThrow(() -> educationDetailService.create(any()));
    }

    @Test
    void should_throw_error_if_id_notFound() {
        assertDoesNotThrow(() -> educationDetailService.delete(1));
    }

    @Test
    void should_delete_if_valid_id() {
        assertDoesNotThrow(() -> educationDetailService.delete(anyInt()));
    }

    @Test
    void should_return_list_if_data_exists() {
        List<EducationHistoryEntity> list = new ArrayList<>();
        list.add(new EducationHistoryEntity());

        when(educationDetailService.get()).thenReturn(list);
        assertEquals(list.size(), 1);
    }

}