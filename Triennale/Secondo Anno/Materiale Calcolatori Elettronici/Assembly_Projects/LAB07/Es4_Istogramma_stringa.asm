CR EQU 13
NL EQU 10
.MODEL small
.STACK
.DATA
stringa DB "sasso rosso"
lung EQU $-stringa
lettere DB 26 DUP (0)
.CODE
.STARTUP
XOR BH,BH
XOR SI,SI
MOV CX,lung
MOV AH,1

scorri: MOV AL,stringa[SI]   ;Difficile se la stringa e' in memoria
CMP AL,CR 
JE stampaL
CMP AL,'a'
JB prossimo
CMP AL, 'z'               
JA prossimo
SUB AL,'a'
MOV BL,AL
INC lettere[BX] 
prossimo: INC SI
LOOP scorri

stampaL: MOV AH,2
MOV CX,26
XOR BX,BX
ciclo: PUSH CX
;MOV CL,lettere[BX]  ;CL: frequenza... ma perche'
;CMP CL,0
CMP lettere[BX],0
JE next     ;Se mai comparsa non faccio nulla
MOV DL,BL   ;BL: indice della lettera
ADD DL,'a'  ;DL: lettera
INT 21H     ;Se comparsa almeno una volta stampo lettera e poi chiamo procedura che stampi *

PUSH CX
CALL istogramma
ADD SP,2

next: INC BX
POP CX
LOOP ciclo
 
.EXIT
istogramma PROC
    PUSH BP
    MOV BP,SP
    MOV CX,[BP+4]
    PUSH AX
    MOV AH,2
    PUSH DX
    
    MOV DL,' '
    INT 21H
    
    MOV DL,'*'
    
    stampaI:INT 21H
    LOOP stampaI
    
    MOV DL,CR
    INT 21H
    MOV DL,NL
    INT 21H
    
    POP DX
    POP AX
    POP BP
    RET
istogramma ENDP
END