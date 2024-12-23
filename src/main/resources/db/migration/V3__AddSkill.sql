-- src/main/resources/db/migration/V3__AddSkills.sql

CREATE TABLE skills (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    level VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    links TEXT[],
    average_learning_time INT NOT NULL,
    qualification_id BIGINT NOT NULL,
    CONSTRAINT fk_qualification
        FOREIGN KEY(qualification_id)
        REFERENCES qualifications(id)
);

INSERT INTO skills (id, name, image_url, level, description, links, average_learning_time, qualification_id) VALUES
(1, 'Skill 1', 'http://example.com/image1.png', 'Beginner', 'Description for Skill 1', ARRAY['http://link1.com', 'http://link2.com'], 10, 1),
(2, 'Skill 2', 'http://example.com/image2.png', 'Intermediate', 'Description for Skill 2', ARRAY['http://link3.com', 'http://link4.com'], 20, 1),
(3, 'Skill 3', 'http://example.com/image3.png', 'Advanced', 'Description for Skill 3', ARRAY['http://link5.com', 'http://link6.com'], 30, 2);