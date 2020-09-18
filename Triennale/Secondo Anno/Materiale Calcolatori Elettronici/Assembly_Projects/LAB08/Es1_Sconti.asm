DIM EQU 5
.MODEL small
.STACK
.DATA
prezzi DW 39, 1880, 2394, 1000, 1590
scontati DW DIM DUP (?)
sconto DW 30
totsconto DW ?

.CODE
.STARTUP
CALL calcola_sconto
.EXIT

calcola_sconto PROC
    
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    
    MOV totsconto,0
    MOV BX,100
    SUB BX,sconto ;BX percentuale prezzo
     
    XOR SI,SI
    MOV CX,DIM
    ciclo: PUSH CX
    MOV AX,prezzi[SI]
    MUL BX  ;DX:AX=prezzo*percentuale prezzo
    MOV CX,100
    DIV CX  ;AX=prezzo finale/100 DX=prezzo finale%100
    
    CMP DX,50   
    JB difetto
    
    INC AX
    difetto: MOV scontati[SI],AX
    MOV CX, prezzi[SI]
    SUB CX,AX
    ADD totsconto,CX
    
    POP CX
    ADD SI,2
    LOOP ciclo
    
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
    RET            
calcola_sconto ENDP


END

