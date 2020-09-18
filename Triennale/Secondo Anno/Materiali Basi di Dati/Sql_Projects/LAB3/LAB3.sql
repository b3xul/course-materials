--ES1
--CODICE, DATA PRIMA MULTA, DATA ULTIMA MULTA dei fattorini che hanno preso almeno due multe
--SELECT DELIVERERID,FIRST_PENALTY_DATA,LAST_PENALTY_DATA
--FROM PENALTIES, (SELECT DATA AS FIRST_PENALTY_DATA
--                FROM PENALTIES P1
--                GROUP BY DELIVERERID
--                HAVING DATA =    (SELECT MIN(DATA)
--                                FROM PENALTIES P2
--                                WHERE P1.DELIVERERID=P2.DELIVERERID)) PRIMA,
--                (SELECT DATA AS LAST_PENALTY_DATA
--                FROM PENALTIES P1
--                GROUP BY DELIVERERID
--                HAVING DATA =    (SELECT MAX(DATA)
--                                FROM PENALTIES P2
--                                WHERE P1.DELIVERERID=P2.DELIVERERID)) ULTIMA                
--GROUP BY DELIVERERID
--HAVING COUNT(*)>=2;
--Non si può perchè i campi di una table function dovrebbero andare nella select del GROUP BY
--ma la group by non può conoscerli essendo in una funzione annidata per cui non ha senso!
--molto più facile
SELECT DELIVERERID,MIN (DATA) AS FIRST_PENALTY_DATA,MAX(DATA) AS LAST_PENALTY_DATA
FROM PENALTIES
GROUP BY DELIVERERID
HAVING COUNT(*)>=2;
--ES2
--CODICE, DATA ultima multa e ammontare di tale multa per ogni fattorino che ha preso almeno una multa
SELECT DELIVERERID, DATA AS LAST_PENALTY_DATA, AMOUNT
FROM PENALTIES
WHERE (DELIVERERID,DATA) IN   (SELECT DELIVERERID,MAX(DATA)
                               FROM PENALTIES
                               GROUP BY DELIVERERID);
--ES2b
--2 modi per controllare che il delivererID sia presente sia in una tabella che nella sua annidata:
--o lo uso come attributo nell'IN o controllo tramite Condizioni di correlazione
--In questo caso però potrebbero esserci duplicati!
SELECT P1.DELIVERERID, DATA, AMOUNT
FROM PENALTIES P1
WHERE P1.DATA = (SELECT MAX(DATA)
                FROM PENALTIES P2
                WHERE P2. DELIVERERID=P1.DELIVERERID);
--ES3
--COMPANYID in cui almeno il 30% dei fattorini presenti del DB hanno fatto almeno 1 consegna o ritiro
--DELIVERERS COUNT(*)*30/100=*0,3
--Quanti fattorini si sono recati in ogni azienda
--LEGGI I CAMPI!! C'E' UN SOLO CAMPO DELIVERERID!!
--COMPANYDEL GROUP BY COMPANYID
--Sono sicuro che i delivererid siano diversi percè fanno parte della chiave primaria e raggruppo per l'altra parte
SELECT COMPANYID
FROM COMPANYDEL
GROUP BY COMPANYID
HAVING COUNT(*)>    (SELECT 0.3*COUNT(*)
                    FROM DELIVERERS);
--E se avessi DELIVERERID_D e DELIVERERID_C come farei?
--COUNT(DISTINCT DEL_D)+COUNT(DISTINCT DEL_C)- COUNT(DEL_C=DEL_D)->Tuple di COMPANYDEL che hanno DEL_C=DEL_D
--per togliere i duplicati posso sfruttare la UNION!
--faccio union tra DEL_C e DEL_D e conto cosa ottengo->elimina i duplicati e
--non c'è bisogno di compatibilità di schema se so che i dati sono uguali
--PROVA
SELECT NUMDELIVERIES,COUNT(*)
FROM COMPANYDEL
WHERE NUMDELIVERIES >3
GROUP BY COMPANYID,NUMDELIVERIES;