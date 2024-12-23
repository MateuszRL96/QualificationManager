
INSERT INTO qualifications (id, name, description) VALUES (1, 'Qualification 1', 'Description for Qualification 1');
INSERT INTO qualifications (id, name, description) VALUES (2, 'Qualification 2', 'Description for Qualification 2');
INSERT INTO qualifications (id, name, description) VALUES (3, 'Qualification 3', 'Description for Qualification 3');

INSERT INTO recommendations (id, title, url, qualification_id) VALUES (1, 'Recommendation 1', 'http://example.com', 1);
INSERT INTO recommendations (id, title, url, qualification_id) VALUES (2, 'Recommendation 2', 'http://example.com', 2);
INSERT INTO recommendations (id, title, url, qualification_id) VALUES (3, 'Recommendation 3', 'http://example.com', 3);

INSERT INTO users (id, email, name, role) VALUES (1, 'mat@email.com', 'Mat', 'USER');
INSERT INTO users (id, email, name, role) VALUES (2, 'kul@email.com', 'Kul', 'ADMIN');