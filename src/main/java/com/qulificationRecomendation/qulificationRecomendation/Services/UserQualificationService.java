package com.qulificationRecomendation.qulificationRecomendation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class UserQualificationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addQualificationToUser(Long userId, Long qualificationId, int level) {
        String sql = "INSERT INTO user_qualification_details (user_id, qualification_id, level) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, qualificationId, level);
    }

    public List<Map<String, Object>> getAllUserQualifications() {
        String sql = "SELECT * FROM user_qualification_details";
        return jdbcTemplate.queryForList(sql);
    }
}