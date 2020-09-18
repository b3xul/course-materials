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
MOV BX,AX
MOV AX,4
IMUL a
IMUL c
SUB BX,AX
;JL radici_complesse     Non si puo' controllare su BX-AX perche' aggiorna il valore di BX NO
                        ;ADD/SUB aggiornano TUTTI i flag di condizione, quindi in questo caso CMP e' superflua perche' il suo unico scopo sarebbe quello di aggiornare i flag!
                        ;Oppure confronta il BX operando invece del BX risultato? NO, non confronta davvero AX e BX, guarda solo il contenuto di alcuni flag
;CMP BX,AX
JL radici_complesse     ;Salta se flag segno!=flag overflow
radici_reali: MOV CX,1
JMP fine

radici_complesse: MOV CX,2  

fine:
.EXIT
END