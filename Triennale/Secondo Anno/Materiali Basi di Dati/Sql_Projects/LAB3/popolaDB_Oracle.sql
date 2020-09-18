INSERT INTO ISTRUTTORE (CodFisc, Nome, Cognome, DataNascita, Email, Telefono)
VALUES('SMTPLA80N31B791Z','Paul','Smith','31-dic-1980','p.smith@email.it',NULL);
INSERT INTO ISTRUTTORE (CodFisc, Nome, Cognome, DataNascita, Email, Telefono)
VALUES('KHNJHN81E30C455Y','John','Johnson','30-mag-1981','j.johnson@email.it','+2300110303444');
INSERT INTO ISTRUTTORE (CodFisc, Nome, Cognome, DataNascita, Email, Telefono)
VALUES('AAAGGG83E30C445A','Peter','Johnson','30-mag-1981','p.johnson@email.it','+2300110303444');


INSERT INTO CORSI (CodC,Nome,Tipo,Livello)
VALUES('CT100','Spinning principianti','Spinning','1');
INSERT INTO CORSI (CodC,Nome,Tipo,Livello)
VALUES('CT101','Ginnastica e musica','AttivitÃ  musicale','2');
INSERT INTO CORSI (CodC,Nome,Tipo,Livello)
VALUES('CT104','Spinning professionisti','Spinning','4');

INSERT INTO PROGRAMMA (CodFisc,Giorno,OrarioInizio,Durata,CodC,Sala)
VALUES('SMTPLA80N31B791Z','Lunedì',TO_DATE('10:00','hh24:mi'),'45','CT100','S1');
INSERT INTO PROGRAMMA (CodFisc,Giorno,OrarioInizio,Durata,CodC,Sala)
VALUES('SMTPLA80N31B791Z','Martedì',TO_DATE('11:00','hh24:mi'),'45','CT100','S1');
INSERT INTO PROGRAMMA (CodFisc,Giorno,OrarioInizio,Durata,CodC,Sala)
VALUES('SMTPLA80N31B791Z','Martedì',TO_DATE('15:00','hh24:mi'),'45','CT100','S2');
INSERT INTO PROGRAMMA (CodFisc,Giorno,OrarioInizio,Durata,CodC,Sala)
VALUES('KHNJHN81E30C455Y','Lunedì',TO_DATE('10:00','hh24:mi'),'30','CT101','S2');
INSERT INTO PROGRAMMA (CodFisc,Giorno,OrarioInizio,Durata,CodC,Sala)
VALUES('KHNJHN81E30C455Y','Lunedì',TO_DATE('11:30','hh24:mi'),'30','CT104','S2');
INSERT INTO PROGRAMMA (CodFisc,Giorno,OrarioInizio,Durata,CodC,Sala)
VALUES('KHNJHN81E30C455Y','Mercoledì',TO_DATE('9:00','hh24:mi'),'60','CT104','S1');