DIM EQU 20
.MODEL small
.STACK
.DATA
VETT DB DIM DUP(?)
.CODE
.STARTUP
    MOV CX, DIM ;carica in CX la dimensione del vettore
    MOV DI, 0   ;azzera il registro DI
    MOV AH, 1   ;predisposizione del registro AH
lab1: INT 21H  ;lettura di un carattere
    MOV VETT[DI], AL ;memorizzaz .del carattere letto
    INC DI      ;passa all’elemento successivo
    DEC CX      ;decrementa il contatore
    CMP CX, 0   ;confronta il contatore con 0
    JNZ lab1   ;se diverso da 0salta
    MOV CX, DIM
    MOV AH, 2   ;predisposizione del registro AH
lab2: DEC DI   ;passa all’elemento precedente
    MOV DL, VETT[DI]
    INT 21H    ;visualizzazione di un carattere
    DEC CX      ;decrementa il contatore
    CMP CX, 0   ;confronta il contatore con 0
    JNZ lab2   ;se diverso da 0salta
.EXIT
END