XOR BX,BX
MOV CX,DIM

cicloEsterno:
XOR SI,SI   
PUSH CX     
MOV CX,DIM

cicloInterno:
INC DI
LOOP cicloInterno

ADD BX,DIM
POP CX
LOOP cicloEsterno