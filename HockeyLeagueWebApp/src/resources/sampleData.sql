-- USE DB;
-- Use dbhockeyleague;
Use DBHOCKEYLEAGUE;

-- Team tb
-- SELECT * FROM DBHOCKEYLEAGUE.team;
-- TRUNCATE TABLE team;
INSERT INTO DBHOCKEYLEAGUE.team VALUES 
(001, 'Red'),
(002, 'Blue');

-- Player tb
-- SELECT * FROM DBHOCKEYLEAGUE.player;
-- TRUNCATE TABLE DBHOCKEYLEAGUE.player;
INSERT INTO DBHOCKEYLEAGUE.player VALUES
(0001, 'Adam Abbot', '100 First St.', 001, 'DEFENSE', 'T'),
(0002, 'Bob Barker', '21 King St.', 001, 'WINGER', 'T'),
(0003, 'Charlie Chaplin', '301 Time Ave.', 001, 'CENTER', 'T');