.MODEL  small
.STACK
.DATA
opa DW 2043 ;UNSIGNED
opb DW 5
res DW ?
tabella DW addizione,sottrazione,moltiplicazione,divisione

.CODE
.STARTUP
XOR BX,BX
XOR DX,DX
;Calcolo scelta
MOV AH,1
INT 21H     ;INPUT va in AL!

SUB AL,'0'
CMP AL,1    ;Controllo input
JB error
CMP AL,4
JA error
;modo2
;Tabella di JMP: vettore di WORD che contiene NON valori numerici ma INDIRIZZI NEL SEGMENTO DI CODICE: LABEL A CUI SALTARE
;Saltare all'etichetta corrispondente a var
;NB valore variabile 1,2,3 <-> indici tabella 0,1,2
DEC AL
;NB2 Posizione indici tabella 0,2,4
;BX*2-> come? MUL,registro con 2,... MEGLIO shl
SHL AL,1   
MOV BL,AL
MOV AX,opa
JMP tabella[BX]     ;Tanti salti condizionati sostituiti da un unico JMP!

addizione:
ADD AX,opb
JC overflow
JMP result

sottrazione:
SUB AX,opb
JC overflow
JMP result

moltiplicazione:
MUL opb
CMP DX,0
JA overflow
JMP result

divisione:  ;W/W->DW/W
DIV opb
JMP result

result: MOV res,AX
JMP fine

error:
overflow:
fine:
.EXIT
END