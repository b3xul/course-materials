int cerca4(int v[MAXN], int n) {
  int maxl=0, i, j, nmax=0;
  int massimi_i0[MAXN];
  /* identifica i sottovettori di lunghezza massima */
  for (i=0; i < n;) {

    /* scorre posizioni fintanto che trova caselle con 0 */
    for (; i<n && v[i] == 0; i++) ;

    /* evita di gestire i casi particolari dovuti al raggiungimento del fondo del vettore */
    if (i >= n) break;
    
    /* scorre posizioni fintanto che trova caselle con valore != 0 */
    for (j = i + 1; j<n && v[j] != 0; j++) ;
    
    /* il sottovettore e' terminato, ne calcolo la lunghezza e la confronto con il massimo temporaneo,
        eventualmente aggiornando il massimo, il vettore massimi_i0 e il suo indice nmax */
    if (j - i > maxl) {
      maxl = j - i;
      massimi_i0[0] = i;
      nmax = 1;
    }
    else if (j - i == maxl) {
      massimi_i0[nmax++] = i;
    }
    i = j;      
  }

  /* stampa i sottovettori a massima lunghezza  (eventualmente più di uno)  */  
  for(i=0; i<nmax; i++) {
    printf("sottovettore massimo: ");
    stampaIntervalloVettore(v,massimi_i0[i],massimi_i0[i]+maxl-1);
  }

  return 0;

}
