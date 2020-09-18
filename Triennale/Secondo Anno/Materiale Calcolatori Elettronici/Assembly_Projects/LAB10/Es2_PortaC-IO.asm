PORTA EQU 80h
PORTB EQU PORTA+1
PORTC EQU PORTA+2
CONTROL EQU PORTA+3

#START=8255.EXE#

.MODEL small
.STACK
.DATA
.CODE
.STARTUP
MOV DX,CONTROL
MOV AL,10010000b
OUT DX,AL

ciclo: IN AL,PORTA
CMP AL,'0'
JB next
CMP AL,'7'
JA next
SUB AL,'0'
MOV CL,AL   ;Numero letto
MOV AH,AL
SHL AH,1

IN AL,PORTC ;Si dovrebbe potere anche se programmata in output
ADD CL,1    ;Numero di posizioni da ruotare per ottenere il valore in quel bit
RCR AL,CL
JC zero

ADD AH,1

zero:MOV BL,9
SUB BL,CL
MOV CL,BL   
RCR AL,CL
MOV AL,AH
OUT CONTROL,AL 
next:JMP ciclo
 
.EXIT
END