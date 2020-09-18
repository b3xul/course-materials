DIM EQU 8
.MODEL small
.STACK
.DATA
riferimento DD 82C0B685h
maschera DD FFFC0000h
address DD 82C0051AH, 0C0A80A01H, 4A7D276AH, 0D5FE1150H
        DD 0C7EF88C8H, 82C0B621H, 82C0A4F5H, 0ADC01874H  
risultato DB DIM DUP (1)    ;Soddisfano fino a prova contraria

.CODE
.STARTUP
push 82C0h          ;BP+12->VALORE parte alta di riferimento
push 0B685h         ;BP+10->VALORE parte bassa di riferimento
lea AX, address
push AX             ;BP+8->INDIRIZZO
lea AX, maschera
push AX             ;BP+6->INDIRIZZO
sub SP, 2           ;BP+4 spazio riservato per risultato
call filtro
pop BX              ;prelevamento risultato da stack (Non in AX senno' me lo rompe?)
add SP, 8

.EXIT
filtro PROC
    push BP
    mov BP,SP
    push CX
    push AX
    mov CX,DIM 
   
    MOV SI,[BP+8]           ;address[0]    
    MOV WORD PTR [BP+4],0   ;risultato
    
    scorri:MOV DI,[BP+6]    ;maschera[0]: N.B. LITTLE ENDIAN: PRIMA WORD LSW!
    MOV DX,CX

    ;Serve sicuramente AND tra indirizzo e maschera per valutare solo i bit presenti nella maschera
    MOV AX,WORD PTR [SI]    ;ADDRESS
    XOR AX,[BP+10]    ;XOR con il riferimento: rimane 1 se valori diversi
        
    AND AX,WORD PTR [DI]    ;Risultato in AND con la maschera: passano solo gli 1 di prima scelti dalla maschera       
    ;Per vedere se sono uguali? confronto semplicemente il numero? FUNZIONA perche' non passa la compare se ci sono 1 in posti dove la maschera e' a zero!                 
    ;CMP AX,WORD PTR [BX]       
    CMP AX,0    ;Se ci sono ancora degli 1 
    JNE non_soddisfa
    
    
    MOV AX,WORD PTR [SI+2]      ;ADDRESS[1]
    XOR AX,[BP+12]
        
    AND AX,WORD PTR [DI+2]      ;MASCHERA[1]      
    CMP AX,0 
    JNE non_soddisfa
    
    soddisfa: INC [BP+4]
    JMP prossimo
    ;vedere seconda parte... fino all'ottava!
    non_soddisfa: PUSH DI
    MOV DI,DX
    MOV risultato[DI],0
    POP DI
    
    prossimo: ADD SI,4
    LOOP scorri
    
    pop AX
    pop CX
    pop BP
    RET
filtro ENDP
END