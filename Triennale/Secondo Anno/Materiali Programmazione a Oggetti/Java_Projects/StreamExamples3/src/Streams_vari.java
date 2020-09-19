import static java.util.Comparator.comparing;							//<-----------import static java.util.Comparator.*
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.counting;						//<-----------import static java.util.stream.Collectors.*	
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Streams_vari {

/*ISSUE MANAGEMENT*/
	/*
	 * SORTEDMAP<CAMPO,COUNTING>
	 * 
	 *	Il metodo countBySeverityOfState() dato uno stato dei ticket fornisce una MAPPA CON IL NUMERO DI TICKET (CLASSE) PER SEVERITY (CAMPO CLASSE),
	 * considerando soltanto i ticket in quello stato oppure tutti i ticket se l'argomento è nullo. La mappa è ORDINATA IN BASE ALLA SEVERITY(CAMPO CLASSE). 
	 	
	 public SortedMap<Ticket.Severity,Long> countBySeverityOfState(Ticket.State state){
    	SortedMap<Ticket.Severity,Long> res;
    	if(state==null) {
    		res=tickets.values().stream()
    				.collect(groupingBy(Ticket::getSeverity, ()->new TreeMap<Ticket.Severity,Long>(), counting()));		<-----------
												                            ^^^^^^^^^^^^^^^^^^^^^^
   o più facilmente	.collect(groupingBy(Ticket::getSeverity, TreeMap::new, counting()));								<-----------ORDINARE MAPPA PER VALUE(CAMPO)
    	}
    	else {
    		res=tickets.values().stream()
    				.filter(t->t.getState()==state)
    				.collect(groupingBy(Ticket::getSeverity, ()->new TreeMap<Ticket.Severity,Long>(), counting()));
    	}
        return res;
    }
    
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	LIST<STRING> CONTENENTE 2 CAMPI
	
	Il metodo topMaintainers() dà una LISTA DI STRINGHE: ogni stringa ha il formato "username:###"
	dove username è l'USERNAME DELL'UTENTE(CAMPO CLASSE) e ### è il numero di ticket chiusi dall'utente (CAMPO CLASSE aggiornato apposta) come maintainer. 
	La lista è ORDINATA PER NUMERO DECRESCENTE di ticket E POI PER USERNAME(CAMPO CLASSE) (in ordine alfabetico).
	
	public List<String> topMaintainers(){
        return users.values().stream()
        		.sorted(comparing(User::getSolved,reverseOrder()).thenComparing(User::getUsername))		<--------------COMPARING SU 2 CAMPI
        		.map(u->u.getUsername() + ":" + u.getSolved())											<--------------RESTITUIRE STRINGHE
        		.collect(toList());
    }
	*/
	
/*PROCUREMENT*/
	/*
	 * MAP<CAMPO APPOSTA, LIST<CLASSE>>
	 * 
	 * •il metodo ordersByProduct() restituisce una MAPPA CHE RAGGRUPPA TUTTI GLI ORDINI(CLASSE) (sia in sospeso che consegnati) (COLLEZIONE apposta)
	  in base al CODICE (CAMPO CLASSE apposta) del prodotto;
	  
	  	public Map<String,List<Order>> ordersByProduct(){
	    return orders.values().stream()
	    		.collect(groupingBy(Order::getProductCode));
	}
	  
	-------------------------------------------------------------------------------------------------------------------------------------------------------  
	
	MAP<CAMPO,COUNTING>
	
	•il metodo orderNBySupplier() restituisce il CONTEGGIO DEGLI ORDINI(CLASSE) completati (CAMPO CLASSE) per CIASCUNO DEI FORNITORI(CAMPO CLASSE)
	(riportati PER NOME(CAMPO CLASSE apposta) ed ORDINATI alfabeticamente). 
	
	public Map<String,Long> orderNBySupplier(){
		List<Order> completati=completedOrders();
		
	    X return completati.stream()
	    X		.map(Order::getSupplierName)
	    X		.sorted()
	    X		.collect(groupingBy(n->n,counting()));)
	   	o ancora più facilmente:
  		completati.stream().collect(groupingBy(Order::getSupplierName , TreeMap::new , counting()));	<------------MAPPA DI CAMPO-CONTEGGIO(CLASSE)
	}
	
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	LIST<STRING> CAMPO-COUNTING CLASSE
	
	•il metodo countDeliveredByProduct() restituisce una LISTA DI STRINGHE,
	ciascuna costituita dal CODICE DEL PRODOTTO(CAMPO CLASSE) ed il CONTEGGIO DEGLI ORDINI(CLASSE) CONSEGNATI(CAMPO CLASSE)
	 separati da " - " (es. "BNN - 10") ORDINTATI in base al NUMERO DECRESCENTI DI ORDINI.
	 
	 public List<Order> completedOrders(){
		return orders.values().stream()
				.filter(o->o.delivered()==true)						
				.sorted(comparing(Order::getProductCode))
				.collect(toList());
	}
	
	List<Order> completati=completedOrders();
		
	    Map<String,Long> code_count=completati.stream()
	    	xxxx.map(Order::getProductCode)	//lista di ProductCode
	    	xxxx.collect(groupingBy(s->s,counting()));
	    		.collect(groupingBy(Order::getProductCode,counting());
	    		
	    return code_count.entrySet().stream()
	    		.sorted(comparing(Map.Entry<String,Long>::getValue,reverseOrder()))		<---------------ORDINARE UNA MAPPA PER VALUE
	    						  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ o.reversed()
	    		.map(e->e.getKey() + " - " + e.getValue())
	    		.collect(toList());
	 */
	
/*TRANSACTION MANAGEMENT*/
	/*
	 * SORTEDMAP<COUNTING,LIST<STRING>> CAMPO SOTTOCLASSE
	 * 
	 * Il metodo SortedMap <Long, List <String>> deliveryRegionsPerNT dà la LISTA DEI NOMI DELLE REGIONI(CAMPO SOTTOCLASSE) (ORDINATI alfabeticamente)
	  che compaiono come REGIONI DI CONSEGNA(CAMPO SOTTOCLASSE) nello stesso numero di TRANSAZIONI(CLASSE).
	  La regione di consegna di una transazione è la regione che include il posto di consegna della richiesta associata alla transazione.
	  La mappa presenta il NUMERO DI TRANSAZIONI IN ORDINE decrescente.
	  
	  	List<Request> richieste=transactions.values().stream()
				.map(Transaction::getRequest)					<---------DA CLASSE A SOTTOCLASSE
				.collect(toList());								XXXXX
																XXXXX EVITABILI
		Map<String,Long> nomeRegione_count=richieste.stream())	XXXXX
				.collect(groupingBy(Request::getPlaceName, TreeMap::new , counting())); 	<---DA SOTTOCLASSE A MAPPA DI CAMPO-CONTEGGIO ORDINATA PER CAMPO
		
		SortedMap<Long,List<String>> finale=nomeRegione_count.entrySet().stream()			
				.collect(groupingBy(Map.Entry::getValue , 									<---INVERTIRE MAPPA E ORDINARLA PER CHIAVI DECRESCENTI (E VALORI CRESCENTI) (PERCHE' MAPPA DI PARTENZA ERA ORDINATA)
														 () -> new TreeMap <Long, List <String>> (reverseOrder()),
																													mapping(Map.Entry::getKey,toList())));
		
		return finale;
		
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	SORTEDMAP<CAMPO,CAMPO>
	
	Il metodo SortedMap <String, Integer> scorePerCarrier (int minimumScore) dà il PUNTEGGIO TOTALE DELLE TRANSAZIONI(CAMPO CLASSE)
	che riguardano lo STESSO TRASPORTATORE(CAMPO CLASSE).
	Le transazioni(CLASSE) con punteggio inferiore al parametro minimumScore sono ignorate. I trasportatori compaiono in ORDINE alfabetico.
		 	
		SortedMap<String,Integer> finale=transactions.values().stream()
			.filter(t->t.getScore()>=minimumScore)
			.collect(groupingBy(Transaction::getCarrierName, TreeMap::new , summingInt(Transaction::getScore)));	<-----MAPPA ORDINATA PER CAMPO E CON SOMMA DI INTERI(CAMPO) COME VALORE
		 
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	SORTEDMAP<CAMPO SOTTOCLASSE,COUNTING CLASSE>
	
	Il metodo SortedMap <String, Long> nTPerProduct dà il numero di transazioni(CLASSE) (soltanto se maggiore di 0) per ID DI PRODOTTO(CAMPO SOTTOCLASSE), con gli id ORDINATI alfabeticamente.
		SortedMap<String,Long> finale=transactions.values().stream()
			.map(Transaction::getRequest)												<---DA CLASSE A SOTTOCLASSE
			.collect(groupingBy(Request::getProductId , TreeMap::new , counting()));	<---DA SOTTOCLASSE MAPPA DI CAMPO-CONTEGGIO ORDINATA PER CAMPO
*/
/*MILLIWAYS*/
	/*
	 * MAP<CLASSE,SOMMA SU CAMPO SOTTOCLASSE>
	 * 
	 * Il metodo statComposition() riassume la composizione dei gruppi, ovvero gli esseri che sono stati seduti al ristorante, secondo la loro razza.
	 Il metodo restituisce una Map con la RAZZA(CLASSE) come chiave, e il NUMERO TOTALE di esseri di tale razza presenti NEI DIVERSI GRUPPI che sono stati seduti.
	1.Struttura dati opzionale da incrementare ogni volta che si siedono nuovi gruppi (contengono MAPPA<RAZZA,INTERO> (per ogni razza quanti elementi ci sono)
	2.Sapendo gruppi seduti:
		return seatedParty.stream()
				.flatMap(e -> e.getComposition().entrySet().stream())					<-----DA STREAM DI CLASSE A CAMPO DELLA SOTTOCLASSE (FLATMAP HALL->MAP<RAZZA,INTERO>->ENTRYSET<RAZZA,INTERO>)
				.collect(groupingBy(Map.Entry<Race,Integer>::getKey, summingInt(Map.Entry::getValue)));   <-----DA STREAM DI ENTRYSET<CLASSE,INTERO> A MAPPA <RAZZA,SOMMA DI TUTTI I NUMERI CON QUELLA RAZZA>	
									^^^^^^^^^^^^^^^^^^^^^^^						
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	LIST<STRING> CAMPO SOTTOCLASSE
	
	Il metodo statFacility() restituisce tutti i SERVIZI(CAMPO DI SOTTOCLASSE) disponibili nel ristorante,
	ORDINATI in base al numero decrescente di SALONI(CLASSE) in cui sono disponibili e, a parità di numero, alfabeticamente.
	1. separando il comparatore
		Comparator<Map.Entry<String,Long>> c=Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder());
				c=c.thenComparing(Map.Entry::getKey);								<-----COMPARATORE SU UNA MAPPA (ENTRYSET) CHE ORDINA
																						  PRIMA I VALUE IN ORDINE DECRESCENTE
																						  E POI LE KEY IN ORDINE CRESCENTE
		
        Map<String,Long> temp=halls.stream().flatMap(h->h.getFacilities().stream())	<----DA STREAM DI CLASSE A CAMPO DELLA SOTTOCLASSE (FLATMAP HALL->SET<STRING->STRING)
        		.collect(Collectors.groupingBy(s->s, Collectors.counting()));		<----COUNTING DI QUANTE VOLTE UN CAMPO DELLA SOTTOCLASSE COMPARE NELLA COLLEZIONE DI CLASSE INIZIALE
        
        return temp.entrySet().stream()
        		.sorted(c)
        		.map(Map.Entry::getKey)
        		.collect(Collectors.toList());
    
    2. comparatore integrato
    		return halls.stream()
    			.flatMap(f -> f.getFacilities().stream())
				.collect(Collectors.groupingBy(x -> x, Collectors.counting()))
				.entrySet().stream()
    			.sorted(comparing(Map.Entry<String,Long>::getValue, reverseOrder()). thenComparing(Map.Entry::getKey))
    			.map(Map.Entry::getKey)
				.collect(Collectors.toList());
				
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	MAP<CAMPO,LISTA<CAMPO>>
	
	Il metodo statHalls() restituisce una Map avente per chiave il NUMERO DI SERVIZI(CAMPO CLASSE) disponibili
	e come valore la lista dei codici dei SALONI(CLASSE) che offrono tale numero di servizi. Sia le chiavi che i saloni sono in ORDINE crescente.
	
			return halls.stream()
				.sorted(comparing(Hall::getId))		//ID ORDINATI				<------ORDINO FUTURI VALORI DELLA MAPPA (CAMPO)
				.collect(groupingBy(Hall::getNumFacilities, TreeMap::new,				 mapping(Hall::getId,toList())));	<-----MAPPA DI <CAMPO,LISTA<CAMPO>> ORDINATA PER CHIAVE E VALORI (PERCHE' ORDINATI PRIMA)
															Numero Facilities ordinato
*/
/*FLIGHT BOOKING*/
/*
 * 	SORTEDMAP<SOMMACAMPO,LIST<CAMPO>> (appropriato)
 * 
 	Il metodo  SortedMap <Integer, List <String>> destinationsPerNSeats()  dà la lista ordinata delle DESTINAZIONI che hanno lo stesso numero di POSTI RICHIESTI
 	(STRUTTURA DATI APPOSTA aggiornata ogni volta che vengono richiesti nuovi posti)
 	i numeri di posti sono ordinati in senso decrescente e sono ottenuti sommando il numero di posti delle richieste relative alla stessa destinazione. 
 	
 	(Aggiornamento mappa)														<------TENERE MAPPA AGGIORNATA INCREMENTANDO I VALORI APPROPRIATI MAN MANO
 		int seatsRequired=0;
		if(destination_seatsRequired.containsKey(destName))							oldN=(composition.containsKey(temp))? composition.get(temp) : 0;
			seatsRequired=destination_seatsRequired.get(destName);					composition.put(temp,oldN+party.getNum(temp));		
			
		
		destination_seatsRequired.put(destName, (seatsRequired+nSeats));
		
 			SortedMap<Integer,List<String>> finale=destination_seatsRequired.entrySet().stream()	
				.collect(Collectors.groupingBy(Map.Entry::getValue,								<------------INVERTIRE MAPPA<STRING,INTEGER> (=MAPPA<INTEGER,LIST<STRING>)
																	()->new TreeMap<Integer,List<String>>(reverseOrder()),
																															mapping(Map.Entry::getKey,toList() ) ) );
	
	-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	SORTEDMAP<CAMPO,SOMMA CAMPO>
	
	Il metodo  SortedMap <String, Integer> revenuesPerFareType()  dà il ricavo per tipo di tariffa, con i tipi di tariffa ordinati.
	Il ricavo si ottiene sommando per ogni prenotazione relativa allo stesso tipo di tariffa il prodotto del numero di posti della richiesta per il prezzo dell'offerta. 
									
		SortedMap<String,Integer> finale=bookings.values().stream()
				.collect(groupingBy(Booking::getFareTypeName, TreeMap::new, summingInt(Booking::getTotalPrice) ) );		<------MAPPA<CAMPO,SOMMA CAMPO INTERO CORRISPONDENTE ALLA CHIAVE>
 */
/*FOOD DELIVERY*/
/*
 * 	DOUBLE somma
 * 
 	Il metodo totalCustomer(String customerId) accetta un codice cliente e restituisce il totale (SOMMA DI CAMPO) dei suoi ordini. 
	
	
		Map<Item,Integer> items;
		
	    public int add(Item item, int qty) {
        
        if(items.containsKey(item)){
            qty += items.get(item).intValue();
        }
        
        items.put(item, qty);
        
        return qty;
    	}	
	    public double getTotal() {
	        return
	        items.entrySet().stream()
	            .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
	            .sum();
	    }
            
	      	double res=orders.stream()
                .filter(o -> o.getCustomer() == c)
                .filter(o->o.getStatus()!=OrderStatus.NEW) // devono essere considerati solo ordini a partire da CONFIRMED
                .mapToDouble(Order::getTotal)					<---------DA CLASSE A CAMPO 
                .sum();
                
     -----------------------------------------------------------------------------------------------------------------------------------------------------------------   
     
      MAPPA<SOMMA CAMPO,STRINGA INDICANTE CLASSE>
	Il metodo bestCustomers() calcola la classifica dei CLIENTI(CLASSE) in base al TOTALE(SOMMA CAMPO) dei loro ordini.
	Restituisce una mappa con CHIAVE IL TOTALE(CAMPO) degli ordini e come VALORI LA LISTA DI CLIENTI(LISTA<CLASSE>) che hanno ordinato quel totale.
	I CLIENTI sono RAPPRESENTATI DA STRINGHE come descritto al requisito R1 e la mappa è ORDINATA per totale decrescente. 
		
		public SortedMap<Double,List<String>> bestCustomers(){
        
    	// mappa Customer (espressi come stringhe come descritto al requisito R1) al loro totale complessivo di tutti gli ordini
        Map<String,Double> totals = orders.stream()
                .filter(o->o.getStatus()!=OrderStatus.NEW) // devono essere considerati solo ordini a partire da CONFIRMED
                .collect(groupingBy(o->o.getCustomer().toString(), //key = Clienti ma espressi come stringhe
                													summingDouble(Order::getTotal))); // Value = la somma (in Double) di tutti gli elementi che hanno la stessa key 
                		

        // mappa totali x: le chiavi diventano value (SortedMap<Double,List<String>>), 
        // value saranno tutti i Customer (stringhe), devo passare per le entry
        return totals.entrySet().stream()
        		.collect(groupingBy(e -> e.getValue(), // key = Double = totali					<----------INVERTIRE MAPPA<STRING,DOUBLE>(=MAPPA<DOUBLE,LIST<STRING)
        											() -> new TreeMap<Double,List<String>>(reverseOrder()), // mapping su TreeMap ordinata in ordine decrescente (di totale);
        																									mapping(e -> e.getKey(),toList() ) ) );
        
        //GENERALE --> se dovete ordinare in base alle key della mappa usate una sorted map 
        // (altrimenti dovrete estrarre le entry ed ordinarle diversamente per poi riottenere la mappa con grouping by o toMap() 
    }
	
	--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	LIST<CLASSE>
	
     * Computes the best items by total amount of orders.
     *  
     * @return the classification

    public List<String> bestItems(){
        return orders.stream().
                .filter(o->o.getStatus()!=OrderStatus.NEW) 	 //solo stati almeno confermati
                .flatMap(o -> o.getItems().stream())		 								<------DA STREAM DI CLASSE a stream di Map.Entry<Item,Integer> DEL CAMPO MAPPA
                .collect(groupingBy(e->e.getKey().getDescription(),							<------MAP<CAMPO DELL'ITEM(CHIAVE DEL CAMPO MAPPA DELLA CLASSE),DOUBLE SOMMA>
                                    								summingDouble(e->e.getValue()*e.getKey().getPrice()) ))	//somma il valore di un ordine=PREZZO ITEM*QUANTITA'
                 //collected in Map<String,Double = valore totale>
                .entrySet().stream() 														<------stream di Map.Entry<String,Double>
                .sorted(comparing(e->-e.getValue())) 			<------------ordinamento MAPPA PER VALORI DECRESCENTI	 N.B. NON PRENDE NE' REVERSEORDER NE' REVERSED!!!!
                .map(r -> r.getKey() + ", " +  String.format("%.2f", r.getValue()))
                .collect(toList());
    }
    
	--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	LIST<CLASSE>
	
     * Computes the most popular items by total quantity ordered.
     *  
     * @return the classification
    public List<String> popularItems(){
        return orders.stream()
                .filter(o->o.getStatus()!=OrderStatus.NEW) // almeno CONFIRMED
                .flatMap(o -> o.getItems().stream()) // stream di Map.Entry<Item,Integer> come prima
                .collect(groupingBy(e->e.getKey().getDescription(),
                                    								summingInt(e->e.getValue()) ))
                .entrySet().stream()  //collected in Map<String,Integer = numero totale>
                .sorted(comparing(e->-e.getValue())) // ordinato in ordine inverso di numero totale
                .map(r -> r.getKey() + ", " +  r.getValue())
                .collect(toList());
    }
    
 */
}
