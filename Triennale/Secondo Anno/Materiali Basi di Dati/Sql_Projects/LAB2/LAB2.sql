--ES1
--CODICE,NOME,INIZIALI dei fattorini che non hanno mai preso multe
SELECT DELIVERERID, NAME, INITIALS
FROM DELIVERERS
WHERE DELIVERERID NOT IN   (SELECT DELIVERERID
                            FROM PENALTIES);
--ES1b
--SELECT sempre * perchè più efficiente
--NOT EXIST->seleziona condizione che le tuple non devono verificare
--perchè li danno con un ordine diverso??????

SELECT DELIVERERID, NAME, INITIALS
FROM DELIVERERS D
WHERE NOT EXISTS (SELECT *
                  FROM PENALTIES P
                  WHERE D.DELIVERERID=P.DELIVERERID);
--ES2
--CODICE dei fattorini che hanno ricevuto ALMENO UNA MULTA da 25 e ALMENO UNA MULTA da 30 euro
--NON una multa da ALMENO 25 EURO!!!
--Algebra:
SELECT P1.DELIVERERID
FROM PENALTIES P1, PENALTIES P2
WHERE   P1.DELIVERERID=P2.DELIVERERID AND
        P1.PAYMENTID<>P2.PAYMENTID AND
        P1.AMOUNT=25 AND P2.AMOUNT=30;
--ES2b
--UOU: cerca le tuple che hanno valore=25 e sono hanno il delivererid presente tra le tuple 
--che hanno amount=30!
SELECT DELIVERERID
FROM PENALTIES
WHERE   AMOUNT=25
        AND DELIVERERID IN (SELECT DELIVERERID
                            FROM PENALTIES
                            WHERE AMOUNT=30);
--ES2c
SELECT DISTINCT DELIVERERID
FROM PENALTIES
WHERE   DELIVERERID IN (SELECT DELIVERERID
                        FROM PENALTIES
                        WHERE AMOUNT=25) AND
        DELIVERERID IN (SELECT DELIVERERID
                        FROM PENALTIES
                        WHERE AMOUNT=30);
--ES3
--CODICE e NOME dei fattorini che hanno ricevuto più di una multa nella stessa data
--GROUP BY con più campi restituisce tutte le tuple che hanno quella coppia di campi uguali!!
SELECT P.DELIVERERID,DATA,NAME
FROM DELIVERERS D, PENALTIES P
WHERE D.DELIVERERID=P.DELIVERERID
GROUP BY P.DELIVERERID,DATA,NAME
HAVING  COUNT(*)>1;
--
--SELECT COMPANYID
--FROM COMPANYDEL
--GROUP BY COMPANYID,NUMCOLLECTIONS
--HAVING  COUNT(*)>1;
--ES4
--CODICE dei fattorini che sono andati in TUTTE le aziende di COMPANIES
SELECT DELIVERERID
FROM COMPANYDEL
GROUP BY DELIVERERID
HAVING COUNT(*)=   (SELECT COUNT(*)
                    FROM COMPANIES);
