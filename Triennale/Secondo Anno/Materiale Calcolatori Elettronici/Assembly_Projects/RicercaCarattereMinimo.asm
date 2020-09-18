.MODEL small 

.STACK
DIM EQU 20 
       
.DATA
TABLE DB DIM DUP(?)

.CODE
.STARTUP
MOV CX, DIM
LEA DI, TABLE
MOV AH, 1       ; lettura
lab1: INT 21H
MOV [DI], AL
INC DI
DEC CX
CMP CX, 0
JNE lab1 ; ripeti per 20 volte
MOV CL, 0FFH ; inizializzazione di CL
MOV DI, 0
ciclo: CMP CL, TABLE[DI] ; confronta con il minimo attuale
JB dopo
MOV CL, TABLE[DI] ; memorizza il nuovo minimo
dopo: INC DI
CMP DI, DIM
JB ciclo
output: MOV DL, CL
MOV AH, 2
INT 21H ; visualizzazione
.EXIT
END