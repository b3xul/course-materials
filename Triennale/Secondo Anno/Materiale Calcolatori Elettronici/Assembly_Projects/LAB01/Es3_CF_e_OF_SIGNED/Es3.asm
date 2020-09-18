.MODEL small
.STACK
.DATA
OPA DW -459  ;N.B. SIGNED
OPB DW 470
OPC DW 32756
OPD DW 1
.CODE
.STARTUP
MOV AX,OPA      ;AX=OPA=11111110 00110101+
                ;   OPB=00000001 11010110
                ;AX=65077 UNSIGNED
                ;AX=-459 SIGNED

ADD AX,OPB      ;AX=00000000 00001011+
               ;OPC=01111111 11110100
                ;AX=11 UNSIGNED !!! OVERFLOW
                ;AX=11 SIGNED
                ;       CF=1 (C'E' CARRY SUGLI 8 MSB->OVERFLOW UNSIGNED)
                ;       ZF=0 (NON VALE 0)
                ;       SF=0 (MSB=1)
                ;       OF=0 (RISULTATO NON TROPPO GRANDE PER STARE IN 16 BIT SIGNED)
                ;       PF=0 (AL CONTIENE UN NUMERO DISPARI DI 1)
                ;       AF=0 (AUXILIARY: NON C'E' CARRY NEGLI 8 LSB)

ADD AX,OPC      ;AX=01111111 11111111+
               ;OPD=00000000 00000001
                ;AX=32767 UNSIGNED
                ;AX=32767 SIGNED   
                ;       CF=0 (NON C'E' CARRY SUGLI 8 MSB)
                ;       ZF=0 (NON VALE 0)
                ;       SF=0 (MSB=0)
                ;       OF=0 (RISULTATO NON TROPPO GRANDE PER STARE IN 16 BIT SIGNED)
                ;       PF=1 (AL CONTIENE UN NUMERO PARI DI 1)
                ;       AF=0 (AUXILIARY: NON C'E' CARRY NEGLI 8 LSB)

ADD AX,OPD      ;AX=10000000 00000000
                ;AX=32768 UNSIGNED
                ;AX=-32768 SIGNED   !!! OVERFLOW   
                ;       CF=0 (NON C'E' CARRY SUGLI 8 MSB)
                ;       ZF=0 (NOB VALE 0)
                ;       SF=1 (MSB=1)
                ;       OF=1 (RISULTATO TROPPO GRANDE PER STARE IN 16 BIT SIGNED->OVERFLOW SIGNED)
                ;       PF=1 (AL CONTIENE UN NUMERO PARI DI 1)
                ;       AF=1 (AUXILIARY: C'E' CARRY NEGLI 8 LSB)
.EXIT
END