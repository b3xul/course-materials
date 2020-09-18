LF EQU 10   ;LINE FEED
CR EQU 13 
.MODEL small
.STACK
.DATA
opA DB ?
opB DB ?
ris DB ?
.CODE
.STARTUP
XOR DI,DI
MOV CX,2    ;2 numeri

ciclo_esterno:
MOV AH,1
PUSH CX
MOV CX,8    ;Massimo numero di bit
XOR BL,BL

lettura:
INT 21H
CMP AL,CR   
JE fine_numero

CMP AL,'0'    ;controllo input
JB error
CMP AL,'1'
JA error                                                                                                                                                            

SUB AL,'0'
            ;Converto man mano BX da binario a unsigned (ma in realta' visto in binario (shifto il bit ricevuto e aggiungo quello nuovo=moltiplico per 2 e aggiungo)
SHL BL,1    ;BX*2
ADD BL,AL

LOOP lettura

fine_numero: MOV opA[DI],BL
INC DI

MOV AH,2
MOV DX,LF
INT 21H
MOV DX,CR
INT 21H

POP CX
LOOP ciclo_esterno

;Espressione logica C = NOT(A AND (NOT(B))) OR (A XOR B)
;XOR AH,AH  non servono perche' tanto lavoro solo con 1 byte
;XOR BH,BH
;XOR DH,DH

MOV AL,opA 
MOV BL,opB
NOT BL
AND AL,BL   ;Risultato in AX
NOT AL
MOV DL,AL   ;Prima parte dell'espressione in DX
MOV AL,opA
NOT BL      ;Ritorno al valore di opB (piu' efficiente di MOV BX,opB)
XOR AL,BL   ;Risultato in AX, seconda parte dell'espressione
OR AL,DL

MOV ris,AL



error:
.EXIT
END