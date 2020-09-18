DIMX EQU 10
DIMY EQU 8
.model small
.stack
.data
matrice db 0, 0, 1, 0, 0, 1, 1, 0, 1, 0
db 0, 1, 1, 0, 0, 0, 1, 0, 1, 1
db 1, 1, 0, 0, 1, 0 ,1, 0, 1, 0
db 1, 0, 1, 0, 0, 1, 1, 0, 1, 0
db 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
db 1, 1, 1, 0, 0, 1, 1, 1, 1, 1
db 1, 1, 1, 0, 1, 1, 1, 1, 1, 0
db 1, 1, 0, 1, 1, 1, 1, 0, 1, 0
.code
.startup
PUSH OFFSET matrice
PUSH DIMX
PUSH DIMY
PUSH 0
CALL valuta
POP AX
ADD SP, 6
.exit

valuta PROC
    PUSH BP
    MOV BP,SP 
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    
    MOV BX,[BP+10]      ;MAT
    XOR SI,SI
        
    MOV CX,[BP+8]       ;COLONNE
    
    MOV DX,[BP+6]      ;RIGHE
             
    PUSH DX
    MOV AX,DX
    DEC AX
    MUL CX              ;AX=COLONNE*(RIGHE-1)
    CMP DX,0
    JNZ fine
    POP DX
    
    ciclo: CMP [BX][SI],0
    JZ next
    CMP BX,AX
    JE aggiungi
    ADD BX,CX
    JMP ciclo           
               
    aggiungi: INC [BP+4]
    
    next: MOV BX,[BP+10]
    INC SI
    CMP SI,CX
    JNE ciclo
    
    fine:
    POP BP
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
    RET
valuta ENDP
END