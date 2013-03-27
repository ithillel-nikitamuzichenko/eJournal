CREATE SCHEMA IF NOT EXISTS EJournal;

CREATE TABLE IF NOT EXISTS `EJournal`.`Users` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `Role` INT NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  `Surname` VARCHAR(45) NULL ,
  `BirthDate` DATETIME NULL ,
  `Login` VARCHAR(45) NOT NULL ,
  `Password` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC));
INSERT INTO `EJournal`.`Users` VALUES (null, 2, null, null, null, 'Admin', '12345');
