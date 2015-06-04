CREATE TABLE "QUESTION" (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  uuid        UUID NOT NULL,
  description VARCHAR(500),
  test_id     INT REFERENCES "TEST" (id)
);