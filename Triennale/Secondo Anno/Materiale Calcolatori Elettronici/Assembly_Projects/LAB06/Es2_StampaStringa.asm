CR EQU 13
NL EQU 10

.MODEL small
.STACK
.DATA
welcome DB 'Hello world!',CR,NL 
lung EQU $-welcome
.CODE
.STARTUP
LEA SI,welcome
MOV CX,lung
CALL stampa

.EXIT      
stampa PROC
    PUSH SI
    PUSH CX
    PUSH AX
    PUSH DX     ;Ah gia'!

    MOV AH,2
    
    cicla:MOV DL,[SI]
    INT 21H
    INC SI
    LOOP cicla
    
    POP DX
    POP AX
    POP CX
    POP SI
    RET
stampa ENDP

END