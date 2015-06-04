CREATE TABLE "SUBJECT" (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  uuid     UUID NOT NULL,
  deleted  BOOLEAN,
  name     VARCHAR(60),
  tutor_id INT REFERENCES "ROLE" (id)
);