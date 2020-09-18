.MODEL small
.STACK
.DATA
prompt DB 'Inserire un intero positivo minore di 65536: '
lung_prompt EQU $-prompt ;lunghezza del vettore=indirizzo corrente-indirizzo iniziale
errore DB 'Hai inserito un intero troppo grande!'
lung_errore EQU $-errore   ;sempre differenza tra i due indirizzi
var DW 0        ;UNSIGNED
.CODE
.STARTUP
MOV AH,2
MOV CX,lung_prompt
LEA BX,prompt
XOR SI,SI
stampa_prompt:MOV DL,BYTE PTR BX[SI]
INT 21H
INC SI
LOOP stampa_prompt

MOV BX,10
XOR CX,CX
XOR DX,DX
;MOV BYTE PTR var,AL      Sposto AL gia' nel primo byte della variabile risultato in memoria

lettura:MOV AH,1        ;Input
INT 21H
CMP AL,13       ;L'input e' stato CR? (ascii=13)
JE fine
SUB AL,'0'      ;ASCII->Int
MOV CL,AL       ;Salvo in CL l'input perche' mi serve AX
MOV AX,var      ;Sposto il numero completo attuale in AX per moltiplicarlo per 10
MUL BX          ;Moltiplico per 10 il risultato attuale
JC bigger_than_word     ;Controllo che il risultato non sia troppo grande CF=1->AX*CX non sta in una word, ma ha bisogno di piu' spazio (DX)
ADD AX,CX       ;Aggiungo al risultato attuale il nuovo input (contenuto in CL ma mi assicuro che CX sia vuoto) e controllo di nuovo
JC bigger_than_word     ;Unsigned guarda carry
MOV var,AX      ;Se il risultato e' accettabile salvalo in memoria e continua
JMP lettura

bigger_than_word:MOV AH,2
MOV CX,lung_errore
LEA BX,errore
XOR SI,SI
MOV DL,10
INT 21H
MOV DL,13
INT 21H
stampa_errore:MOV DL,BYTE PTR BX[SI]
INT 21H
INC SI
LOOP stampa_errore

fine:
.EXIT
END

;USO DEI REGISTRI:
;AH valore I/0
;AL BYTE ricevuto dall'input
;CL BYTE temporaneo che era in AL 
;AX MUL 10
;10 lo tengo in BX
;Risultato lo tengo in DX
;
;controllo per CR(=13)