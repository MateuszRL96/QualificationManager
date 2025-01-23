ALTER TABLE qualifications ADD COLUMN employer_details_id BIGINT;

ALTER TABLE qualifications
ADD CONSTRAINT fk_employer_details
FOREIGN KEY (employer_details_id)
REFERENCES employer_details(id);