CREATE TABLE GROUP_TABLE (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  uuid    UUID NOT NULL,
  deleted BOOLEAN,
  name    VARCHAR(10)
);