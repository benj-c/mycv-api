package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.model.entity.EducationHistoryEntity;
import com.mycv.model.entity.ProfessionalQualificationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProfessionalQualificationServiceTest {

    @MockBean
    private ProfessionalQualificationService service;

    private MockMvc mockMvc;

    @Test
    void should_throw_error_when_create_if_cv_NotFound() {
        doThrow(ApiException.class).when(service).create(any());
        assertThrows(ApiException.class, () -> service.create(any()));
    }

    @Test
    void should_create_if_there_is_valid_cv() {
        doNothing().when(service).create(any());
        assertDoesNotThrow(() -> service.create(any()));
    }

    @Test
    void should_throw_error_when_update_if_profQual_NotFound() {
        doThrow(ApiException.class).when(service).update(any());
        assertThrows(ApiException.class, () -> service.update(any()));
    }

    @Test
    void should_update() {
        doNothing().when(service).update(any());
        assertDoesNotThrow(() -> service.update(any()));
    }

    @Test
    void should_delete_if_valid_id() {
        assertDoesNotThrow(() -> service.delete(anyInt()));
    }

    @Test
    void should_return_list_if_data_exists() {
        List<ProfessionalQualificationEntity> list = new ArrayList<>();
        list.add(new ProfessionalQualificationEntity());

        when(service.get()).thenReturn(list);
        assertEquals(list.size(), 1);
    }
}
