CREATE SCHEMA IF NOT EXISTS EJournal;

CREATE TABLE IF NOT EXISTS EJournal.Users (
  ID INT NOT NULL AUTO_INCREMENT ,
  Role INT NOT NULL ,
  Name VARCHAR(45) NULL ,
  Surname VARCHAR(45) NULL ,
  BirthDate DATETIME NULL ,
  Sex INT NULL,
  Login VARCHAR(45) NOT NULL ,
  Password VARCHAR(45) NOT NULL ,
  PRIMARY KEY (ID) ,
INSERT INTO EJournal.Users VALUES (null, 2, null, null, null, 1, 'admin', '12345');

CREATE TABLE IF NOT EXISTS EJournal.EntitiesLog (
  ID INT NOT NULL AUTO_INCREMENT ,
  EntityID INT NOT NULL ,
  EntityType INT NOT NULL ,
  Date DATETIME NULL ,
  Action INT NOT NULL,
  Comment VARCHAR(255) NULL ,
  UserID INT NOT NULL,
  PRIMARY KEY (ID));
alter table EJournal.EntitiesLog add foreign key (UserID) references EJournal.Users(ID);
