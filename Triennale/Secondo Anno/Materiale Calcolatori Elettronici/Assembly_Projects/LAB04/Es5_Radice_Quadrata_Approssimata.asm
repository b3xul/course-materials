.MODEL small
.STACK
.DATA
numero dw 48
radice dw ?
.CODE
.STARTUP
MOV AX,numero
MOV BX,1   ;Numero dispari
XOR CX,CX   ;Radice 

ciclo:  INC CX 
SUB AX,BX
JS fine:      ;<0 
ADD BX,2
JMP ciclo

fine: MOV radice,CX  
.EXIT
END