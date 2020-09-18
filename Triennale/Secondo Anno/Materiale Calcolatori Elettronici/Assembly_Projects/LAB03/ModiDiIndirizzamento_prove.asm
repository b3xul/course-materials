.MODEL small
.STACK
.DATA
;vet DB 6,7,45,75,75,23,13,86,43
vet DB  6, 7,45,
    DB 75,75,23,
    DB 13,86,43
    DB  4,27, 9 
;Stessa cosa per la CPU ma io la vedo come matrice!
.CODE
.STARTUP
;DIRECT ADDRESSING
MOV AL,vet      ;6
MOV AL,vet[7]   ;86
MOV AL,vet+7    ;86

;DIRECT INDEXED ADDRESSING
MOV BX,7
MOV AL,vett[BX] ;86
;BX,DI,SI,(BP)

;BASE INDEXED ADDRESSING
MOV BX,4
MOV DI,3
MOV AL,vett[BX][DI] ;86
;BX Riga
;DI Colonna
MOV BX,2*3  ;Terza riga (numeroriga*dimensionecolonna)
MOV DI,1    ;Seconda colonna
MOV AL, vet[BX][DI]

;
MOV BX,OFFSET vet
MOV AL,[BX]

;BASE RELATIVE ADDRESSING
MOV AL,[BX]+7   ;[BX+7],7[BX]

.EXIT
END