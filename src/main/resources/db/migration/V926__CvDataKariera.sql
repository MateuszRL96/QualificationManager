CREATE TABLE cv_data_kariera (
    id SERIAL PRIMARY KEY,
    cv_data_id INTEGER NOT NULL,
    kariera TEXT NOT NULL,
    FOREIGN KEY (cv_data_id) REFERENCES cv_data(id)
);

CREATE TABLE cv_data_umiejetnosci (
    id SERIAL PRIMARY KEY,
    cv_data_id INTEGER NOT NULL,
    umiejetnosci TEXT NOT NULL,
    FOREIGN KEY (cv_data_id) REFERENCES cv_data(id)
);