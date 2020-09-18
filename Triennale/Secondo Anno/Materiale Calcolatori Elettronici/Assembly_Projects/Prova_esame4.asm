PORTA EQU 80h
PORTB EQU PORTA+1
PORTC EQU PORTA+2
CONTROL EQU PORTA+3

#START=8255.exe#

.MODEL small
.STACK
.DATA
.CODE
.STARTUP
MOV AL,10010000b
OUT CONTROL,AL

MOV AL, 5
PUSH AX
PUSH AX ; spazio per valore di ritorno
CALL leggi_t
POP AX ; AL contiene lettura
ADD SP,2 ; ripristino del valore di SP
.EXIT
leggi_t PROC
    PUSH BP
    MOV BP,SP
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    
    MOV [BP+4],0
    MOV CX,[BP+6]
    XOR DH,DH
    XOR BH,BH
    MOV BL,-128
    MOV DL,127
    ciclo:IN AL,PORTA
    CMP AL,BL
    JNG min
    MOV BL,AL
    
    min:CMP AL,DL
    JNL next
    MOV DL,AL
    
    next: ADD [BP+4],AL
    JO overflow
    LOOP ciclo
    
    MOV AX,[BP+4]
    SUB AX,BX
    SUB AX,DX
    MOV CX,[BP+6]
    CBW
    IDIV CL
    CBW
    MOV [BP+4],AX
    
    overflow:
    POP DX
    POP CX
    POP BX
    POP AX
    POP BP
    RET
leggi_t ENDP
END