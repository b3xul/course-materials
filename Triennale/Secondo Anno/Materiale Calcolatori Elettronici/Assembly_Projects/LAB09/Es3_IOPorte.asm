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
MOV AL,10011001b
OUT CONTROL,AL

ciclo: IN AL,PORTC
TEST AL,1
JNZ ripeti
IN AL,PORTC
TEST AL,1
JZ ripeti

CALL maiusc

ripeti: JMP ciclo

.EXIT

maiusc PROC
    PUSH AX
    
    IN AL,PORTA
    CMP AL,'a'
    JB fine
    CMP AL,'z'
    JA fine

    SUB AL,'a'-'A'
    OUT PORTB,AL
    
   
    fine:
    POP AX
    RET
maiusc ENDP
END