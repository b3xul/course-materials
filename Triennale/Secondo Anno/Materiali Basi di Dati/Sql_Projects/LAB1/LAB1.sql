--ES 1
--Dati fattorini:
SELECT *
FROM DELIVERERS;
--ES 2
--Identificativi aziende per cui hanno lavorato i fattorini:
--N.B. DISTINCT!
SELECT DISTINCT COMPANYID
FROM COMPANYDEL;
--ES3
--Nome e codice dei fattorini che hanno il NAME iniziante per B
SELECT NAME, DELIVERERID
FROM DELIVERERS
WHERE NAME LIKE 'B%';
--ES4
--Nome, sesso, codice dei fattorini il cui PHONENO è diverso da 8467 o non esiste
SELECT NAME,SEX,DELIVERERID
FROM DELIVERERS
WHERE PHONENO<>8467 OR PHONENO IS NULL;
--ES5
--Nome, città dei fattorini che hanno ricevuto almeno una multa
--N.B. DISTINCT!
SELECT DISTINCT NAME, TOWN
FROM DELIVERERS D, PENALTIES P
WHERE D.DELIVERERID=P.DELIVERERID;
--ES6
--Nome e iniziali dei referenti di azienda (fattorini speciali) che hanno ricevuto almeno una multa dopo il
--31/12/1980 ordinati in ordine alfabetico per nome
--N.B. JOIN LASCIA DUPLICATI!->DISTINCT!
--N.B. ORDER BY, NON SORT BY
SELECT DISTINCT NAME, INITIALS
FROM DELIVERERS D, COMPANIES C, PENALTIES P
WHERE C.DELIVERERID=P.DELIVERERID AND P.DELIVERERID=D.DELIVERERID AND DATA > TO_DATE('31/12/1980','DD/MM/YYYY')
ORDER BY NAME;
--ES6b
--Come il 6 ma con l’IN
--N.B. IN elimina duplicati
--N.B.YYYY, NON AAAA
SELECT NAME, INITIALS
FROM DELIVERERS
WHERE DELIVERERID IN (
    SELECT DELIVERERID
    FROM COMPANIES
    WHERE DELIVERERID IN (
        SELECT DELIVERERID
        FROM PENALTIES
        WHERE DATA > TO_DATE('31/12/1980','DD/MM/YYYY')));
--ES7
--Identificatore azienda e fattorino con esso con TOWN=stratford tra cui ci sono stati
--almeno 2 ritiri e 1 consegna
SELECT COMPANYID, C.DELIVERERID
FROM COMPANYDEL C, DELIVERERS D
WHERE C.DELIVERERID=D.DELIVERERID AND NUMCOLLECTIONS>=2 AND NUMDELIVERIES>=1 AND TOWN='Stratford';
--ES8
--Identificatore fattorini nati dopo il 1962 che hanno effettuato almeno una consegna
--a una compagnia!! con il referente al primo mandato, in ordine decrescente di data
--N.B. A un’azienda possono consegnare anche fattorini diversi dal referente
--N.B.2 Non importa di quali tabelle faccio il join fra 3
--N.B.3 Non è un po' disordinato col join?
SELECT DISTINCT D.DELIVERERID
FROM DELIVERERS D, COMPANYDEL CD, COMPANIES C
WHERE C.COMPANYID=CD.COMPANYID AND CD.DELIVERERID=D.DELIVERERID AND D.YEAR_OF_BIRTH>1962 AND C.MANDATE='first'
ORDER BY D.DELIVERERID DESC;
--ES8b
--Come l’8 ma con l’IN
SELECT DELIVERERID
FROM DELIVERERS
WHERE YEAR_OF_BIRTH>1962 AND
DELIVERERID IN(
    SELECT DELIVERERID
    FROM COMPANYDEL
    WHERE COMPANYID IN(
        SELECT COMPANYID
        FROM COMPANIES
        WHERE MANDATE='first'))
