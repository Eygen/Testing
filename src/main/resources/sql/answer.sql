CREATE TABLE "ANSWER" (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  uuid        UUID NOT NULL,
  question_id INT REFERENCES "QUESTION" (id),
  test_id     INT REFERENCES "TEST" (id),
  description VARCHAR(500),
  correct     BOOLEAN
);