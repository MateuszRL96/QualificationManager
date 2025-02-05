CREATE TABLE cv_data_edukacja (
    id SERIAL PRIMARY KEY,
    cv_data_id INTEGER NOT NULL,
    edukacja TEXT NOT NULL,
    FOREIGN KEY (cv_data_id) REFERENCES cv_data(id)
);