package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Repo.UserQualificationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserQualificationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserQualificationDetailsRepository userQualificationDetailsRepository;

    public void addQualificationToUser(Long userId, Long qualificationId, String level) {
        int levelInt = convertLevelToInt(level);
        String sql = "INSERT INTO user_qualification_details (user_id, qualification_id, level) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, qualificationId, levelInt);
    }

    private int convertLevelToInt(String level) {
        switch (level.toLowerCase()) {
            case "beginner":
                return 1;
            case "intermediate":
                return 2;
            case "expert":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    public List<Map<String, Object>> getAllUserQualifications() {
        String sql = "SELECT * FROM user_qualification_details";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getQualificationsByUserId(Long userId) {
        String sql = "SELECT uqd.id, uqd.user_id, uqd.qualification_id, uqd.level, q.name, q.description " +
                "FROM user_qualification_details uqd " +
                "JOIN qualifications q ON uqd.qualification_id = q.id " +
                "WHERE uqd.user_id = ?";
        return jdbcTemplate.queryForList(sql, userId);
    }

    public void deleteQualificationFromUser(Long qualificationId) {
        String sql = "DELETE FROM user_qualification_details WHERE user_id = 1 AND qualification_id = ?";
        jdbcTemplate.update(sql, qualificationId);
    }
}