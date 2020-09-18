LUNG EQU 6
.MODEL small
.STACK
.DATA
anni dw 1945, 2008, 1800, 2006, 1748, 1600
ris db LUNG DUP (?)
.code
.startup
lea si, anni
lea di, ris
mov bx, lung
call bisestile
.EXIT
bisestile PROC
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    MOV CX,BX
    MOV BL,100
    
    ciclo:PUSH CX
    MOV AX,[SI]
    DIV BL
    CMP AH,0
    JNE non_divisibile_100
    
    divisibile_100: XOR DX,DX
    MOV AX,[SI]
    MOV CX,400
    DIV CX
    CMP DX,0
    JE Rbisestile
    JNE Rnon_bisestile
    
    non_divisibile_100: MOV AX,[SI]
    ;ROR AX,1
    ;JC non_bisestile
    ;ROR AX,1
    ;JC non_bisestile
    ;Ancora piu' bello per testare se un numero e' divisibile per 4
    ;se e' presente un 1 negli ultimi 2 bit non e' divisibile
    TEST AX,3
    JZ Rbisestile 
    
    Rnon_bisestile: MOV [DI],0
    JMP next
    
    Rbisestile: MOV [DI],1
    ;JMP next
    
    next: ADD SI,2
    INC DI
    POP CX
    LOOP ciclo
    RET
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
bisestile ENDP    

END
