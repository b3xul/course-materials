DIM EQU 8
.MODEL small
.STACK
.DATA
address DD 82C0051AH, 0C0A80A01H, 4A7D276AH, 0D5FE1150H
        DD 0C7EF88C8H, 82C0B621H, 82C0A4F5H, 0ADC01874H
mask DD 0FFFF0000H
.CODE
.STARTUP
push 82C0h; parte alta di indirizzo di riferimento
push 0B685h; parte bassa di indirizzo di riferimento
lea AX, address
push AX
lea AX, mask
push AX
sub SP, 2; spazio riservato per risultato
call filtro
pop AX; prelevamento risultato da stack
add SP, 8
.EXIT
filtro PROC
MOV BP, SP
PUSH AX
PUSH BX
PUSH CX
PUSH SI
PUSH DI
MOV SI, [BP+6] ; indirizzo address
MOV DI, [BP+4] ; indirizzo mask
XOR BX, BX
MOV CX, DIM
ciclo: MOV AX, WORD PTR [SI]
XOR AX, [BP+8]
AND AX, WORD PTR [DI]
JNZ next
MOV AX, WORD PTR [SI+2]
XOR AX, [BP+10]
AND AX, WORD PTR [DI+2]
JNZ next
INC BX
next: ADD SI, 4
LOOP ciclo
MOV [BP+2], BX
POP DI
POP SI
POP CX
POP BX
POP AX
RET
filtro ENDP
END