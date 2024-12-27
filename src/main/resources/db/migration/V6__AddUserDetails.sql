CREATE TABLE user_details (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(255) NOT NULL
);

INSERT INTO user_details (first_name, last_name, email, password, age, address) VALUES
('John', 'Doe', 'john.doe@example.com', 'password123', 30, '123 Main St'),
('Jane', 'Smith', 'jane.smith@example.com', 'password456', 25, '456 Elm St'),
('Alice', 'Johnson', 'alice.johnson@example.com', 'password789', 28, '789 Oak St');