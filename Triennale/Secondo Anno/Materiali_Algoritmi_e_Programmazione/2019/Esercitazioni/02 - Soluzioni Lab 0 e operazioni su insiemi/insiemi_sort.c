#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 20

void sort(int V[], int l, int r) {
  int i, j, min, temp;

  for(i=l;i<r;i++) {
    min = i;
    for(j=i+1;j<=r;j++)
      if (V[j]<V[min])
        min = j;
    temp = V[i];
    V[i] = V[min];
    V[min] = temp;
  }
  return;
}

void leggiVet(int V[], int n) {
  int i;

  for(i=0;i<n;i++) {
    printf("Inserisci V[%d]: ", i);
    scanf("%d", &V[i]);
  }
  return;
}

int unione(int V1[], int V2[], int v1, int v2, int U[]) {
  int i = 0, j = 0, k = 0;
  int daInserire = 0;

  while(i < v1 && j < v2) {
    daInserire = (V1[i] < V2[j]) ? V1[i++] : V2[j++];
    if (k == 0 || daInserire != U[k-1]) // Salta elementi ripetuti
      U[k++] = daInserire;
  }

  while(i < v1) {
    if (k == 0 || (k > 0 && V1[i] > U[k-1]))
      U[k++] = V1[i++];
    else
      i++;
  }
  while(j < v2) {
    if (k == 0 || (k > 0 && V2[j] > U[k-1]))
      U[k++] = V2[j++];
    else
      j++;
  }

  return k;
}

int intersezione(int V1[], int V2[], int v1, int v2, int I[]) {
  int i = 0,  j = 0, k = 0;

  while(i < v1 && j < v2) {
    if (V1[i] < V2[j])
      i++;
    else if (V2[j] < V1[i])
      j++;
    else if (V1[i] == V2[j]) {
      if (k > 0 && V1[i] == I[k-1]) {
        i++;
        j++;
      }
      else {
        I[k++] = V1[i];
        i++;
        j++;
      }
    }
  }
  return k;
}

int differenza(int V1[], int V2[], int v1, int v2, int D[]) {
  int i = 0, j = 0, k = 0, toSkip;

  while (i < v1 && j < v2) {
    if (V1[i] < V2[j]) {
      if (k > 0 && V1[i] == D[k-1])
        i++;
      else
        D[k++] = V1[i++];
    }
    else if (V2[j] < V1[i]) {
      if (k > 0 && V2[j] == D[k-1])
        j++;
      else
        D[k++] = V2[j++];
    }
    else {
      toSkip = V1[i];
      while(i < v1 && V1[i] == toSkip)
        i++;
      while(j < v2 && V2[j] == toSkip)
        j++;
    }
  }

  while(i < v1) {
    if (k == 0 || (k > 0 && V1[i] > D[k-1]))
      D[k++] = V1[i++];
    else
      i++;
  }
  while(j < v2) {
    if (k == 0 || (k > 0 && V2[j] > D[k-1]))
      D[k++] = V2[j++];
    else
      j++;
  }
  return k;
}

int main() {
  int v1, v2, V1[MAX], V2[MAX];
  int j, u, d, i, U[2*MAX], D[MAX], I[MAX];

  do {
    printf("Primo vettore: quanti valori vuoi inserire?\n");
    scanf("%d", &v1);
  } while(v1 <= 0 || v1 > MAX);

  leggiVet(V1, v1);

  do {
    printf("Secondo vettore: quanti valori vuoi inserire?\n");
    scanf("%d", &v2);
  } while(v2 <= 0 || v2 > MAX);

  leggiVet(V2, v2);

  sort(V1, 0, v1-1);
  sort(V2, 0, v2-1);

  u = unione(V1, V2, v1, v2, U);
  printf("L'unione dei 2 vettori e': { ");
  for(j=0;j<u;j++)
    printf("%d ", U[j]);
  printf("}\n");

  d = differenza(V1, V2, v1, v2, D);
  printf("La differenza simmetrica dei 2 vettori e': { ");
  for(j=0;j<d;j++)
    printf("%d ", D[j]);
  printf("}\n");

  i = intersezione(V1, V2, v1, v2, I);
  printf("L'intersezione dei 2 vettori e': { ");
  for(j=0;j<i;j++)
    printf("%d ", I[j]);
  printf("}\n");

  return 0;
}
