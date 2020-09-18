.model small
.stack
.data
risult DD ?
.code
.startup
PUSH 256
PUSH 3
SUB SP, 4
CALL potenza
POP AX
POP DX
ADD SP, 4
mov risult, AX
mov risult+2, DX
.exit
potenza proc
MOV BP, SP
PUSH AX
PUSH BX
PUSH CX
PUSH DX
MOV AX, 1 ; accumulatore doubleword AX, DX
XOR DX, DX ; inizialmente a 1
MOV CX, [BP+6] ; contatore numero di iterazioni
CMP CX, 0
JE uno
ciclo: MOV BX, DX
MUL WORD PTR [BP+8] ; moltiplicazione "in colonna" cominciando da parte meno significativa
                    ; primo giro: 1*BASE
PUSH AX
PUSH DX             
                    ; risultato della mul precedente->AX al giro dopo rimane
MOV AX, BX
MUL WORD PTR [BP+8] ; moltiplicazione parte piu' significativa
                    ; primo giro: 0*BASE
JC ovf2 ; overflow se eccedo 16 bit su parte piu' sign.
POP DX
ADD DX, AX
                    ; 0+AX
JC ovf1 ; overflow se eccedo 16 bit su parte piu' sign.
POP AX
LOOP ciclo
MOV [BP+2], AX
MOV [BP+4], DX
JMP fine
uno: MOV [BP + 2], 1
MOV [BP + 4], 0
JMP fine
ovf2: ADD SP, 2
ovf1: ADD SP, 2
MOV [BP+2], 0FFFFh
MOV [BP+4], 0FFFFh
fine: POP DX
POP CX
POP BX
POP AX
RET
potenza endp
END