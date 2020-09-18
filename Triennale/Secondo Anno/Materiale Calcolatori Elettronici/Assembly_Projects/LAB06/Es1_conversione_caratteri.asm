DIST EQU 'a'-'A'
.MODEL small 
.STACK 
.DATA 
parola DB 'caratteri'
lung EQU $-parola

.CODE
.STARTUP
XOR SI,SI
XOR AH,AH
MOV CX,lung
ciclo:  MOV AL,parola[SI]
CALL maiuscola
MOV parola[SI],AL
INC SI
LOOP ciclo

.EXIT
maiuscola PROC
    SUB AL,DIST
    ;Oppure ADD 'A', SUB 'a'
    RET
maiuscola ENDP

END