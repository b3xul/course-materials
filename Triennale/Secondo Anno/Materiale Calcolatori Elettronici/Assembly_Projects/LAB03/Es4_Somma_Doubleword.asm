.MODEL small
.STACK
.DATA
vett DB -5,-45,-96,-128
lung EQU $-vett 
addendo DD 69000
risultato DD ?
.CODE
.STARTUP
XOR AX,AX
XOR BX,BX
XOR SI,SI
MOV CX,lung

Somma_vett:
MOV AL,vett[SI]     ;Prima passo in AL i valori di vett per trasformarli in word e poi sommo in BX
CBW 
ADD BX,AX
INC SI
LOOP Somma_vett

MOV AX,BX
CWD
ADD AX,word ptr addendo
ADC DX,word ptr addendo+2

MOV risultato,AX
MOV risultato+2,DX

.EXIT
END