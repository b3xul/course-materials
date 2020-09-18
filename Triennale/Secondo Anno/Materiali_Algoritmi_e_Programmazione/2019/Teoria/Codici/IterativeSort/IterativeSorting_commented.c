#include "ordinamenti.h"

void InsertionSort(Item A[], int N) {
  int i, j, l=0, r=N-1;
  Item x;
  for(i = l+1; i <= r; i++) {
    x = A[i];                               //x=elemento da ordinare
    j = i - 1;                              //confronta con i valori da quello precedente al primo del vettore e man mano se c'e bisogno trasla
    while (j >= l && ITEMlt(x, A[j])) {     //finchè x è minore dell'elemento precedente
      A[j+1] = A[j];                        //se x è minore di quello che lo precede trasla quest'ultimo avanti di uno
      j--;
    }
    A[j+1] = x;                             //piazza x dopo quello di cui non è minore
  }
}


void InsertionSortReversed(Item A[],int N){
    int i,j,l=0,r=N-1;
    Item x;

    for(i = l+1; i <= r; i++) {
        x = A[i];                               //x=elemento da ordinare
        j = i - 1;                              //confronta con i valori da quello precedente al primo del vettore e man mano se c'e bisogno trasla
        while (j >= l && ITEMgt(x, A[j])) {     //finchè x è maggiore dell'elemento precedente
          A[j+1] = A[j];                        //se x è maggiore di quello che lo precede trasla quest'ultimo avanti di uno
          j--;
        }
        A[j+1] = x;                             //piazza x dopo quello di cui non è maggiore
    }
}

void InsertionSortPartial(Item A[],int N,int l,int r){
    int i, j;
    Item x;

    if(l<0 || l>r || r>=N){
        fprintf(stderr,"Indici errati\n");
        exit(-1);
    }
    for(i = l+1; i <= r; i++) {
        x = A[i];                               //x=elemento da ordinare
        j = i - 1;                              //confronta con i valori da quello precedente al primo del vettore e man mano se c'e bisogno trasla
        while (j >= l && ITEMlt(x, A[j])) {     //finchè x è minore dell'elemento precedente
            A[j+1] = A[j];                      //se x è minore di quello che lo precede trasla quest'ultimo avanti di uno
            j--;
        }
        A[j+1] = x;                             //piazza x dopo quello di cui non è minore
    }
}

void InsertionSortPartialReversed(Item A[],int N,int l,int r){
        int i, j;
    Item x;

    if(l<0 || l>r || r>=N){
        fprintf(stderr,"Indici errati\n");
        exit(-1);
    }
    for(i = l+1; i <= r; i++) {
        x = A[i];                               //x=elemento da ordinare
        j = i - 1;                              //confronta con i valori da quello precedente al primo del vettore e man mano se c'e bisogno trasla
        while (j >= l && ITEMgt(x, A[j])) {     //finchè x è maggiore dell'elemento precedente
            A[j+1] = A[j];                      //se x è maggiore di quello che lo precede trasla quest'ultimo avanti di uno
            j--;
        }
        A[j+1] = x;                             //piazza x dopo quello di cui non è maggiore
    }
}


/*void CountingSort(Item A[], int l, int r int k) {
    ... wrapper
}
*/

void InsertionSort(int A[], int l, int r){//(O(n^2))
    int i,j;
    int x;

    for(i=l+1;i<=r;i++){
        x=A[i];
        j=i-1;                      //confronta con i valori da quello precedente al primo del vettore e man mano se c'e bisogno trasla
        while(x<A[j]&&j>=l){
            A[j+1]=A[j];            //se A[i] è minore di quello che lo precede trasla quest'ultimo avanti di uno
            j--;
        }
        A[j+1]=x;                   //piazza A[i] dopo quello di cui non è minore
    }

    return;
}

void BubbleSort(int A[],int l,int r){//(O(n^2))
    int i,j;
    int tmp;
    int flag=1;

    for(i=l;i<r&&flag==1;i++){        //confronta dall'inizio al penultimo con il successivo
        flag=0;                       //se al giro precedente non ci sono stati scambi il vettore è già ordinato perchè i confronti partono sempre da l fino alla fine (l'indice arriva massimo a r-1 ma il confronto lo si fa con j+1 (ultimo elemento)
        for(j=l;j<r-i+l;j++){         //quante "volte" confronta, all'inizio r,poi r-1, ... poi un solo confronto alla fine, ma anche CHI confronta (i primi elementi del vettore sono quelli disordinati)
            if(A[j]>A[j+1]){
                flag=1;
                tmp=A[j+1];
                A[j+1]=A[j];          //se A[i] è maggiore del successivo scambiali
                A[j]=tmp;
            }
        }               //altrimenti vuol dire che è nella posizione giusta
    }
}

void SelectionSort(int A[],int l,int r){//(O(n^2))
    int i,j;
    int min;
    int tmp;

    for(i=l;i<r;i++){               //scambio un elemento per volta
        min=A[i];
        for(j=i+1;j<=r;j++){
            if(A[j]<min)  min=j;    //trovo il minimo nei successivi(in realtà trovo LA POSIZIONE DEL MINIMO) e poi uso quella per scambiare il numero in quella posizione (il minimo)
        }
        tmp=A[i];
        A[i]=A[min];                //scambio l'elemento corrente i con il minimo
        A[min]=tmp;
    }
}

void ShellSort(int A[], int l, int r){//(O(n^3/2)
    int i,j;
    int x;
    int n=r-l+1;                          //lunghezza vettore=dfferenza degli indici estremi +1 per convertire da indice a lunghezza
    int h=1;

    while(h<n/3)
        h=3*h+1;                          //trovo il massimo h per cui dividere a seconda della dimensione del vettore

    while(h>=1){                      //man mano diminuisco h seguendo la sequenza di CNUT (si avvicinano i sottovettori)
        for(i=l+h;i<=r;i++){
            j=i;
            x=A[i];
            while(x<A[j-h]&&j>=l+h){    //confronta i valori distanti h, se c'e bisogno ordinali, poi passa al valore successivo e confrontalo con quelli distanti h...
                A[j]=A[j-h];            //se A[i] è minore di quello con cui è confrontatp invertili
                j-=h;
            }
            A[j]=x;                   //piazza A[i] al posto di quello di cui non è minore
        }
        h=h/3;                        //non serve il -1 perchè tronca da solo
    }
    return;
}

/*
void ShellSort(Item A[], int N) {
  int i, j, h=1, l=0, r= N-1;
  Item tmp;

  while (h < N/3)
    h = 3*h+1;
  while(h >= 1) {
    for (i = l + h; i <= r; i++) {
      j = i;
      tmp = A[i];
      while(j >= l + h  && ITEMlt(tmp,  A[j-h])) {
        A[j] = A[j-h];
        j -=h;
      }
      A[j] = tmp;
    }
    printf("h = %d, h-sorted array is: \n", h);
    for (i = 0; i <(r-l+1); i++)
      ITEMshow(A[i]);
    printf("\n");
    h = h/3;
  }
}
*/
