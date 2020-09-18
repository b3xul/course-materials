NUM_RIGHE EQU 4
NUM_COLONNE EQU 4
.MODEL small
.STACK
.DATA
riga DW 1,2,3,4
colonna DW 5,6,7,8
MATRICE DW NUM_RIGHE*NUM_COLONNE DUP (?)    ;MOLTO IMPORTANTI LE PARENTESI
.CODE
.STARTUP
XOR BX,BX   ;Indice righe matrice
XOR DI,DI   ;Indice vettore riga
XOR SI,SI   ;Indice colonne matrice e vettore colonna
ciclo:MOV AX,riga[DI]           ;Ciclo colonne               
MUL colonna[SI]
MOV MATRICE[BX][SI],AX
ADD SI,2
CMP SI,NUM_COLONNE*2
JNZ ciclo
XOR SI,SI                       ;Ciclo righe
ADD DI,2
ADD BX,NUM_COLONNE*2
CMP BX,NUM_RIGHE*NUM_COLONNE*2  ;BX indice di riga va da zero a numrighe-1
JNZ ciclo 
.EXIT
END