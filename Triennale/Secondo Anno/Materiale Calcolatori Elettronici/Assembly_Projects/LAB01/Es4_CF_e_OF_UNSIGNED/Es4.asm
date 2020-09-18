.MODEL small
.STACK
.DATA
OPA DW 32767    ;UNSIGNED
OPB DW 1
.CODE
.STARTUP
MOV AX,OPA      ;AX=01111111 11111111
                ;AX=32767 UNSIGNED
                ;AX=32767 SIGNED

ADD AX,OPB      ;AX=10000000 00000000
                ;AX=32768 UNSIGNED
                ;AX=-32768 SIGNED   !!!OVERFLOW
                ;       CF=0 (NON C'E' CARRY SUGLI 8 MSB)
                ;       ZF=0 (NON VALE 0)
                ;       SF=1 (MSB=1)
                ;       OF=1 (RISULTATO TROPPO GRANDE PER STARE IN 16 BIT SIGNED->OVERFLOW SIGNED)
                ;       PF=1 (AL CONTIENE UN NUMERO PARI DI 1)
                ;       AF=1 (AUXILIARY: C'E' CARRY NEGLI 8 LSB)

ADD AX,OPA      ;AX=11111111 11111111
                ;AX=65535 UNSIGNED
                ;AX=-1 SIGNED   
                ;       CF=0 (NON C'E' CARRY SUGLI 8 MSB)
                ;       ZF=0 (NON VALE 0)
                ;       SF=1 (MSB=1)
                ;       OF=0 (RISULTATO NON TROPPO GRANDE PER STARE IN 16 BIT SIGNED)
                ;       PF=1 (AL CONTIENE UN NUMERO PARI DI 1)
                ;       AF=0 (AUXILIARY: NON C'E' CARRY NEGLI 8 LSB)

ADD AX,OPB      ;AX=00000000 00000000
                ;AX=0 UNSIGNED  !!!OVERFLOW
                ;AX=0 SIGNED   
                ;       CF=1 (C'E' CARRY SUGLI 8 MSB->OVERFLOW UNSIGNED)
                ;       ZF=1 (VALE 0)
                ;       SF=0 (MSB=0)
                ;       OF=0 (RISULTATO NON TROPPO GRANDE PER STARE IN 16 BIT SIGNED)
                ;       PF=1 (AL CONTIENE UN NUMERO PARI DI 1)
                ;       AF=1 (AUXILIARY: C'E' CARRY NEGLI 8 LSB)
.EXIT
END