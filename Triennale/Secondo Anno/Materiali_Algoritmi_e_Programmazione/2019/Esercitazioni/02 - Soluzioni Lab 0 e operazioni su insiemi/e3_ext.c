/* Sfrutta l'algebra modulare per individuare una serie di spostamenti successivi tali da coprire tutte le celle del vettore
  Se N e P sono coprimi, incrementi dell'indice di destinazione pari a P comportano la visita di tutte le celle del vettore una e una sola volta
  Se N e P non sono coprimi, dopo N/MCD(N, P) spostamenti si ritornera' sulla cella iniziale. Inoltre, le celle visitate saranno distanziate di K = MCD(N, P) posizioni.
  Partendo dalla posizione 0, saranno stati spostati gli elementi in posizione 0, K, 2K, 3K ... 
  Il ciclo esterno si occupera' quindi di incrementare la posizione di inizio, in modo da shiftare gli elementi in posizione i, K+i, 2K+i, ... fino a coprire tutte le celle.
  Da notare come l'algoritmo sia simile a quello che effettua rotazioni di 1 singola posizione
*/
void ruotaVettore5(int V[maxN], int dir, int N, int P) {
  int tmp, i, j, cnt;
  if (dir == -1) {
    /* scambia direzione: P spostamenti a DX sono equivalenti a N-P a SX */
    P = N-P;
  }
  /* Il ciclo esterno incrementa la posizione di partenza degli shift fintanto che non siano stati effettuati N spostamenti - se N e P sono coprimi si effettuera' una sola iterazione */
  for (cnt = 0, i = 0; cnt < N; i++) {
    tmp = V[i];
    /* Il procedimento e' analogo allo shift di 1 posizione, ma gli spostamenti coprono P caselle */
    for (j = i; (j + P) % N != i; j = (j + P) % N, cnt++) {
      V[j] = V[(j+P)%N];
    }
    V[j] = tmp;
    cnt++;
  }
}
