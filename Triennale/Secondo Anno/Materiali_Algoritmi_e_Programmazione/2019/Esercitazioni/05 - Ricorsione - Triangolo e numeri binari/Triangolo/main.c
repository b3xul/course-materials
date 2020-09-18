#include <stdio.h>
#include <stdlib.h>

//void triangolo(int pos, int* val, int* sol, int n, int k);
void triangolo(int* val, int n);
void triangolo_r(int* val, int n);

int main()
{
    int a[5]={1,2,3,4,5};

    triangolo(a,5);
    triangolo_r(a,5);

    return 0;
}

void triangolo(int* val, int n){
    int i,j;
    int* temp=(int*)malloc(n*sizeof(int));

    if(n<1)     //Vettore di dimensione 0
        return;

    for(i=0,j=0;i<n-1;i++)  //Riempio vettore per la prossima ricorsione
        temp[j++]=val[i]+val[i+1];

    printf("[ ");
    for(i=0; i<n; i++)
        printf("%d ", val[i]);  //Val è il vettore che mi è stato passato
    printf("]\n");

    triangolo(temp,n-1);    //Prossimo vettore ha dimensione n-1

    return;
}

void triangolo_r(int* val, int n){
    int i,j;
    int* temp=(int*)malloc(n*sizeof(int));

    if(n<1)     //Vettore di dimensione 0
        return;

    for(i=0,j=0;i<n-1;i++)  //Riempio vettore per la prossima ricorsione
        temp[j++]=val[i]+val[i+1];

    triangolo_r(temp,n-1);    //Prossimo vettore ha dimensione n-1

    printf("[ ");
    for(i=0; i<n; i++)
        printf("%d ", val[i]);  //Val è il vettore che mi è stato passato
    printf("]\n");

    return;
}

/*void triangolo(int pos, int* val, int* sol, int n, int k){  //Non vera ricorsione!
    int i,j;

    if(pos>=n){
        return;
    }

    printf("[ ");
    for(i=0,j=0 ; i<k; i++){
        printf("%d ", sol[i]);
        if(j<k-1)
            sol[j++]=val[i]+val[i+1];
    }
    printf("]\n");

    triangolo(pos+1,val,sol,n,k-1);

    return;

}*/
