.MODEL small 
.STACK 
.DATA 
vett1 DW 1,2,3,5,10
vett2 DW 3,5,6,8,16
DIM EQU $-vett2
medie DW DIM DUP (?)

.CODE
.STARTUP
CALL media

.EXIT
media PROC
    PUSH SI
    PUSH AX
    PUSH CX
    
    XOR SI,SI
    MOV CX,DIM
    cicla: MOV AX,vett1[SI]
    ADD AX,vett2[SI]
    SHR AX,1
    MOV medie[SI],AX
    ADD SI,2
    LOOP cicla
    
    POP CX
    POP AX
    POP SI
    RET                        
media ENDP

END
;ciclo esterno che chiama solo la  media tra due numeri