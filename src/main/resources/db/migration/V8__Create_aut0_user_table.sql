-- src/main/resources/db/migration/V8__Create_auth0_user_table.sql
CREATE TABLE auth0_user (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    sub VARCHAR(255),
    picture VARCHAR(255),
    login_date TIMESTAMP
);