-- src/main/resources/db/migration/V10__CreateUserQualificationsTable.sql
CREATE TABLE user_qualifications (
    user_id BIGINT NOT NULL,
    qualification_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, qualification_id),
    FOREIGN KEY (user_id) REFERENCES auth0_user(id),
    FOREIGN KEY (qualification_id) REFERENCES qualifications(id)
);