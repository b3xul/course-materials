-- create an empty database. Name of the database: 
SET storage_engine=InnoDB;
SET FOREIGN_KEY_CHECKS=1;
CREATE DATABASE IF NOT EXISTS televisione;

-- use televisione 
USE televisione;


-- drop tables if they already exist
DROP TABLE IF EXISTS APPARIZIONE;
DROP TABLE IF EXISTS VIP;
DROP TABLE IF EXISTS CANALE_TV;

-- create tables

CREATE TABLE VIP (
	CodFisc CHAR(20) ,
	Nome CHAR(50) NOT NULL ,
	Cognome CHAR(50) NOT NULL ,
	Professione CHAR(50) NULL ,
	PRIMARY KEY (CodFisc)
);

CREATE TABLE CANALE_TV (
	CodC CHAR(10) ,
	Nome CHAR(50) NOT NULL ,
	Emittente CHAR(50) NOT NULL ,
	Frequenza INT NOT NULL,
	PRIMARY KEY (CodC)
);

CREATE TABLE APPARIZIONE (
	CodFisc CHAR(20) NOT NULL ,
    Data DATE NOT NULL , 
	OraInizio TIME NOT NULL ,
	OraFine TIME NOT NULL ,
    CodC CHAR(10) NOT NULL ,
	PRIMARY KEY (CodFisc,Data,OraInizio),
	FOREIGN KEY (CodFisc) REFERENCES VIP(CodFisc) 
		ON DELETE CASCADE
		ON UPDATE CASCADE,
    FOREIGN KEY (CodC) REFERENCES CANALE_TV(CodC) 
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

