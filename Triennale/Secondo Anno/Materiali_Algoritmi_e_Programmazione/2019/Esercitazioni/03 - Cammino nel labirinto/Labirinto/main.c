#include <stdio.h>
#include <stdlib.h>

#define MAX_DIM 50

typedef enum dir{N,E,S,O,NE,SE,SO,NO} Dir_e;
typedef struct dim{int nr; int nc;} Dim_t;
typedef struct cam{int lunghezza; int semplice;} Cam_t;

Dim_t leggiLab(char lab[][MAX_DIM]);
Cam_t verificaMosse(char lab[][MAX_DIM],Dim_t d);
Dir_e selezionaDirezione(int dx,int dy);

int main()
{
    char lab[MAX_DIM][MAX_DIM];
    Dim_t dimensioni;
    Cam_t cammino;
    int i,j;

    dimensioni=leggiLab(lab);

    for(i=0;i<dimensioni.nr;i++){
        for(j=0;j<dimensioni.nc;j++)
            printf("%c",lab[i][j]);
        printf("\n");
    }

    cammino=verificaMosse(lab,dimensioni);

    if (cammino.lunghezza ==0)
        printf("Cammino non valido!\n");
    else
        printf("Cammino %s semplice di lunghezza %d\n", cammino.semplice ? "" : "non ", cammino.lunghezza);

    return 0;
}

Dim_t leggiLab(char lab[][MAX_DIM]){
    int i,j;
    Dim_t d;
    FILE* fp;

    if((fp=fopen("labirinto.txt","r"))==NULL){
        fprintf(stderr,"Errore apertura file labirinto\n");
        exit(-1);
    }
    fscanf(fp,"%d%d\n",&d.nr,&d.nc);
    for(i=0;i<d.nr;i++){
        for(j=0;j<d.nc;j++){
            fscanf(fp,"%c",&lab[i][j]);
        }
        fgetc(fp);
    }
    return d;
}

Cam_t verificaMosse(char lab[][MAX_DIM],Dim_t d){
    int i,j,x=0,y=0,dx,dy,final_x,final_y,inCammino=1;
    Cam_t cammino;
    cammino.lunghezza=0;
    cammino.semplice=1;

    Dir_e direzione;

    FILE* fp;

    if((fp=fopen("mosse.txt","r"))==NULL){
        fprintf(stderr,"Errore apertura file mosse\n");
        exit(-1);
    }

    while((fscanf(fp,"%d%d",&dx,&dy)==2)&&inCammino){  //N.B. 0 -2 =sud y+(-1)*dy!
        final_x=x+dx;           //Colonna x
        final_y=y+(-1)*dy;      //Riga y
        if(final_x>=d.nc || final_x <0 || final_y>=d.nr || final_y<0){
            cammino.lunghezza=0;
            inCammino=0;
            break;
        }

        direzione=selezionaDirezione(dx,dy);
        switch(direzione){
            case N:
                for(i=y-1;i>=final_y;i--){    //Righe verso l'alto
                    switch(lab[i][x]){
                        case 'X':
                            /*Non strutturato
                            printf("Percorso non valido!\n");
                            exit(0);*/
                            cammino.lunghezza=0;
                            /*Inutile*/
                            cammino.semplice=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[i][x]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
                break;
            case S:
                for(i=y+1;i<=final_y;i++){    //Righe verso il basso
                    switch(lab[i][x]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[i][x]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
            break;
            case E:
                for(j=x+1;j<=final_x;j++){    //Colonne verso destra
                    switch(lab[y][j]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[y][j]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
            break;
            case O:
                for(j=x-1;j>=final_x;j--){    //Colonne verso sinistra
                    switch(lab[y][j]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[y][j]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
            break;
            case NE:
                for(i=y-1,j=x+1 ; i>=final_y && j<=final_x ; i--,j++){    //Righe verso l'alto e colonne verso destra
                    switch(lab[i][j]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[i][j]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
                break;
            case SE:
                for(i=y+1,j=x+1 ; i<=final_y && j<=final_x ; i++,j++){    //Righe verso il basso e colonne verso destra
                    switch(lab[i][j]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[i][j]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
            break;
            case SO:
                for(i=y+1,j=x-1 ; i<=final_y && j>=final_x ; i++,j--){    //Righe verso il basso e colonne verso sinistra
                    switch(lab[i][j]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[i][j]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
            break;
            case NO:
                for(i=y-1, j=x-1 ; i>=final_y && j>=final_x ; i--,j--){    //Righe verso l'alto e colonne verso sinistra
                    switch(lab[i][j]){
                        case 'X':
                            cammino.lunghezza=0;
                            inCammino=0;        //Percorso non valido
                            break;
                        case '-':
                            cammino.lunghezza++;
                            lab[i][j]='+';
                            break;
                        case '+':
                            cammino.lunghezza++;
                            cammino.semplice=0;
                            break;
                        default:    //I e U
                            cammino.lunghezza++;
                    }
                }
            break;
            default:
                break;
        }

        printf("\n");
        for(i=0;i<d.nr;i++){
            for(j=0;j<d.nc;j++)
                printf("%c",lab[i][j]);
            printf("\n");
        }
        printf("\n");

        x=final_x;
        y=final_y;
        if(y==d.nr-1 && x==d.nc-1){
            inCammino=0;        //Arrivati
            printf("Il cammino ha raggiunto la destinazione!\n");
        }
    }

    return cammino;
}

Dir_e selezionaDirezione(int dx,int dy){
  Dir_e direzione;

  if (dx <0 && dy == 0)
    direzione = O;
  if (dx > 0 && dy == 0)
    direzione = E;
  if (dx == 0 && dy < 0)
    direzione = S;
  if (dx == 0 && dy > 0)
    direzione = N;
  if (dx < 0 && dy > 0)
    direzione = NO;
  if (dx > 0 && dy > 0)
    direzione = NE;
  if (dx < 0 && dy < 0)
    direzione = SO;
  if (dx > 0 && dy < 0)
    direzione = SE;

  return direzione;
}
