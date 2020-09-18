.MODEL small
.STACK
.DATA
var DB 14
var2 DB -14
ris DB ?
.CODE
.STARTUP
;Divido var/3
;Caso 1: BINARIO PURO (UNSIGNED)
MOV AL,var ;E' un BYTE
MOV AH,0
;DIV 3 NO! DIV non accetta valori immediati
MOV CL,3
DIV CL  ;Piu' pulito e veloce scrivere in un registro
MOV ris,AL  ;AL=QUOZIENTE(=4)
            ;AH=RESTO(=2)  
;Caso 2: CA2 (SIGNED)
MOV AL,var2 
;MOV AH,0   NO!
CBW     ;Copia il primo bit di AL in tutti i bit di AH:
        ;Se AL e' positivo (MSB di AL=0) in AL tutti zeri
        ;Se AL e' negativo (MSB di AL=1) in AL tutti uno
MOV CL,3
;DIV CL     NO! Prova a fare la divisione in UNSIGNED->mi da un OVERFLOW!  

IDIV CL
MOV ris,AL

            
.EXIT
END