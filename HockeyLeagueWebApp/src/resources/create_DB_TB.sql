-- create database
-- DROP SCHEMA dbhockeyleague;
CREATE SCHEMA IF NOT EXISTS dbhockeyleague;
-- USE dbhockeyleague;
Use DBHOCKEYLEAGUE;

-- DROP TABLE team;
CREATE TABLE team (
    teamID INT PRIMARY KEY,
    teamName VARCHAR(100)
);

-- DROP TABLE player;
CREATE TABLE player (
    playerID INT PRIMARY KEY,
    playerName VARCHAR(100),
    playerAddress VARCHAR(200),
    teamID INT,
    playerRole VARCHAR(100),
    playerActiveStatus VARCHAR(1),
    CONSTRAINT fk_player_teamID FOREIGN KEY (teamID) REFERENCES team(teamID)
);

