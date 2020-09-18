DIM EQU 7
.MODEL small
.STACK
.DATA
VETT DW 423,3191,23,11,-412,3,9 
.CODE
.STARTUP
MOV CX,DIM
;MOV SI,0    
XOR SI,SI    ;N.B. per ogni bit darebbe vero solo se uno e' vero e l'altro e' falso,
             ;ma avendo entrambi i bit sicuramente lo stesso valore dara' sempre falso->azzera!
ciclo1: PUSH VETT[SI]
DEC CX
INC SI
INC SI
CMP CX,0
JNZ ciclo1  ;N.B. INC e DEC aggiornano lo zero flag!
 
MOV SI,0            ;Cosi' funziona senza problemi
ciclo2:POP VETT[SI] ;con DIM*2-SI no->studiarsi indirizzamenti
INC CX
INC SI
INC SI
CMP CX,DIM
JNZ ciclo2

.EXIT
END