--ES5
--CODICE dei fattorini che hanno fatto consegne/ritiri in ALMENO UN'azienda in cui
--il fattorino 57 ha fatto consegne/ritiri (Escluso o incluso il 57? bah
SELECT DELIVERERID
FROM COMPANYDEL
WHERE COMPANYID IN (SELECT COMPANYID
                    FROM COMPANYDEL
                    WHERE DELIVERERID=57);
--ES6
--CODICE e NOMI dei fattorini che hanno ricevuto più multe nel 1980 che nel 1981
--conta quante istanze ci sono per ogni DELIVERERID che abbiano una data nel 1980 e le confronta
--con tutte le istanze che ci sono per ogni DELIVERERID che abbiano una data nel 1981
--Senza il secondo group by? wtf?
--Condizioni di correlazione: metto in relazione due funzioni aggregate di due tabelle nidificate
--Le uso quando ho sia una condizione di tupla (DATA) ma anche una di gruppo (COUNT(*) su DELIVERERID)
--e devo metterle in relazione su 2 tabelle nidificate
--1.Join DELIVERERS e PENALTIES P1
--2.condizione di tupla sulla data delle multe di tutti
--3.raggruppo le tuple che soddisfano la condizione per DELIVERERID
--4.conto a ogni DELIVERERID quante tuple sono associate
--5.per ogni gruppo confronto questo valore con il valore che risulta da
--6.N.B.Join tra la tabella appena ottenuta (già joinata e raggruppata!!) e PENALTIES P2 = condizione di correlazione
--7.vedi 2.
--8.conto, degli ID ottenuti precedentemente, quante tuple DI P2 soddisfano la condizione di tupla sulla DATA
--9. per ogni tupla confronto i due valori
SELECT D.DELIVERERID,NAME
FROM DELIVERERS D,PENALTIES P1
WHERE   D.DELIVERERID=P1.DELIVERERID AND
        DATA>=TO_DATE('01/01/1980','DD/MM/YYYY') AND
        DATA<=TO_DATE('31/12/1980','DD/MM/YYYY') 
GROUP BY D.DELIVERERID,NAME
HAVING COUNT(*)>   (SELECT COUNT(*)
                    FROM PENALTIES P2
                    WHERE   P2.DELIVERERID=D.DELIVERERID AND
                            DATA>=TO_DATE('01/01/1981','DD/MM/YYYY') AND
                            DATA<=TO_DATE('31/12/1981','DD/MM/YYYY'));
--ES7
--CODICE del fattorino che ha ricevuto più multe
--Funziona anche senza fare select del delivererid nella tabella annidata
SELECT DELIVERERID
FROM PENALTIES
GROUP BY DELIVERERID
HAVING COUNT(*) =  (SELECT MAX (CONTEGGIO)
                    FROM   (SELECT DELIVERERID, COUNT(*) AS CONTEGGIO
                            FROM PENALTIES 
                            GROUP BY DELIVERERID));
--ES7b
--Non posso usare totmulte nella tabella annidata perchè non la conosce
--Non posso usare COUNT(*) nel WHERE perchè non posso applicarla senza avere gruppi
SELECT DELIVERERID
FROM   (SELECT DELIVERERID, COUNT(*) AS CONTEGGIO
        FROM PENALTIES 
        GROUP BY DELIVERERID) TOTMULTE
WHERE CONTEGGIO =  (SELECT MAX (CONTEGGIO)
                    FROM   (SELECT DELIVERERID, COUNT(*) AS CONTEGGIO
                            FROM PENALTIES 
                            GROUP BY DELIVERERID));
--ES8
--CODICE dei fattorini che hanno fatto consegne/ritiri in TUTTE le aziende
--dove ha fatto consegne/ritiri il fattorino 57
SELECT DELIVERERID
FROM COMPANYDEL
WHERE COMPANYID IN (SELECT COMPANYID
                    FROM COMPANYDEL
                    WHERE DELIVERERID=57)
--Fattorini che hanno consegnato in almeno un'azienda in cui ha consegnato 57
GROUP BY DELIVERERID
HAVING COUNT(*) =  (SELECT COUNT(*)
                    FROM COMPANYDEL
                    WHERE DELIVERERID=57);
--ES9
--CODICE dei fattorini che hanno fatto consegne/ritiri SOLO nelle aziende
--dove ha fatto consegne/ritiri il fattorino 57
SELECT DELIVERERID
FROM COMPANYDEL
WHERE COMPANYID IN (SELECT COMPANYID
                    FROM COMPANYDEL
                    WHERE DELIVERERID=57)
--Fattorini che hanno consegnato in almeno un'azienda in cui ha consegnato 57
GROUP BY DELIVERERID
HAVING COUNT(*) =  (SELECT COUNT(*)
                    FROM COMPANYDEL
                    WHERE DELIVERERID=57);
SELECT DELIVERERID
FROM COMPANYDEL
WHERE DELIVERERID NOT IN (SELECT DELIVERERID
                          FROM COMPANYDEL
                          WHERE COMPANYID NOT IN (SELECT COMPANYID
                                                FROM COMPANYDEL
                                                WHERE DELIVERERID=57));
--ES10
--CODICE dei fattorini che hanno fatto consegne/ritiri in TUTTE e SOLE
--le aziende dove ha fatto consegne/ritiri il fattorino 57
SELECT DELIVERERID
FROM COMPANYDEL
WHERE DELIVERERID NOT IN (SELECT DELIVERERID
                          FROM COMPANYDEL
                          WHERE COMPANYID NOT IN (SELECT COMPANYID
                                                FROM COMPANYDEL
                                                WHERE DELIVERERID=57))
GROUP BY DELIVERERID
HAVING COUNT(*) =  (SELECT COUNT(*)
                    FROM COMPANYDEL
                    WHERE DELIVERERID=57);