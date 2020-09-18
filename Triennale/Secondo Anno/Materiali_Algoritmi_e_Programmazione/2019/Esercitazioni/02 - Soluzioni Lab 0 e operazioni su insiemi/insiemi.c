#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 20

void leggiVet(int V[], int n) {
  int i;

  for(i=0;i<n;i++) {
    printf("Inserisci V[%d]: ", i);
    scanf("%d", &V[i]);
  }
  return;
}

int appartenenza(int V[], int v, int val) {
  int i;

  for(i=0;i<v;i++) {
    if (V[i] == val)
      return 1;
  }
  return 0;
}

int unione(int V1[], int V2[], int v1, int v2, int U[]) {
  int i, k = 0;

  for(i=0;i<v1;i++) {
    if (!appartenenza(U, k, V1[i]))
      U[k++] = V1[i];
  }
  for(i=0;i<v2;i++) {
    if (!appartenenza(U, k, V2[i]))
      U[k++] = V2[i];
  }
  return k;
}

int differenza(int V1[], int V2[], int v1, int v2, int D[]) {
  int i, k = 0;

  for(i=0;i<v1;i++) {
    if (!appartenenza(D, k, V1[i]) && !appartenenza(V2, v2, V1[i]))
      D[k++] = V1[i];
  }
  for(i=0;i<v2;i++) {
    if (!appartenenza(D, k, V2[i]) && !appartenenza(V1, v1, V2[i]))
      D[k++] = V2[i];
  }
  return k;
}

int intersezione(int V1[], int V2[], int v1, int v2, int I[]) {
  int i, k = 0;

  for(i=0;i<v1;i++) {
    if (!appartenenza(I, k, V1[i]) && appartenenza(V2, v2, V1[i]))
      I[k++] = V1[i];
  }
  for(i=0;i<v2;i++) {
    if (!appartenenza(I, k, V2[i]) && appartenenza(V1, v1, V2[i]))
      I[k++] = V2[i];
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

  u = unione(V1, V2, v1, v2, U);
  printf("L'unione dei 2 vettori e': {");
  for(j=0;j<u;j++)
    printf("%d ", U[j]);
  printf("}\n");

  d = differenza(V1, V2, v1, v2, D);
  printf("La differenza simmetrica dei 2 vettori e': {");
  for(j=0;j<d;j++)
    printf("%d ", D[j]);
  printf("}\n");

  i = intersezione(V1, V2, v1, v2, I);
  printf("L'intersezione dei 2 vettori e': {");
  for(j=0;j<i;j++)
    printf("%d ", I[j]);
  printf("}\n");

  return 0;
}
