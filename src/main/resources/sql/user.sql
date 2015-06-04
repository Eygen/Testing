CREATE TABLE USER (
  id           INT PRIMARY KEY AUTO_INCREMENT,
  uuid         UUID             NOT NULL,
  deleted      BOOLEAN,
  firstname    VARCHAR(50),
  lastname     VARCHAR(50),
  email        VARCHAR(50),
  login        VARCHAR(30),
  password     VARCHAR(32),
  registerDate DATE,
  role_id      INT REFERENCES ROLE (ID),
  group_id     INT DEFAULT NULL NULL REFERENCES GROUP_TABLE (ID)
);