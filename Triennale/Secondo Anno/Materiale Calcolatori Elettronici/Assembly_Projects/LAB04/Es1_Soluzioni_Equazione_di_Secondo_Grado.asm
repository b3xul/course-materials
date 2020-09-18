.MODEL small
.STACK
.DATA
a DW 2
b DW -4
c DW 1
.CODE
.STARTUP
MOV AX,b
IMUL b
JC overflow ;Gestire overflow!!!
MOV BX,AX
MOV AX,4
IMUL a
JC overflow
IMUL c
;CMP DX,0FFh 
;JNE overflow
JC overflow
SUB BX,AX
JO overflow     ;+65535 e' overflow!  
;JL radici_complesse     Non si puo' controllare su BX-AX perche' aggiorna il valore di BX NO
                        ;ADD/SUB aggiornano TUTTI i flag di condizione, quindi in questo caso CMP e' superflua perche' il suo unico scopo sarebbe quello di aggiornare i flag!
                        ;Oppure confronta il BX operando invece del BX risultato? NO, non confronta davvero AX e BX, guarda solo il contenuto di alcuni flag
;CMP BX,AX
JL radici_complesse     ;Salta se flag segno!=flag overflow
JZ radici_coincidenti


radici_reali: MOV CX,1
JMP fine

radici_coincidenti: MOV CX,2
JMP fine

radici_complesse: MOV CX,3  

fine:
.EXIT
END