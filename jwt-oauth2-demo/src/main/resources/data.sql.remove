-- In Spring Boot using Spring Data, we use ~/src/main/resources/data.sql to seed data.
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE roles_seq START WITH 1 INCREMENT BY 1;

-- New Users --
-- Password for user1 was 'user1'
 insert into users(id,email,password,first_name,last_name)
    values (1, 'user1@example.com','$2a$10$d37BpcDJvTr2G0ehFEvg.OtW9Omavhf12T6d50iCSbm2NdIlpo2Uq','User','1');
-- Password for admin was 'admin'
 insert into users(id,email,password,first_name,last_name)
    values (2,'admin1@example.com','$2a$10$d37BpcDJvTr2G0ehFEvg.OtW9Omavhf12T6d50iCSbm2NdIlpo2Uq','Admin','1');
-- Password for user2 was 'user2'
 insert into users(id,email,password,first_name,last_name)
    values (3,'user2@example.com','$2a$10$d37BpcDJvTr2G0ehFEvg.OtW9Omavhf12T6d50iCSbm2NdIlpo2Uq','User2','2');

ALTER SEQUENCE users_seq START WITH 4;

-- ROLES --
insert into roles(id, name) values (1, 'ROLE_USER');
insert into roles(id, name) values (2, 'ROLE_ADMIN');


-- User have one role
insert into user_role(user_id,role_id) values (1, 1);
-- Admin has two roles
insert into user_role(user_id,role_id) values (2, 1);
insert into user_role(user_id,role_id) values (2, 2);

ALTER SEQUENCE roles_seq START WITH 4;
-- the end --