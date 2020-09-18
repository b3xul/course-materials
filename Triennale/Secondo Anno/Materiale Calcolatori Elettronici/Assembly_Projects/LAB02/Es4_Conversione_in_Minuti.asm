.MODEL small
.STACK
.DATA
Minuti DB 200   ;UNSIGNED
Ore DB 135
Giorni DB 40
risultato DW ?  ;Con un solo valore NO parentesi
.CODE
.STARTUP
;MOV AX,WORD PTR Minuti  Word PTR per azzerare AH NO!! Legge anche i BYTE DOPO!
XOR AH,AH
MOV AL,Minuti
MOV CX,AX    ;Minuti

MOV AL,Ore          ;No word PTR perche' AH gia' azzerato e non mi serve
MOV BL,60
MUL BL              ;Converti ore in minuti
ADD CX,AX    ;Aggiungi

XOR AH,AH
MOV AL,Giorni  ;Word PTR perche' mi serve lavorare su word
MOV BX,1440         ;60*24
MUL BX
JC overflow         ;Salta se il risultato non sta in una word
ADD CX,AX    ;Aggiungi
MOV risultato,CX
JC overflow
JMP fine

overflow:MOV risultato,0FFFFh

fine:
.EXIT
END
