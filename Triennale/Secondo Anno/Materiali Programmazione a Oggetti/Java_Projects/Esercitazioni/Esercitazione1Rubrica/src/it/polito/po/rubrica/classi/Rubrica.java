package it.polito.po.rubrica.classi;

public class Rubrica {
	
	private String nome;
	
	private Voce[] voci;
	
	private int next;
	
	private final int MAX_VOCI = 100;
	
	public Rubrica(String nome) {
		this.nome = nome;
		this.voci = new Voce[MAX_VOCI];
		this.next = 0;
	}
	
	@Override
	public String toString() {
		
		return "Nome rubrica: " + nome +" elenco: " + this.elenco();
	}
	
	public void aggiungi(String nome, String cognome, String numTelefono) {
		Voce temp = new Voce(nome, cognome, numTelefono);
		
		if(next<MAX_VOCI) {
			voci[next] = temp;
			next++;
		}
	}
	
	public String primo() {
		String output;
		
		if(next!=0)
			output = voci[0].toString();
		else
			output =  "Rubrica vuota";
		
		return output;
		
// CODICE ALTERNATIVO
//		return voce(1);		
		
		
	}

	public String voce(int indice) {
		String output;
		
		
		if(indice <= 0 )
			output = "Indice deve essere > 0";
		
		if(indice <= next)
			output = voci[indice-1].toString();
		else
			output = "Indice troppo grande";
		
		return output;

// CODICE ALTERNATIVO
//		if(indice < next)
//			return voci[indice-1].toString();
//		else 
//			return "Indice sadfasdf";
		
	}
	
	public String elenco() {
		
		String output = "(";
				
		for ( int i = 0; i <next; i++) {
			if(i!=0)
				output+=", ";
			output += voci[i].toString();
		}


// CODICE ALTERNATIVO
//		boolean primo = true;
//		for(Voce v: voci) {
//			if(v==null)
//				break;
//
//			if(primo) 
//				primo = false;
//			else
//				output+=", ";
//			output += v.toString();
//		}
		
		output += ")";
		
		return output;
	}
	

	
	public String ricerca(String strDaCercare) {
		String output="Voce non presente in elenco";
		
		for(int i=0; i < next; i++) {
			if(voci[i].getNome().contains(strDaCercare) ||
					voci[i].getCognome().contains(strDaCercare)||
					voci[i].getNumTelefono().contains(strDaCercare)) {
				output = "Trovato: " + voci[i].toString();
				break;}
		}
		
		return output;
		
	}

	public String getNome() {
		return nome;
	}
	
}
