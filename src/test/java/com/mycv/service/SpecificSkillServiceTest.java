package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.model.entity.SpecificSkilEntity;
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
class SpecificSkillServiceTest {

    @MockBean
    private SpecificSkillService specificSkillService;

    private MockMvc mockMvc;

    @Test
    void should_throw_error_when_create_if_cv_NotFound() {
        doThrow(ApiException.class).when(specificSkillService).create(any());
        assertThrows(ApiException.class, () -> specificSkillService.create(any()));
    }

    @Test
    void should_create_if_there_is_valid_cv() {
        doNothing().when(specificSkillService).create(any());
        assertDoesNotThrow(() -> specificSkillService.create(any()));
    }

    @Test
    void should_throw_error_when_update_if_specSkill_NotFound() {
        doThrow(ApiException.class).when(specificSkillService).update(any());
        assertThrows(ApiException.class, () -> specificSkillService.update(any()));
    }

    @Test
    void should_update_if_id_found() {
        doNothing().when(specificSkillService).update(any());
        assertDoesNotThrow(() -> specificSkillService.update(any()));
    }

    @Test
    void should_delete_if_valid_id() {
        assertDoesNotThrow(() -> specificSkillService.delete(anyInt()));
    }

    @Test
    void should_return_list_if_data_exists() {
        List<SpecificSkilEntity> list = new ArrayList<>();
        list.add(new SpecificSkilEntity());

        when(specificSkillService.get()).thenReturn(list);
        assertEquals(list.size(), 1);
    }
}