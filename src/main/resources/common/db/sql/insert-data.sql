SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE USERS;
TRUNCATE TABLE AUTHORITIES;
SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO USERS VALUES (1, 'fia', 'link.koreatech@gmail.com', 'fia', 31);
INSERT INTO USERS VALUES (2, 'alex', 'alex@yahoo.com', '1111', 31);
INSERT INTO USERS VALUES (3, 'joel', 'joel@gmail.com', '1111', 31);

INSERT INTO AUTHORITIES VALUES (1, 'ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES (2, 'ROLE_USER');
INSERT INTO AUTHORITIES VALUES (3, 'ROLE_USER');
