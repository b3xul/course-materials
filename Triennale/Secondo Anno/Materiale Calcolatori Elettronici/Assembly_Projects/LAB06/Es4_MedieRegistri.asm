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
cicla: MOV AX,vett1[SI]
    MOV BX,vett2[SI]
    CALL media
    MOV medie[SI],AX
    ADD SI,2
    LOOP cicla

.EXIT
media PROC        
    ADD AX,BX
    SHR AX,1
    
    RET                        
media ENDP

END