--ES1
SELECT NomeD,COUNT(*)
FROM DIPARTIMENTO D,RICERCATORE R,AZIENDA A,CONTRATTO_DI_RICERCA CDR
WHERE   D.CodD=R.CodD AND
        R.CodR=CDR.CodR_ResponsabileScientifico AND
        A.CodA=CDR.CodA AND
        CDR.CodR_ResponsabileScientifico NOT IN (SELECT CodR_ResponsabileScientifico
                                                FROM AZIENDA A2,CONTRATTO_DI_RICERCA CDR2
                                                WHERE   A2.CodA=CDR2.CodA AND
                                                        (TipoA<>'Grande Azienda' OR
                                                        Importo<=100000))
GROUP BY D.CodD,NomeD;
--ES2
SELECT A.Settore_Industriale,D.CodD,D.NomeD
FROM DIPARTIMENTO D, RICERCATORE R,AZIENDA A,CONTRATTO_DI_RICERCA CDR    
WHERE   D.CodD=R.CodD AND
        R.CodR=CDR.CodR_ResponsabileScientifico AND
        A.CodA=CDR.CodA
GROUP BY A.Settore_Industriale,D.CodD,D.NomeD
HAVING COUNT(*)=MAX((SELECT COUNT(*)
                    FROM  RICERCATORE R2,AZIENDA A2,CONTRATTO_DI_RICERCA CDR2
                    WHERE   R2.CodR=CDR2.CodR_ResponsabileScientifico AND
                            A2.CodA=CDR2.CodA AND
                            A.Settore_Industriale=A2.Settore_Industriale AND
                            R.DataPresaServizio>TO_DATE('30/06/2015','dd/mm/yyyy')
                    GROUP BY A2.Settore_Industriale));
--ES3
SELECT APA1.CodAutore,Cognome,Università,Conferenza,Edizione,COUNT(*)
FROM AUTORE AU1,AUTORE_PRESENTA_ARTICOLO APA1
WHERE   AU1.CodAutore=APA1.CodAutore AND
        APA1.CodAutore NOT IN   (SELECT APA2.CodAutore
                                FROM AUTORE AU2,ARTICOLO AR2,AUTORE_PRESENTA_ARTICOLO APA2
                                WHERE   AU2.CodAutore=APA2.CodAutore AND
                                        AR2.CodArticolo=APA2.CodArticolo AND
                                        AR2.Argomento<>'Data Mining')
GROUP BY APA1.CodAutore,Cognome,Università,Conferenza,Edizione;
--ES4
SELECT CodS
FROM CALENDARIO
WHERE CodD IN   (SELECT CodD
                FROM COMPETENZE C1
                GROUP BY CodD
                HAVING COUNT(*)=    (SELECT MAX(conteggio.numero_competenze)
                                    FROM    (SELECT COUNT(*) AS numero_competenze
                                            FROM COMPETENZE C2
                                            GROUP BY C2.CodD)conteggio));
--ES5
SELECT MatrDoc,CodCorso
FROM CORSO C1
WHERE   MatrDoc NOT IN  (SELECT MatrDoc
                        FROM CORSO
                        WHERE Area<>'Basi di dati') AND
        CodCorso IN (SELECT CodCorso
                    FROM CORSO C2,LEZIONE L1
                    WHERE   C2.CodCorso=L1.CodCorso AND
                            C1.MatrDoc=C2.MatrDoc
                    GROUP BY CodCorso,NumStudentiPresenti
                    HAVING NumStudentiPresenti =    (SELECT MAX(Medie.Media_Studenti_Presenti)
                                                    FROM    (SELECT AVG(NumStudentiPresenti) AS Media_Studenti_Presenti
                                                             FROM LEZIONE
                                                             GROUP BY CodCorso)Medie));
--ES6
SELECT S.MatricolaS,S.Cognome,S.Corso_di_Laurea
FROM STUDENTE S,HOMEWORK_DA_CONSEGNARE HDC1,VALUTAZIONE_HW_CONSEGNATI VHC1
WHERE   S.MatricolaS=VHC1.MatricolaS AND
        HDC1.CodHW=VHC1.CodHW AND
        S.MatricolaS NOT IN (SELECT MatricolaS
                            FROM HOMEWORK_DA_CONSEGNARE HDC2,VALUTAZIONE_HW_CONSEGNATI VHC2
                            WHERE   HDC2.CodHW=VHC2.CodHW AND
                                    DataConsegna>DataScadenza) AND
        VHC1.Valutazione =  (SELECT MAX(VHC3.Valutazione)
                            FROM VALUTAZIONE_HW_CONSEGNATI VHC3
                            WHERE VHC1.CodHW=VHC3.CodHW)
GROUP BY S.MatricolaS,S.Cognome,S.Corso_di_Laurea
HAVING COUNT(*) =   (SELECT COUNT(*)
                    FROM HOMEWORK_DA_CONSEGNARE HDC2);
