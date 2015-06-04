CREATE TABLE TEST (
  id              INT PRIMARY KEY AUTO_INCREMENT,
  uuid            UUID NOT NULL,
  subject_id      INT REFERENCES SUBJECT (id),
  question_amount INT,
  author_id       INT REFERENCES USER (id)
);