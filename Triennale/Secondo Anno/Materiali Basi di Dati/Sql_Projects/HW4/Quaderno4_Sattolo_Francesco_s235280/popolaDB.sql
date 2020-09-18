SET storage_engine=InnoDB;
SET FOREIGN_KEY_CHECKS=1;
use televisione;

INSERT INTO VIP(CodFisc,Nome,Cognome,Professione)
        VALUES ('AAAAA00N31B751G','Aldo','Baglio','Comico');
       
INSERT INTO VIP(CodFisc,Nome,Cognome,Professione)
        VALUES ('GIOVA00C31B123V','Giovanni','Storti','Comico');
       
INSERT INTO VIP(CodFisc,Nome,Cognome,Professione)
        VALUES ('GIACO21F31B154Z','Giacomino','Poretti',NULL);
       
INSERT INTO CANALE_TV(CodC,Nome,Emittente,Frequenza)
        VALUES ('C1','Italia1','Mediaset',610);
       
INSERT INTO CANALE_TV(CodC,Nome,Emittente,Frequenza)
        VALUES ('C2','Rai2','RAI',482);
        
INSERT INTO CANALE_TV(CodC,Nome,Emittente,Frequenza)
        VALUES ('C3','Rai3','RAI',482);

INSERT INTO APPARIZIONE(CodFisc,Data,OraInizio,OraFine,CodC)        
        VALUES ('AAAAA00N31B751G','2006-08-12','21:00','23:00','C1');
        
INSERT INTO APPARIZIONE(CodFisc,Data,OraInizio,OraFine,CodC)        
        VALUES ('AAAAA00N31B751G','2006-08-12','19:00','20:00','C1');

INSERT INTO APPARIZIONE(CodFisc,Data,OraInizio,OraFine,CodC)        
        VALUES ('GIACO21F31B154Z','2006-08-25','21:00','23:00','C2');