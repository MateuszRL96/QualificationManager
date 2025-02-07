package com.qulificationRecomendation.qulificationRecomendation.Migrations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

class DataMigrationsTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DataMigrations dataMigrations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void runInsertsUserAndQualificationsWhenUserDoesNotExist() throws Exception {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(0);

        dataMigrations.run();

        verify(jdbcTemplate, times(1)).update(contains("INSERT INTO user_details"));
        verify(jdbcTemplate, times(1)).update(contains("INSERT INTO user_qualification_details (user_id, qualification_id, level) VALUES (1, 10, 1)"));
        verify(jdbcTemplate, times(1)).update(contains("INSERT INTO user_qualification_details (user_id, qualification_id, level) VALUES (1, 20, 1)"));
    }

    @Test
    void runDoesNotInsertUserAndQualificationsWhenUserExists() throws Exception {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

        dataMigrations.run();

        verify(jdbcTemplate, never()).update(contains("INSERT INTO user_details"));
        verify(jdbcTemplate, never()).update(contains("INSERT INTO user_qualification_details"));
    }
}