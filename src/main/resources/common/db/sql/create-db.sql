create schema fia;
set schema fia;

CREATE TABLE USERS (
  id        INTEGER PRIMARY KEY,
  name      VARCHAR(30),
  email     VARCHAR(50),
  password  VARCHAR(80),
  age       INTEGER
);

CREATE TABLE AUTHORITIES (
  user_id   INTEGER,
  role      VARCHAR(100),
);

CREATE TABLE SERVICE_PROVIDERS (
  user_id       INTEGER,
  service_type  VARCHAR(100),
)