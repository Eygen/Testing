CREATE TABLE "MARK" (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  uuid       UUID NOT NULL,
  student_id INT REFERENCES "USER" (id),
  test_id    INT REFERENCES "TEST" (id),
  value      INT
);