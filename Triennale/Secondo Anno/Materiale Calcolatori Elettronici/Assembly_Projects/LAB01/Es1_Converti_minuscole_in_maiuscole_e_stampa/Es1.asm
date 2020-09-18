.MODEL small
.STACK
.DATA
var1 DB 'a'
var2 DB 's' 
var3 DB 'm'
;a=97
;A=65

.CODE
.STARTUP
MOV AH,2    ;output
;SUB var1,'a'-'A'    ;l'assemblatore puo' lavorare solo su costanti, non sa cosa c'e' in var1!
;dato che mi serve solo l'offset, quello posso ottenerlo avendo solo il valore che lui sa
;MOV DL,var1
;N.B. SEMPRE PIU' EFFICIENTE LAVORARE SUI REGISTRI!
MOV DL, var1    ;PRIMA
SUB DL, 'a'-'A' ;DOPO
INT 21H
   
MOV DL,10   ;NEW LINE
INT 21H
MOV DL,13   ;CARRIAGE RETURN
INT 21H

SUB var2,'a'-'A'
MOV DL,var2
INT 21H     

MOV DL,10   ;NEW LINE
INT 21H
MOV DL,13   ;CARRIAGE RETURN
INT 21H

SUB var3,'a'-'A'
MOV DL,var3
INT 21H

.EXIT                                                     

END