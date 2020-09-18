.MODEL small
.STACK
.DATA
VAR DW ?
var2 DB 1 
var3 DD 6 
var4 DW 8
.CODE
.STARTUP
MOV VAR, 0
MOV var2,25
MOV var3, 35,5
MOV var4, 2
.EXIT
END