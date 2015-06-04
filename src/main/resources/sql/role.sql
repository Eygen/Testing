CREATE TABLE ROLE (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  uuid UUID NOT NULL,
  name VARCHAR(10)
);

INSERT INTO ROLE VALUES (DEFAULT, random_uuid(), 'admin');
INSERT INTO ROLE VALUES (DEFAULT, random_uuid(), 'tutor');
INSERT INTO ROLE VALUES (DEFAULT, random_uuid(), 'student');
INSERT INTO ROLE VALUES (DEFAULT, random_uuid(), 'user');