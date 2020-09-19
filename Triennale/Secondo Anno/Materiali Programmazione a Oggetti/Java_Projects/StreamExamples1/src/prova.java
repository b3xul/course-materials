import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import static java.util.Comparator.comparing;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import Examples.*;


public class prova {
	public static void main(String[] args) {
//		List<Libro> grossi=Libro.listaLibri.stream().filter(libro->libro.getPagine()>100).collect(Collectors.toList());
//		List<Libro> grossi2=Libro.listaLibri.stream().filter(libro->libro.getPagine()>100).collect(toList());
//		System.out.println(grossi);
//		System.out.println(grossi2);
//		List<Libro> grossi3=Arrays.stream(Libro.libri).filter(libro->libro.getPagine()>100).collect(toList());
//		System.out.println(grossi3);
//		Set<String> grossi4=Libro.listaLibri.stream().filter(l->l.getPagine()>100).map(libro->libro.getTitolo()).collect(Collectors.toSet());
//		System.out.println(grossi4);
//		SortedSet<String> grossi5=Libro.listaLibri.stream().filter(l->l.getPagine()>100).map(libro->libro.getTitolo()).collect(Collectors.toCollection(TreeSet::new));
//		System.out.println(grossi5);
//		SortedSet<String> grossi6=	Libro.listaLibri.stream().
//									filter(l->l.getPagine()>100). 	
//									map(libro->libro.getTitolo()).
//									collect(toCollection( ()->new TreeSet<String>(Collections.reverseOrder() ) ));
//		System.out.println(grossi6);
//		
//		String titoli=	Libro.listaLibri.stream()
//						.map(Libro::getTitolo)	//Nome classe!
//						.collect(joining(",","{", "}"));
//		System.out.println(titoli);
//		
//		Integer somma=	Libro.listaLibri.stream()
//						.mapToInt(Libro::getPagine)
//						.sum();
//		System.out.println(somma);
//		
//		int somma2=	Arrays.stream(Libro.libri)
//					.peek(l-> System.out.println("Sbircio: " + l + "->" + l.getPagine()))
//					.collect(summingInt(l->l.getPagine()));
//		System.out.println(somma2);
//		
//		Map<String,List<Libro>> gruppoAutori=	Libro.listaLibri.stream()
//												.collect(groupingBy(Libro::getAutore));
//		System.out.println(gruppoAutori);
//		
//		SortedMap<String,List<Libro>> gruppoAutori2=	Libro.listaLibri.stream()
//				.collect(groupingBy(l->l.getAutore(),TreeMap::new,toList()));
//		System.out.println(gruppoAutori2);
//		
//		Map<String,Map<Integer,List<Libro>>> gruppoAutoriPagine=	Libro.listaLibri.stream()
//																	.collect(groupingBy(Libro::getAutore,groupingBy(l->l.getPagine())));
//		System.out.println(gruppoAutoriPagine);
//		
//		SortedMap<String,Map<Integer,List<Libro>>> gruppoAutoriPagine2=	Libro.listaLibri.stream()
//				//.peek(l-> System.out.println("Sbircio: " + l + " - " + l.getAutore() + " - " + l.getPagine()))
//				.sorted(comparing(Libro::getTitolo))
//				//.peek(l-> System.out.println("SbircioDopo: " + l + " - " + l.getAutore() + " - " + l.getPagine()))
//				.collect(groupingBy(l->l.getAutore(),TreeMap::new,groupingBy(l->l.getPagine())));
//		System.out.println(gruppoAutoriPagine2);
//		
//		SortedMap<String,Map<Integer,List<Libro>>> gruppoAutoriPagine3=	Libro.listaLibri.stream()
//				.peek(l-> System.out.println("Sbircio: " + l + " - " + l.getAutore() + " - " + l.getPagine()))
//				.sorted(comparing(Libro::getPagine))
//				.peek(l-> System.out.println("SbircioDopo: " + l + " - " + l.getAutore() + " - " + l.getPagine()))
//				.collect(groupingBy(l->l.getAutore(),
//									TreeMap::new,
//									groupingBy(l->l.getPagine())));
//		System.out.println(gruppoAutoriPagine3);
		
//		Map<Integer,List<Libro>> ordinePagine= Libro.listaLibri.stream()
//				//.peek(l-> System.out.println("Sbircio: " + l + " - " + l.getPagine()))
//				//.sorted(comparing(Libro::getPagine)) Non serve se ho treeMap con Pagine come chiavi
//				//.peek(l-> System.out.println("SbircioDopo: " + l + " - " + l.getPagine()))
//				.collect(groupingBy(Libro::getPagine,TreeMap::new,toList()));	
//		System.out.println(ordinePagine);
		
//		Map<String,Integer> base=Libro.listaLibri.stream()
//				.collect(toMap(Libro::getTitolo,Libro::getPagine));
//		System.out.println(base);
//		
//		
//		Set<String> sol=base.entrySet().stream()
//				.sorted((a,b)->a.getValue()-(b.getValue()))
//				.map(entry->entry.getKey())
//				.collect(toSet());
//		System.out.println(sol);
				
			
//.collect(toMap(l->l.getTitolo(),l->l.getPagine(),(l1,l2)->l1,()->new TreeMap<String,Integer>(Collections.reverseOrder())));


//		Arrays.stream(OrdineCliente.ordiniCliente)
//				.flatMap(ordine->ordine.getLibri().stream())
//				.forEach(s->System.out.println(s));
//		List<Libro> richiesti=Arrays.stream(OrdineCliente.ordiniCliente)
//				.flatMap(ordine->ordine.getLibri().stream())
//				.collect(toList());
//		
//		System.out.println("-------\n"+richiesti);
		
//		Map<String,Long> numeroCopie=OrdineCliente.getStream()
//				.flatMap(ordine->ordine.getLibri().stream())
//				.collect(groupingBy(Libro::getTitolo,counting()));
//		System.out.println(numeroCopie);
//		
		/*Invertire una mappa*/
		/*Stream Sequenziali separati*/	
		
		SortedMap<String,Long> numeroCopieordT=OrdineCliente.getStream()
				.flatMap(ordine->ordine.getLibri().stream())
				.collect(groupingBy(Libro::getTitolo,TreeMap::new,counting()));
		System.out.println(numeroCopieordT);
	
		SortedMap<Long,List<String>> TitoliperCopie=numeroCopieordT.entrySet().stream()	//entryset->metodo di map che restituisce un Set di Map.Entry:
																						//: arancio=2, azzurro=1->si possono chiamare getKey e getValue
				.peek(l->System.out.println(l))
				.collect(groupingBy(e->e.getValue(),
									TreeMap::new,
									mapping(e->e.getKey(),toList())));

		System.out.println(TitoliperCopie);
/**/
/*Stream Sequenziali congiunti:evito la creazione della mappa numerocopieordT,anche se internamente la crea*/
		SortedMap<Long,List<String>> TitoliperCopie2=OrdineCliente.getStream()
				.flatMap(ordine->ordine.getLibri().stream())
				.collect(groupingBy(Libro::getTitolo,TreeMap::new,counting()))
				.entrySet().stream()
				.peek(l->System.out.println(l))
				.collect(groupingBy(e->e.getValue(),
									TreeMap::new,
									mapping(e->e.getKey(),toList())));
		System.out.println(TitoliperCopie2);
/**/
		Map<String,Map<String,Long>> ordineEditor=OrdineCliente.getStream()
				.flatMap(ord->ord.getLibri().stream())
				.collect(groupingBy(Libro::getEditore,groupingBy(Libro::getTitolo,counting())));
		System.out.println(ordineEditor);
		
//		List<OrdineEditore> ordineEditop=ordineEditor.entrySet().stream()
//				.map(ed->{	OrdineEditore nuovo=new OrdineEditore(ed.getKey());
//							ed.getValue().forEach((k,v)->nuovo.addLinea(k, v.intValue()));
//							return nuovo;
//						 })
//		//.forEach(OrdineCliente.OrdineEditore.Linea(String::toString,l->l))
//				.collect(toList());
//		System.out.println(ordineEditop);
	}
	

}
