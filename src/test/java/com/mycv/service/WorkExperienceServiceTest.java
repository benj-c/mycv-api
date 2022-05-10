package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.model.entity.SpecificSkilEntity;
import com.mycv.model.entity.WorkExperienceEntity;
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
class WorkExperienceServiceTest {

    @MockBean
    private WorkExperienceService workExperienceService;

    private MockMvc mockMvc;

    @Test
    void should_throw_error_when_create_if_cv_NotFound_for_authenticatedUser() {
        doThrow(ApiException.class).when(workExperienceService).create(any());
        assertThrows(ApiException.class, () -> workExperienceService.create(any()));
    }

    @Test
    void should_create_if_there_is_valid_cv() {
        doNothing().when(workExperienceService).create(any());
        assertDoesNotThrow(() -> workExperienceService.create(any()));
    }

    @Test
    void should_throw_error_when_update_if_workExp_NotFound_for_id() {
        doThrow(ApiException.class).when(workExperienceService).update(any());
        assertThrows(ApiException.class, () -> workExperienceService.update(any()));
    }

    @Test
    void should_update_if_workExp_id_found() {
        doNothing().when(workExperienceService).update(any());
        assertDoesNotThrow(() -> workExperienceService.update(any()));
    }

    @Test
    void should_delete_if_valid_id() {
        assertDoesNotThrow(() -> workExperienceService.delete(anyInt()));
    }

    @Test
    void should_return_list_if_data_exists() {
        List<WorkExperienceEntity> list = new ArrayList<>();
        list.add(new WorkExperienceEntity());

        when(workExperienceService.get()).thenReturn(list);
        assertEquals(list.size(), 1);
    }
}