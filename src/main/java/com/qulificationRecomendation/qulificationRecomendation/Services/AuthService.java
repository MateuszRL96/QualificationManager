package com.qulificationRecomendation.qulificationRecomendation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> countLoginsByDate() {
        String sql = "SELECT DATE(login_date) as login_date, COUNT(*) as login_count " +
                "FROM auth0_user " +
                "GROUP BY DATE(login_date) " +
                "ORDER BY DATE(login_date)";
        return jdbcTemplate.queryForList(sql);
    }
}
