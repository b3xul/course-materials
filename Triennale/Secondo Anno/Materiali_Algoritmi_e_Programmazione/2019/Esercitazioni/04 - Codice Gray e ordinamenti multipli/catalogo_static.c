#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAXC 10
#define MAXN 20
#define MAXPROD 25

typedef struct {
  char codice[MAXC];
  char nome[MAXN];
  float prezzo;
} prodotto_t;

typedef enum {
  cr_codice,cr_nome
} criterio_t;

int leggiCatalogo(prodotto_t *cat) {
  FILE *fp;
  int np, i;
  fp=fopen("catalogo.txt","r");
  if(fp==NULL) {
    fprintf(stderr,"Errore in apertura file");
    return 0;
  }
  fscanf(fp,"%d",&np);
  for (i=0; i<np; i++) {
    fscanf(fp,"%s %s %f",cat[i].codice,cat[i].nome, &cat[i].prezzo);
  }
  fclose(fp);
  return np;
}

void stampaProdotto(prodotto_t p, FILE *fp) {
  fprintf(fp, "%s %s %.2f\n",
	  p.codice, p.nome, p.prezzo);
}

void stampaCatalogo(prodotto_t *cat, int np, FILE *f) {
  int i;
  if (cat == NULL || f == NULL) return;
  fprintf(f,"%d\n",np);
  for(i=0;i<np;i++)
    stampaProdotto(cat[i],f);
}

void stampaCatalogo_vistaIndici(prodotto_t *cat, int np, int *vista, FILE *f) {
  int i;
  if (cat == NULL || f == NULL) return;
  fprintf(f,"%d\n",np);
  for (i = 0; i < np; i++) {
    stampaProdotto(cat[vista[i]], f);
  }
}

void stampaCatalogo_vistaPuntatori(prodotto_t **vista, int np, FILE *f) {
  int i;
  if (vista == NULL || f == NULL) return;
  fprintf(f,"%d\n",np);
  for (i = 0; i < np; i++) {
    stampaProdotto(*vista[i], f);
  }
}

int confrontaProdotti (prodotto_t a, prodotto_t b, criterio_t crit) {
  switch (crit) {
  case cr_codice:
    return strcmp(a.codice,b.codice);
    break;
  case cr_nome:
    return strcmp(a.nome,b.nome);
    break;
  default: fprintf(stderr,"codice errato in confronto prodotti");
    return 0; /* risultato arbitrario */
  }
}

/* Insertion sort*/
void ordinaVistaIndici(prodotto_t *cat, int np, int *vista, criterio_t crit) {
  int i, j;
  int temp;
  for (i=1; i<np; i++) {
    temp = vista[i];
    j = i-1;
    while (j >= 0 && confrontaProdotti(cat[temp], cat[vista[j]], crit) < 0) {
      vista[j+1] = vista[j];
      j--;
    }
    vista[j+1] = temp;
  }
}

/* Insertion sort*/
void ordinaVistaPuntatori(prodotto_t *cat, int np, prodotto_t *vista[MAXPROD], criterio_t crit) {
  int i, j;
  prodotto_t *temp;
  for (i=1; i<np; i++) {
    temp = vista[i];
    j = i-1;
    while (j >= 0 && confrontaProdotti(*temp, *vista[j], crit) < 0) {
      vista[j+1] = vista[j];
      j--;
    }
    vista[j+1] = temp;
  }
}

void inizializza_vistaInteri(int *vista, int np) {
  int i;
  for (i = 0; i < np; i++) {
    vista[i] = i;
  }
}

void inizializza_vistaPuntatori(prodotto_t *catalogo, prodotto_t *vista[MAXPROD], int np) {
  int i;
  for (i = 0; i < np; i++) {
    vista[i] = &catalogo[i];
  }
}

int main() {

  prodotto_t catalogo[MAXPROD];
  int vistaNomeI[MAXPROD];
  int vistaCodiceI[MAXPROD];
  prodotto_t *vistaNomeP[MAXPROD];
  prodotto_t *vistaCodiceP[MAXPROD];
  int np;

  np = leggiCatalogo(catalogo);

  inizializza_vistaInteri(vistaNomeI, np);
  inizializza_vistaInteri(vistaCodiceI, np);

  inizializza_vistaPuntatori(catalogo, vistaNomeP, np);
  inizializza_vistaPuntatori(catalogo, vistaCodiceP, np);

  stampaCatalogo(catalogo, np, stdout);
  printf("\n");
  ordinaVistaIndici(catalogo, np, vistaNomeI, cr_nome);
  ordinaVistaIndici(catalogo, np, vistaCodiceI, cr_codice);

  ordinaVistaPuntatori(catalogo, np, vistaNomeP, cr_nome);
  ordinaVistaPuntatori(catalogo, np, vistaCodiceP, cr_codice);

  stampaCatalogo_vistaIndici(catalogo, np, vistaNomeI, stdout);
  printf("\n");
  stampaCatalogo_vistaIndici(catalogo, np, vistaCodiceI, stdout);
  printf("\n");

  stampaCatalogo_vistaPuntatori(vistaNomeP, np, stdout);
  printf("\n");
  stampaCatalogo_vistaPuntatori(vistaCodiceP, np, stdout);
  printf("\n");
  
  
}
