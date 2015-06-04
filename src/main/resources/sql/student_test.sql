CREATE TABLE STUDENT_TEST (
  student_id INT REFERENCES USER (ID),
  test_id    INT REFERENCES TEST (ID),
  active     BOOLEAN DEFAULT TRUE,
  PRIMARY KEY (student_id, test_id)
);