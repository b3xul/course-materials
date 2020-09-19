package succo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Facciata {

	int i=0;
	Sensata [] vettore=new Sensata[10];
	List<Sensata> lista=new ArrayList<>();
	
	public Facciata() {}
	
	public void addSensata (String name,int numero, double flow) {
		
		vettore[i++]=new Sensata(name,numero, flow);
		lista.add(new Sensata(name,numero,flow));
	}
	
	public void leggiETogli () {
		Iterator<Sensata> iterator=lista.iterator();
		Sensata temp;
		
		while(iterator.hasNext()) {
			temp=iterator.next();
			System.out.println(temp);
			iterator.remove();
		}
		
		System.out.println(lista.size());
	}
	
	public void ordinamiNumber (){
		lista.sort(compNumber);
		System.out.println(lista);
	}
	
	public void ordinamiF (){
		lista.sort(compFlow);
		System.out.println(lista);
	}
	
	public void ordinamiName (){
		lista.sort((s1,s2)->;
		System.out.println(lista);
	}
	/*Comparatore con classe anonima*/
	
	Comparator<Sensata> compNumber=new Comparator<Sensata>() {
		public int compare(Sensata s0, Sensata s1) {
			return (s0.getNumero()-s1.getNumero());
		}
	};
	
	Comparator<Sensata> compFlow=new Comparator<Sensata>() {
		public int compare(Sensata s0, Sensata s1) {
			return (s0.getFlow()<s1.getFlow())?-1:1;
		}
	};
	
	/*Comparatore con interfaccia funzionale*/
//	Comparator<Sensata> compName=new Comparator<Sensata>() {
//		comparing(Sensata::getName);
//	}
	
	
}
