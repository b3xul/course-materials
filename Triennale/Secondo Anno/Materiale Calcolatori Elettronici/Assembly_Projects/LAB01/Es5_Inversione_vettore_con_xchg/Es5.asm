DIM EQU 7
.MODEL small
.STACK
.DATA
VETT DW 423,3191,23,11,-412,3,9
.CODE
.STARTUP
MOV CX,DIM/2
;MOV SI,0
MOV DI,(DIM-1)*2    
XOR SI,SI    ;N.B. per ogni bit darebbe vero solo se uno e' vero e l'altro e' falso,
             ;ma avendo entrambi i bit sicuramente lo stesso valore dara' sempre falso->azzera!
ciclo: MOV AX,VETT[SI]
XCHG AX,VETT[DI]
MOV VETT[SI],AX
DEC CX
ADD SI,2
SUB DI,2
CMP CX,0
JNZ ciclo  ;N.B. INC e DEC aggiornano lo zero flag!

.EXIT
END
