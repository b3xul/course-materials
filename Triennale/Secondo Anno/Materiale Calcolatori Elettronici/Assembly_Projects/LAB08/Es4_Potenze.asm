.MODEL small
.STACK
.DATA
result DD ?
.CODE
.STARTUP
PUSH 3 ; base
PUSH 12 ; esponente
SUB SP, 4
CALL potenza
POP AX
POP DX
ADD SP, 4
mov result, AX  ;LSB
mov result+2, DX  ;MSB
.EXIT
potenza PROC
    PUSH BP
    MOV BP,SP
    
    PUSH AX
    PUSH CX
    
    MOV AX,WORD PTR [BP+10] ;base 
    MOV CX,WORD PTR [BP+8]  ;esponente
    CMP CX,0
    JZ uno
    CMP CH,0
    JA overflow ;2^257>DD
    
    ciclo: MUL WORD PTR [BP+10]
    JC double
    LOOP ciclo
              
    double: CMP CX,0
    JZ finale
    
    
    finale: MOV [BP+4],AX
    MOV [BP+6],DX
    JMP fine   
    
    uno: MOV [BP+4],1
    MOV [BP+6],0
    JMP fine
    
    overflow:MOV [BP+4], 0FFFFh    ;LSB
    MOV [BP+6], 0FFFFh    ;MSB    
    
    fine:
    POP CX
    POP AX
    POP BP
    RET
potenza ENDP
END