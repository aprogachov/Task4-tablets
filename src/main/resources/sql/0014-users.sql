--liquibase formatted sql

--changeset andrey:14
INSERT INTO `USERS` (`LOGIN`, `EMAIL`, `PASSWORD`)
VALUES
	('user_1', 'user_1@mail.com', '$2a$10$TOqWUAEJcp8LdknAVTshQeLp0Z6.XAgw7iiJK6kS/t332TksVD1VC');
/* password: user_1 */