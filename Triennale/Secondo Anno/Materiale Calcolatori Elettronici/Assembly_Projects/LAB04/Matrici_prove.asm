COLONNE EQU 3
RIGHE EQU 3
.MODEL small
.STACK
.DATA
matrice DB  1, 2, 3
        DB  4, 5, 6
        DB  7, 8, 9
        DB 10,11,12
.CODE
.STARTUP
XOR BX,BX   ;Indice righe
;media degli elementi di una matrice->somma elementi matrice
XOR AX,AX   ;Somma degli elementi
MOV CX,RIGHE

cicloEsterno:
XOR DI,DI   ;Indice colonne
PUSH CX     ;Secondo modo
MOV CX,COLONNE  ;Comoda LOOP per i cicli
;doppio ciclo: interno su colonne, esterno su righe

cicloInterno:
ADD AL,matrice[BX][DI]  ;matrice+0+0 in AL
;ADD AL,matrice[BX*COLONNE][DI] NON PUO' FUNZIONARE! Operando * non puo' lavorare su BX!
ADC AH,0    ;Sommo l'eventuale riporto ad AH!
JC overflow ;salta se non sta su un byte! In questo caso lo usiamo per evitare che superi addirittura una word!
INC DI
;Quante volte? comode costanti
LOOP cicloInterno

;INC BX         no!
;CMP BX,RIGHE

ADD BX,COLONNE
;PRIMO MODO
CMP BX,RIGHE*COLONNE
JB cicloEsterno

;SECONDO MODO
POP CX
LOOP cicloEsterno
;N.B. NON e' detto che la somma stia su un BYTE!
overflow:
.EXIT
END