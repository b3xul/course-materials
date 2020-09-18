DIM EQU 5
.MODEL small
.STACK
.DATA
prompt DB 'Inserire cinque interi positivi minori di 65536: '
lung_prompt EQU $-prompt ;lunghezza del vettore=indirizzo corrente-indirizzo iniziale
errore DB 'Hai inserito un intero troppo grande!'
lung_errore EQU $-errore   ;sempre differenza tra i due indirizzi
fattore DW 10
var DW DIM DUP (?)        ;UNSIGNED
.CODE
.STARTUP         
;stampa prompt

MOV AH,2         
MOV CX,lung_prompt
LEA BX,prompt
XOR SI,SI
stampa_prompt:MOV DL,BYTE PTR BX[SI]
INT 21H
INC SI
LOOP stampa_prompt
;fine stampa

MOV BX,DIM
LEA DI,var
ciclo:
    ;Inizializzazioni
    XOR CX,CX
    XOR DX,DX
    MOV WORD PTR [DI],0
    ;MOV BYTE PTR var,AL      Sposto AL gia' nel primo byte della variabile risultato in memoria
    
    ;Ciclo
    lettura:MOV AH,1        ;Input
    INT 21H
    CMP AL,13       ;L'input e' stato CR? (ascii=13)
    JE fineciclo
    SUB AL,'0'      ;ASCII->Int
    MOV CL,AL       ;Salvo in CL l'input perche' mi serve AX
    ;E' necessario azzerare qua sapendo che tanto MOV sovrascrivera'?
    MOV AX,[DI]      ;Sposto il numero completo attuale in AX per moltiplicarlo per 10
    MUL fattore          ;Moltiplico per 10 il risultato attuale
    JC bigger_than_word     ;Controllo che il risultato non sia troppo grande CF=1->AX*CX non sta in una word, ma ha bisogno di piu' spazio (DX)
    ADD AX,CX       ;Aggiungo al risultato attuale il nuovo input (contenuto in CL ma mi assicuro che CX sia vuoto) e controllo di nuovo
    JC bigger_than_word     ;Unsigned guarda carry
    MOV [DI],AX      ;Se il risultato e' accettabile salvalo in memoria e continua
    JMP lettura

fineciclo:
    MOV AH,2
    MOV DX,10
    INT 21H
    MOV DX,13
    INT 21H
    ADD DI,2
    DEC BX
    CMP BX,0
    JNZ ciclo
    JZ fine
    

;Errore
bigger_than_word:MOV AH,2
MOV CX,lung_errore
LEA BX,errore
XOR SI,SI
MOV DL,10
INT 21H
MOV DL,13
INT 21H
;stampa_errore:MOV DL,BYTE PTR BX[SI]
;INT 21H
;INC SI
;LOOP stampa_errore

fine:
.EXIT
END

;USO DEI REGISTRI:
;AH valore I/0
;AL BYTE ricevuto dall'input
;CL BYTE temporaneo che era in AL 
;AX MUL 10
;10 lo tengo in BX
;Risultato lo tengo in var oppure in [DI] cosa cambia?
;
;controllo per CR(=13)