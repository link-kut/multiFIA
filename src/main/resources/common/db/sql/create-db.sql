create schema fia AUTHORIZATION DBA;
set schema fia;

CREATE TABLE USERS (
  id        INTEGER PRIMARY KEY,
  name      VARCHAR(30),
  email     VARCHAR(50),
  password  VARCHAR(20),
  age       INTEGER
);

CREATE TABLE AUTHORITIES (
  user_id   INTEGER,
  role      VARCHAR(100),
);