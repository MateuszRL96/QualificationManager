CREATE TABLE skill_links (
    skill_id BIGINT NOT NULL,
    links TEXT NOT NULL,
    CONSTRAINT fk_skill
        FOREIGN KEY(skill_id)
        REFERENCES skills(id)
);


INSERT INTO skill_links (skill_id, links) VALUES
(1, 'http://link1.com'),
(1, 'http://link2.com'),
(2, 'http://link3.com'),
(2, 'http://link4.com'),
(3, 'http://link5.com'),
(3, 'http://link6.com')