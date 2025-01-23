package com.qulificationRecomendation.qulificationRecomendation.Migrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataMigrations implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Check if user already exists
        String checkUserSql = "SELECT COUNT(*) FROM user_details WHERE id = 1";
        Integer count = jdbcTemplate.queryForObject(checkUserSql, Integer.class);

        if (count == 0) {
            // Insert user if not exists
            String insertUserSql = "INSERT INTO user_details (id, first_name, last_name, email, password, age, address) " +
                    "VALUES (4, 'John', 'Doe', 'john.doe@example.com', 'password123', 30, '123 Main St')";
            jdbcTemplate.update(insertUserSql);

            // Insert qualifications
            String sql1 = "INSERT INTO user_qualification_details (user_id, qualification_id, level) VALUES (1, 10, 1)";
            String sql2 = "INSERT INTO user_qualification_details (user_id, qualification_id, level) VALUES (1, 20, 1)";
            jdbcTemplate.update(sql1);
            jdbcTemplate.update(sql2);
        }
    }
}
