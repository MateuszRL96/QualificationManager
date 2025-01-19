ALTER TABLE qualifications ADD COLUMN auth0_user_id BIGINT;

ALTER TABLE qualifications ADD CONSTRAINT fk_auth0_user
    FOREIGN KEY (auth0_user_id) REFERENCES auth0_user(id);