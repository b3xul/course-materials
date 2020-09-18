NRIGHE EQU 4
NCOLONNE EQU 6
.MODEL small
.STACK
.DATA
matrice DB 0, 0, 0, 0, 0, 0
DB 1, 1, 0, 0, 0, 0
DB 0, 1, 1, 1, 0, 0
DB 0, 0, 0, 1, 0, 0
.CODE
.STARTUP
PUSH OFFSET matrice
SUB SP, 2
MOV DL, 1
MOV DH, 0
CALL seguiPercorso
POP AX
ADD SP, 2
.EXIT
seguiPercorso PROC
    PUSH BP
    MOV BP,SP
    PUSH BX
    PUSH SI
    PUSH AX
    PUSH DX
    
    MOV BX,[BP+6]
    XOR AH,AH
    MOV AL,DL
    MOV DL,NCOLONNE
    MUL DL
    ADD BX,AX     ;BX=RIGA
    XOR DL,DL
    
    MOV SI,DX     ;SI=COLONNA
    
    XOR DX,DX
    
    ciclo: CMP [BX][SI],0
    JZ fine
    INC DX
    
    CMP SI,NCOLONNE-1
    JZ next
    CMP [BX][SI][1],1
    JNE next
    INC SI
    JMP ciclo
    
    next: CMP BX, NCOLONNE*(NCOLONNE-1)
    JZ fine
    CMP [BX][SI][NCOLONNE],1
    JNE fine
    ADD BX,NCOLONNE
    JMP ciclo
    
    
    fine: MOV [BP+4],DX
    
    POP DX
    POP AX
    POP SI
    POP BX
    POP BP
    RET
seguiPercorso ENDP
END