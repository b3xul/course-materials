PORTA EQU 80h
PORTB EQU PORTA+1
PORTC EQU PORTA+2
CONTROL EQU PORTA+3

#START=8255.exe#
.MODEL small
.STACK
.DATA
a DB ?
b DB ?
ris DB ?
.CODE
.STARTUP
MOV AL,10010010b
OUT CONTROL,AL

IN AL,PORTA
MOV a,AL
MOV BL,AL
IN AL,PORTB
MOV b,AL
XOR AL,BL
NOT AL
MOV ris,AL

MOV BL,AL
MOV CX,7
ciclo: MOV AL,CL
SHL AL,1    ;bit sulla porta=7,6,5..
SHL BL,1    ;valore del bit=1/0 a seconda del carry
ADC AL,0

OUT CONTROL,AL

DEC CX
JNS ciclo


.EXIT
END