ORDER BY DELIVERERID DESC;
--ES9
--Nome fattorini con TOWN=’Inglewood’ OR TOWN=’Stratford’ che sono andati presso almeno 2 aziende
--E’ uguale prendere c1.delid o c2.delid perché il risultato del join C1-C2 avrà come campi
--C1.COMPANYID,C1.DELIVERERID,C2.COMPANYID,C2.DELIVERERID,…
--Ma per l’altra condizione di join so che c1.delid e c2.delid sono sicuramente uguali
--N.B. LE PARENTESI NELLE CONDIZIONI DEL WHERE!!
SELECT DISTINCT NAME
FROM DELIVERERS D, COMPANYDEL C1, COMPANYDEL C2
WHERE (TOWN='Inglewood' OR TOWN='Stratford') AND
	C1.COMPANYID<>C2.COMPANYID AND
	C1.DELIVERERID=C2.DELIVERERID AND
	C1.DELIVERERID=D.DELIVERERID;
--ES9b
--Come il 9 ma usando GROUP BY invece dell'algebra
SELECT DISTINCT NAME
FROM DELIVERERS D, COMPANYDEL C
WHERE (TOWN='Inglewood' OR TOWN='Stratford') AND
    C.DELIVERERID=D.DELIVERERID
GROUP BY C.DELIVERERID,NAME
HAVING COUNT(*)>=2;
--ES10
--ID fattorino e totale multe ricevute di tutti i fattorini di Inglewood che hanno preso almeno 2 multe
--N.B. In algebra non si possono fare le somme!! Per quello questo metodo non funziona, bisogna farlo per forza in SQL!
--SELECT D.DELIVERERID, SUM(P1.AMOUNT)
--FROM DELIVERERS D, PENALTIES P1, PENALTIES P2
--WHERE TOWN='Inglewood' AND
--	P1.PAYMENTID>P2.PAYMENTID AND
--	P1.DELIVERERID=P2.DELIVERERID AND
--	P1.DELIVERERID=D.DELIVERERID
SELECT P.DELIVERERID,SUM(AMOUNT)
FROM DELIVERERS D, PENALTIES P
WHERE TOWN='Inglewood' AND 
    D.DELIVERERID=P.DELIVERERID
GROUP BY P.DELIVERERID
HAVING COUNT(*)>=2;
--ES11
--NOME e MULTA MINIMA di tutti i fattorini che hanno ricevuto almeno 2 multe e non più di 4
--Si può raggruppare anche per nome perchè c'è dipendenza funzionale tra Delivererid e name
--Si DEVE raggruppare anche per nome perchè è quello il risultato della select
SELECT NAME,MIN(AMOUNT)
FROM DELIVERERS D, PENALTIES P
WHERE D.DELIVERERID=P.DELIVERERID
GROUP BY P.DELIVERERID,NAME
HAVING  COUNT(*)>=2 AND
        COUNT(*)<=4;
--ES12
--Numero totale di consegne e ritiri effettuati da fattorini non di Stratford che iniziano per B
--SUM interna somma tutte le consegne e tutti i ritiri effettuati da un singolo fattorino,
--SUM esterna somma le somme di tutti i fattorini coinvolti in modo da trovare il totale di consegne e ritiri dei fattorini
SELECT SUM(SUM(NUMDELIVERIES)),SUM(SUM(NUMCOLLECTIONS))
FROM DELIVERERS D,COMPANYDEL C
WHERE   NAME LIKE 'B%' AND
        TOWN<>'Stratford' AND
        D.DELIVERERID=C.DELIVERERID
GROUP BY C.DELIVERERID;

CREATE VIEW PROVA2 AS
SELECT D.DELIVERERID,D.DELIVERERID AS NOME
FROM DELIVERERS D, COMPANYDEL C
WHERE D.DELIVERERID=C.DELIVERERID;