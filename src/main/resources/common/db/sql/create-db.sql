CREATE SCHEMA IF NOT EXISTS fia;

SET SCHEMA fia;

CREATE TABLE IF NOT EXISTS USERS (
  id        INTEGER PRIMARY KEY,
  name      VARCHAR(30),
  email     VARCHAR(50),
  password  VARCHAR(80),
  age       INTEGER
);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
  user_id   INTEGER,
  role      VARCHAR(100),
);

CREATE TABLE IF NOT EXISTS SERVICE_PROVIDERS (
  user_id           INTEGER,
  service_type      VARCHAR(100),
  network_quality   VARCHAR(100),
  service_capacity  VARCHAR (100),
  plan              VARCHAR (100)
);