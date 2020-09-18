.MODEL  small
.STACK
.DATA
var DW ?
tabella DW salto1,salto2,salto3

.CODE
.STARTUP
;Calcolo var
MOV var,2 
;modo1
;sequenza di compare

;CMP var,1
;JE salto1

;CMP var,2
;JE salto2...

;salto1:...
;JMP fine

;salto2
;JMP fine

;modo2
;Tabella di JMP: vettore di WORD che contiene NON valori numerici ma INDIRIZZI NEL SEGMENTO DI CODICE: LABEL A CUI SALTARE
;Saltare all'etichetta corrispondente a var
;NB valore variabile 1,2,3 <-> indici tabella 0,1,2
MOV BX,var
DEC BX
;NB2 Posizione indici tabella 0,2,4
;BX*2-> come? MUL,registro con 2,... MEGLIO shl
SHL BX,1
JMP tabella[BX]     ;Tanti salti condizionati sostituiti da un unico JMP!

salto1:
JMP fine
salto2:
JMP fine
salto3:
JMP fine

fine:
.EXIT
END