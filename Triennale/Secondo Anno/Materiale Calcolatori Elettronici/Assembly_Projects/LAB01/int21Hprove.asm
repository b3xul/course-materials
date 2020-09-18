.MODEL small
.STACK
.DATA
var1 DB 'a'
var2 DB 6 
var3 DB '6'

.CODE
.STARTUP
MOV AH,2
MOV DL,var1
INT 21H   
MOV DL,var2 ;Stampa il sesto carattere della tabella ascii
INT 21H
MOV DL,10   ;NEW LINE-> SENZA SOVRASCRIVE!
INT 21H
MOV DL,13   ;CARRIAGE RETURN
INT 21H
MOV DL,var3
INT 21H     ;Stampa 6
.EXIT                                                     

END