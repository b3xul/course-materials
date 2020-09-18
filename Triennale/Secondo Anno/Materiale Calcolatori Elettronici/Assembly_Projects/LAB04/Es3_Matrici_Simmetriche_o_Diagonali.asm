DIM EQU 4
.MODEL small
.STACK
.DATA
matrice DW  1,4,5,6
        DW  4,2,7,8
        DW  5,7,3,9
        DW  6,8,9,4   
.CODE
.STARTUP
MOV DL,2    ;Switch

MOV SI,1    ;SI indice colonne
XOR BX,BX   ;BX indice righe
MOV CX,DIM-1;Conteggio
              
cicloEsterno:
PUSH CX     ;ripeto il ciclo esterno DIM-1 volte 

check:      ;ripeto il ciclo interno a ogni giro una volta in meno
MOV AX,matrice[BX][SI]  ;primo valore da confrontare 
PUSH AX
MOV AX,DIM  ;Calcolare offset di BX:BX->BX+(DIM-CX)*DIM
            ;Passo da (0,1) a (1,1), (0,2) a (2,0)
SUB AX,CX
SUB SI,AX   ;Sono sulla colonna giusta
MUL AX,DIM
ADD BX,AX   ;Sono sulla riga giusta

MOV DX,matrice[BX][SI]
CMP AX,DX
...
ADD SI,2
LOOP check

ADD BX,DIM*2
POP CX
LOOP cicloEsterno

stampa:
MOV AH,2
POP DL
INT 21H

.EXIT
END