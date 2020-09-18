DIM EQU 5
.MODEL small
.STACK
.DATA
matrice DW  1,4,5,6,7
        DW  4,2,8,6,4
        DW  5,8,3,5,9
        DW  6,6,2,4,4
        DW  7,4,9,4,5

risultato DW 2   
.CODE
.STARTUP                              
XOR BX,BX   ;BX indice righe
MOV SI,2    ;SI indice colonne
MOV CX,DIM-1;Conteggio
              
cicloEsterno:
PUSH CX     ;ripeto il ciclo esterno DIM-1 volte 
PUSH BX
PUSH SI
MOV CX,DIM

cicloInterno:MOV AX,matrice[BX][SI]    ;ripeto il ciclo interno a ogni giro una volta in meno
PUSH BX
PUSH SI
PUSH AX             ;PRIMO VALORE-> parametro
CALL confronto

POP SI
ADD SI,2
POP BX
CMP SI,DIM*2
JNZ cicloInterno

POP SI
ADD SI,2
POP BX
ADD BX,DIM*2
POP CX
LOOP cicloEsterno
JMP stampa

non_simmetrica: MOV risultato,0
 
stampa: PROC
MOV AH,2
MOV DX,risultato
ADD DX,'0'
INT 21H
JMP fine

confronto PROC
PUSH BP ;In questo caso non mi serve ma utile per le annidate!
MOV BP,SP
MOV AX,BP+4     ;BP+2=BP vecchio!<->in AX metto PRIMO VALORE

calcoloBX: MOV AX,CX           ;BX->DIM*SI (parte da 2, per fargli fare una riga basta fare *SI)
MUL SI
CMP DX,0            ;Gestione matrice con DIM>2^16
JNZ troppo_grande
PUSH AX             ;NUOVO BX

calcoloSI: MOV AX,BX           ;SI->BX/DIM
;MUL AX,2
;SHL AX,1
JC troppo_grande
DIV CX

POP BX 
MOV SI,AX           

MOV AX,BP+4             ;PRIMO VALORE (AX parametro passato)
MOV DX,matrice[BX][SI]  ;SECONDO VALORE
CMP AX,DX
JNZ non_simmetrica

PUSH AX
CALL check
PUSH DX
CALL check

POP BP
RET 2   ;Non mi serve piu' AX passato come parametro
ENDP

check PROC
;POP AX non ancora!
PUSH BP ;In questo caso non mi serve ma utile per le annidate!
MOV BP,SP
MOV AX,BP+4     ;BP+2=BP vecchio!
CMP AX,0    
JNZ simmetrica:
JZ diagonale:

simmetrica: MOV risultato,1 ;Uso variabile globale, in questo caso ci sta
diagonale:  POP BP ;Non mi serve piu' BP, lo sovrascrivo con il vecchio valore->riprendo il vecchio valore-> salvato
RET 2   ;Scarto l'AX/BX passato in precedenza come parametro (non lo uso come valore di ritorno e tanto non l'ho modificato)
ENDP

troppo_grande:
fine:
.EXIT
END