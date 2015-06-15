CREATE TABLE TEST_QUESTION (
  student_id  INT REFERENCES USER (ID),
  test_id     INT REFERENCES TEST (ID),
  question_id INT REFERENCES QUESTION (ID),
  answer_id   INT REFERENCES ANSWER (ID),
  PRIMARY KEY (student_id, test_id, question_id)
);

