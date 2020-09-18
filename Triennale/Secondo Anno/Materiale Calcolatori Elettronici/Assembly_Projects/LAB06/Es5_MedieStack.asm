.MODEL small 
.STACK 
.DATA 
vett1 DW 1,2,3,5,10
vett2 DW 3,5,6,8,16
DIM EQU $-vett2
medie DW DIM DUP (?)

.CODE
.STARTUP
XOR SI,SI
MOV CX,DIM
cicla: PUSH vett1[SI]
    PUSH vett2[SI]
    ;PUSH medie[SI]
    SUB SP,2    ;RISERVO SPAZIO PER VALORE DI RITORNO
    CALL media
    POP medie[SI]
    ADD SP,4    ;POP senza salvataggio dei 2 parametri
    ADD SI,2
    LOOP cicla

.EXIT
media PROC
    PUSH BP
    PUSH AX
    MOV BP,SP
    MOV AX,[BP+10]  ;vett1        
    ADD AX,[BP+8]   ;+vett2
    SHR AX,1        ;/2
    MOV [BP+6],AX   
    POP AX
    POP BP
    RET                        
media ENDP

END