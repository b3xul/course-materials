DIM EQU 8   

.MODEL small
.STACK
.DATA       ;
vet1 DB 00101101b
     DB 01000010b 
     DB 01001011b
     DB 10000001b
     DB 01100011b
     DB 11000000b
     DB 11111111b
     DB 00001011b
            ;
vet2 DW DIM DUP (?)
ind1 DB 0
ind2 DB 0
flag DB 0
temp DB 0
.CODE
.STARTUP
CALL extract
.EXIT

PROC extract
    
    MOV CX,8
    
    
    ciclo:MOV BX,ind1
    MOV AL,vet1[BX]
    MOV temp,AL
    SHR AL,4
    MOV BX,ind2
    MOV vet2[BX],AL
    
    ;ciclo:MOV AL,temp da qui alla fine no
    CMP flag,0
    JZ primo
    
    
    primo: OR flag,1
    SHL AL,4
    MOV BX,ind1
    MOV AL,vet1[BX]
    MOV temp,AL
    SHL AL,4
    INC ind1
    MOV BX,ind2 
    MOV vet2[BX],AH
    INC ind2
    
    JMP next
    
    msb:OR flag,1
    MOV BX,ind1
    MOV AL,vet1[BX]
    MOV temp,AL
    INC ind1
    SHL AL,4
    MOV BX,ind2
    MOV vet2[BX],AL
    INC ind2
    
    next:DEC CX
    CMP CX,0
    ;JNZ ciclo
    
    fine:
    RET
ENDP extract 
END