CR EQU 13
NL EQU 10
.MODEL small
.STACK
.DATA
prompt DB 'Richiedo introduzione di un numero intero: '                                                                                                               
lung EQU $-prompt

prompt_errore DB CR,NL,'Hai inserito un carattere che non corrisponde a un numero!',CR,NL
errlung EQU $-prompt_errore

message_pari DB CR,NL,'Il numero che hai inserito si considera pari',CR,NL
parilung EQU $-message_pari

message_dispari DB CR,NL,'Il numero che hai inserito si considera dispari',CR,NL
disparilung EQU $-message_dispari
.CODE
.STARTUP
LEA AX,prompt
MOV BX,lung
CALL stampa 
XOR CX,CX

lettura:MOV AH,1        ;Input
INT 21H
CMP AL,13       ;L'input e' stato CR? (ascii=13)
JE parita

CMP AL,'0'      ;Controllo inserimento numero
JB errore
CMP AL,'9'
JA errore

SUB AL,'0'      ;ASCII->Int
MOV CL,AL
INC CH
JMP lettura

parita: CMP CH,0
JE errore 
XOR CH,CH

;MOV AX,CX
;MOV DL,2
;DIV DL
;DISPENDIOSO!!
;CMP AH,0

TEST CL,1   ;AND CL,1=1->Dispari
            ;AND CL,1=0->Pari
JZ pari
JNZ dispari

pari:LEA AX,message_pari
MOV BX,parilung
CALL stampa
JMP fine

dispari:LEA AX,message_dispari
MOV BX,disparilung
CALL stampa
JMP fine
 
errore:LEA AX,prompt_errore
MOV BX,errlung
CALL stampa

fine:
.EXIT 
stampa PROC
    PUSH SI
    PUSH CX
    PUSH AX
    PUSH DX     ;Ah gia'!
    
    MOV CX,BX
    MOV SI,AX
    MOV AH,2
    
    cicla:MOV DL,[SI]
    INT 21H
    INC SI
    LOOP cicla
    
    POP DX
    POP AX
    POP CX
    POP SI
    RET
stampa ENDP
END