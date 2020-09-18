CREATE TABLE DIPARTIMENTO
	(CodD CHAR (5) PRIMARY KEY,
	NomeD VARCHAR (50),
	Settore_Scientifico	VARCHAR (50),
	Università VARCHAR (50));
	
CREATE TABLE RICERCATORE
	(CodR CHAR (5) PRIMARY KEY,
	Nome VARCHAR (50),
	Cognome VARCHAR (50),
	DataNascita DATE,
	CodD CHAR (5),
	DataPresaServizio DATE,
	FOREIGN KEY(CodD)
		REFERENCES DIPARTIMENTO(CodD));
	
CREATE TABLE AZIENDA
	(CodA CHAR (5) PRIMARY KEY,
	NomeA VARCHAR (50),
	TipoA VARCHAR (50),
	Città VARCHAR (50),
	Settore_Industriale	VARCHAR (50));
	
CREATE TABLE CONTRATTO_DI_RICERCA
	(CodR_ResponsabileScientifico CHAR (5),
	CodA CHAR (5),
	DataInizio DATE,
	Durata INT,
	Importo INT,
	PRIMARY KEY(CodR_ResponsabileScientifico,CodA,DataInizio),
	
	FOREIGN KEY(CodR_ResponsabileScientifico)
		REFERENCES RICERCATORE(CodR),
	
	FOREIGN KEY(CodA)
		REFERENCES AZIENDA(CodA));

--Popolamento
INSERT INTO DIPARTIMENTO (CodD, NomeD)
VALUES('Cod1','Nome1');

INSERT INTO DIPARTIMENTO (CodD, NomeD)
VALUES('Cod2','Nome2');

INSERT INTO DIPARTIMENTO (CodD, NomeD)
VALUES('Cod3','Nome3');

INSERT INTO DIPARTIMENTO (CodD, NomeD)
VALUES('Cod4','Nome4');

INSERT INTO DIPARTIMENTO (CodD, NomeD)
VALUES('Cod5','Nome5');

INSERT INTO RICERCATORE (CodR,CodD,DataPresaServizio)
VALUES('CodR1','Cod1',TO_DATE('29/6/2015','dd/mm/yyyy'));

INSERT INTO RICERCATORE (CodR,CodD,DataPresaServizio)
VALUES('CodR2','Cod1',TO_DATE('1/7/2015','dd/mm/yyyy'));

INSERT INTO RICERCATORE (CodR,CodD,DataPresaServizio)
VALUES('CodR3','Cod1',TO_DATE('2/7/2015','dd/mm/yyyy'));

INSERT INTO RICERCATORE (CodR,CodD,DataPresaServizio)
VALUES('CodR4','Cod1',TO_DATE('3/7/2015','dd/mm/yyyy'));

INSERT INTO RICERCATORE (CodR,CodD,DataPresaServizio)
VALUES('CodR5','Cod2',TO_DATE('29/6/2015','dd/mm/yyyy'));

INSERT INTO AZIENDA (CodA,TipoA,Settore_Industriale)
VALUES('CodA1','Media Azienda','Robotica');

INSERT INTO AZIENDA (CodA,TipoA,Settore_Industriale)
VALUES('CodA2','Grande Azienda','Robotica');

INSERT INTO AZIENDA (CodA,TipoA,Settore_Industriale)
VALUES('CodA3','Piccola Azienda','Meccanica');

INSERT INTO AZIENDA (CodA,TipoA,Settore_Industriale)
VALUES('CodA4','Grande Azienda','Alimentari');

INSERT INTO AZIENDA (CodA,TipoA,Settore_Industriale)
VALUES('CodA5','Grande Azienda','Farmaceutico');

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR1','CodA1',TO_DATE('10/2/1985','dd/mm/yyyy'),160000);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR2','CodA1',TO_DATE('12/3/1954','dd/mm/yyyy'),102000);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR3','CodA1',TO_DATE('10/2/2012','dd/mm/yyyy'),200);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR1','CodA2',TO_DATE('14/4/1986','dd/mm/yyyy'),110000);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR1','CodA3',TO_DATE('20/3/1999','dd/mm/yyyy'),130000);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR3','CodA1',TO_DATE('12/6/1998','dd/mm/yyyy'),7500);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR4','CodA4',TO_DATE('10/2/1985','dd/mm/yyyy'),160000);

INSERT INTO CONTRATTO_DI_RICERCA (CODR_RESPONSABILESCIENTIFICO,CodA,DataInizio,Importo)
VALUES('CodR4','CodA4',TO_DATE('12/2/1985','dd/mm/yyyy'),240000);