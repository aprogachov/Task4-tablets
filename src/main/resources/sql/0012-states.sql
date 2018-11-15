--liquibase formatted sql

--changeset andrey:11
INSERT INTO `STATES` (`STATE_ID`, `CODE`, `NAME`)
VALUES
	(1, 'AR', 'Arkansas'),
	(2, 'CO', 'Colorado'),
	(3, 'FL', 'Florida'),
	(4, 'HI', 'Hawaii'),
	(5, 'LA', 'Louisiana'),
	(6, 'NV', 'Nevada'),
	(7, 'NJ', 'New Jersey'),
	(8, 'OH', 'Ohio'),
	(9, 'TX', 'Texas'),
	(10, 'WA', 'Washington');