DIM EQU 5
.MODEL small
.STACK
.DATA
vett DB 3,6,7,13,25
res DB DIM DUP (1)  ;Tutti primi finche' non si trova un divisore
.CODE
.STARTUP
XOR SI,SI
MOV CX,DIM

ciclo_esterno: PUSH CX
XOR CH,CH
MOV CL,vett[SI] ;Salvo il numero in CL per non doverlo andare a prendere ogni volta dalla memoria
MOV DL,CL   
SHR DL,1    ;Massimo dividendo (Massimo dividendo di un byte sara' la radice di quel byte, che quindi sta in un byte)   
MOV BL,2    ;dividendo attuale 

ciclo: XOR AX,AX
MOV AL,CL  ;cicla sugli elementi del vettore
DIV BL   ;Cicla su tutti i dividendi possibili da 2 a num/2      
            ;AL=AX/BL   quoziente
CMP AH,0    ;AH resto
JZ non_primo

INC BL
CMP BL,DL
JA successivo 
JMP ciclo


non_primo: MOV res[SI],0
successivo:INC SI   ;passa all'elemento del vettore successivo
POP CX
LOOP ciclo_esterno
         
.EXIT
END