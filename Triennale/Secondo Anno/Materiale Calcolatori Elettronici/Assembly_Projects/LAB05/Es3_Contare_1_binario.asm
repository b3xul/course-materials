.MODEL small
.STACK
.DATA
var DB 011001101b
res DB ?
.CODE
.STARTUP
MOV AL,var
MOV CX,8
XOR DX,DX

ciclo: SHL AL,1
ADC DL,0
LOOP ciclo

MOV res,DL
.EXIT
END