CREATE TABLE user_qualification_details (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL,
    qualification_id BIGINT NOT NULL,
    level INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_details(id),
    FOREIGN KEY (qualification_id) REFERENCES qualifications(id)
);