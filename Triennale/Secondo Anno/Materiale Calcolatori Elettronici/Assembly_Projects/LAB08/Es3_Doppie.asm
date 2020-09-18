.MODEL small
.STACK
.DATA
stringa DB "ATTESTAZIONI incredibBili"
DIMENSIONE EQU $-stringa 
DIMENSIONE_AGGIORNATA DW ?
stringa_res DB DIMENSIONE DUP (?)

.CODE
.STARTUP
lea ax, stringa
push ax ;BP+8    
mov ax, DIMENSIONE
push ax ;BP+6
sub sp, 2 ;BP+4
call converti
pop ax
mov DIMENSIONE_AGGIORNATA, ax
add sp, 4
.EXIT
converti PROC
    PUSH BP ;BP+2 
    MOV BP,SP 
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    PUSH DI
    
    MOV SI,[BP+8]
    MOV CX,[BP+6] 
    
    XOR BX,BX
    XOR DI,DI
    ciclo: MOV AL,BYTE PTR [SI]
        CMP AL,BYTE PTR [SI+1]
        JE prossima
        INC BX  ;Dimensione stringa risultante++
        MOV stringa_res[DI],AL
        INC DI
        ;MOV AL,BYTE PTR [SI+2]
        ;MOV BYTE PTR [SI+1],AL
        
    prossima:
    INC SI
    LOOP ciclo 
    
    MOV [BP+4],BX    
    POP BP
    POP DI
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
    RET
converti ENDP
END