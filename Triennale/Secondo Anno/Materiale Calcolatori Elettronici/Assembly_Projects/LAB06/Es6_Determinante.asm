.MODEL small
.STACK
.DATA
matrice DW 1, 2,-5
        DW 0,-3, 6
        DW 1, 0, 1 
res DD ?
.CODE
.STARTUP
;1*Det1
    PUSH matrice[6][2]  ;BP+16
    PUSH matrice[6][4]  ;BP+14
    PUSH matrice[12][2] ;BP+12
    PUSH matrice[12][4] ;BP+10
    SUB SP,2    ;Risultato determinante:BP+8
    CALL determinante
    POP AX      ;AD-BC
ADD SP,8        ;Svuoto stack    
IMUL matrice[0][0]          
;prima parte del risultato in DX:AX
MOV res,AX
MOV res+2,DX


;-2*Det2
    PUSH matrice[6][0]  ;BP+16
    PUSH matrice[6][4]  ;BP+14
    PUSH matrice[12][0] ;BP+12
    PUSH matrice[12][4] ;BP+10
    SUB SP,2    ;Risultato determinante:BP+8
    CALL determinante
POP AX      ;AD-BC
ADD SP,8        ;Svuoto stack
IMUL matrice[0][2]          ;1*Det1

SUB res+2,DX
SBB res,AX

;-5*Det3
    PUSH matrice[6][0]  ;BP+16
    PUSH matrice[6][2]  ;BP+14
    PUSH matrice[12][0] ;BP+12
    PUSH matrice[12][2] ;BP+10
    SUB SP,2    ;Risultato determinante:BP+8
    CALL determinante
POP AX      ;AD-BC
ADD SP,8        ;Svuoto stack
IMUL matrice[0][4]             
;prima parte del risultato in DX:AX
ADD res,AX
ADC res+2,DX

.EXIT
determinante PROC
    PUSH BP             ;BP+6
    PUSH AX             ;BP+4
    PUSH BX             ;BP+2
    MOV BP,SP
    MOV AX,[BP+16]
    IMUL [BP+10]
    MOV BX,AX       ;AD
    MOV AX,[BP+14]
    IMUL [BP+12]    ;BC
    SUB BX,AX       ;AD-BC
    MOV [BP+8],BX   ;Res
    
    POP BX
    POP AX
    POP BP
    RET
determinante ENDP
END