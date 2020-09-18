.MODEL small
.STACK
.DATA
valore DW    1,  2,  5, 10, 20, 50, 100, 200
N EQU ($-valore)/2
monete DB  100, 23, 17,  0, 79, 48, 240, 211
euro DW ?
cent DW ?
.CODE
.STARTUP
XOR SI,SI
XOR DI,DI
XOR BX,BX
XOR CX,CX
calcolo:
XOR AH,AH
MOV AL,monete[SI] ;AL Byte monete        (non si puo' fare DI*2 ovviamente)
MUL valore[DI]    ;AX LSW Monete*valore temporaneo
ADD BX,AX         ;BX LSW Monete*valore definitivo
ADC CX,DX         ;DX MSW M*v temporaneo
                  ;CX MSW M*V definitivo
JC overflow       ;gestire sempre!!
ADD DI,2
INC SI
CMP SI,N
JNZ calcolo

MOV AX,BX
MOV DX,CX
MOV BX,100
DIV BX          ;AX:DX/BX
MOV euro,AX
MOV cent,DX

overflow:
.EXIT
END