DIM EQU 15
.MODEL small
.STACK
.DATA
VETT DW 2,5,16 ,12 ,34 ,7, 40000
RESULT DW ?
.CODE
.STARTUP
    MOV AX, 0       ;azzera il registro AX
    MOV CX, DIM     ;carica in CX la dimensione del vettore
    MOV DI, 0       ;azzera il registro DI
lab: ADD AX, VETT[DI]   ;somma ad AX l'i -esimo elemento di VETT
    ADD DI, 2       ;passa all'elemento successivo
    DEC CX          ;decrementa il contatore
    CMP CX, 0       ;confronta il contatore con 0
    JNZ lab         ;se diverso da 0 salta
    MOV RESULT, AX  ;altrimenti scrivi il risultato
.EXIT
END