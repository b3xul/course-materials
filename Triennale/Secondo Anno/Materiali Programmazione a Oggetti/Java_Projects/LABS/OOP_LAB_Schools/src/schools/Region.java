package schools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

//import static java.util.Comparator.*;

// Hint: to write compact stream expressions
// you can include the following
//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

/**
 * Represents the region and serves as a facade class
 * for the package.
 * 
 * It provides factory methods for creating instances of
 * {@link Community}, {@link Municipality}, {@link School}, and {@link Branch}
 *
 */
public class Region {
	
	private String name;
	private Map<String,Community> communities=new HashMap<>();
	private Map<String,Municipality> municipalities=new HashMap<>();
	private Map<String,School> schools=new HashMap<>();
	/*ricerche con get invece che con filter (liste)*/
	
	/**
	 * Creates a new region with the given name.
	 * @param name the name of the region
	 */
	public Region(String name){
		this.name=name;
	}
	
	/**
	 * Getter method
	 * @return the name of the region
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Retrieves all schools in the region
	 * @return collection of schools
	 */
	public Collection<School> getSchools() {
		/*Ancora più grave passare collezione vera qui perchè è la classe facciata col mondo esterno!
		 * Collection unwritable List?
		 */
		
		return schools.values();	//E' già non modificabile?
	}
	
	/**
	 * Retrieves all the communities
	 * @return the collection of all communities
	 */
	public Collection<Community> getCommunities() {
		return communities.values();
	}
	
	/**
	 * Retrieves all municipalities in the region
	 * @return the collection of municipalities
	 */
	public Collection<Municipality> getMunicipalies() {
		return municipalities.values();
	}
	
	
	// factory methods
	
	/**
	 * Factory method that build a new community of the given type.
	 * The type is {@link Community.Type}
	 * 
	 * @param name name of the community
	 * @param type type of the community
	 * @return the new created community
	 */
	public Community newCommunity(String name, Community.Type type){
		Community comm=new Community(name,type);
		communities.put(name, comm);
		return comm;
	}

	/**
	 * Factory method that build a new municipality.
	 * 
	 * @param nome 		name of the municipality
	 * @param province 	province of the municipality
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String nome, String province){
		//oppure richiamo semplicemente quella a 3 parametri passando come ultimo parametro null ->(1 solo costruttore)
		Municipality mun=new Municipality(nome,province,null);
		municipalities.put(nome, mun);
		return mun;
	}
	
	/**
	 * Factory methods, that build a new municipality that
	 * is part of a community.
	 * 
	 * @param nome 		name of the municipality
	 * @param province 	province of the municipality
	 * @param comunita  community the municipality belongs to 
	 * @return the new created municipality
	 */
	public Municipality newMunicipality(String nome, String province, Community comunita){
		Municipality mun=new Municipality(nome,province,comunita);
		municipalities.put(nome, mun);
		
		if(comunita!=null&&communities.get(comunita.getName())==null)
			communities.put(comunita.getName(),comunita);
		
		return mun;
	}
	
	/**
	 * Factory method that creates a new school
	 * 
	 * @param name    name of the school
	 * @param code    code of the school
	 * @param grade	  grade of the school (1 to 4)
	 * @param description  description of the school
	 * 
	 * @return a new school object
	 */
	public School newSchool(String name, String code, int grade, String description){
		School sch=new School(name,code,grade,description);
		schools.put(code, sch);
		return sch;
	}
	
	/**
	 * Factory method that creates a new school branch
	 * 
	 * @param regionalCode	regional code of the branch
	 * @param municipality	municipality where the branch is located
	 * @param address		address of the branch
	 * @param zipCode		zip code of the branch
	 * @param school		school the branch is part of
	 * @return	the new created branch
	 */
	public Branch newBranch(int regionalCode, Municipality municipality, 
							String address, int zipCode, School school)	{
		Branch bra=new Branch(regionalCode,municipality,address,zipCode,school);
		(schools.get(school.getCode())).addBranch(bra);
		//anche nel municipality
		return bra;
	}
	
	/**
	 * Load data from a file.
	 * 
	 * The file must be a CSV file and it is supposed to contain the following fields:
	 * <ul>
	 *  <li>{@code "Provincia"},   (province)
	 *  <li>{@code "Comune"},		(municipality)
	 *  <li>{@code "Grado Scolastico"}, (school grade)
	 *  <li>{@code "Descrizione Scuola"}, (school description)
	 *  <li>{@code "Cod Sede"}, (branch code)
	 *  <li>{@code "Cod Scuola"}, (school code)
	 *  <li>{@code "Denominazione Scuola"}, (name of the school)
	 *  <li>{@code "Indirizzo e n. civico"}, (address of the branch)
	 *  <li>{@code "C.A.P."}, (zip code of the branch)
	 *  <li>{@code "Comunita Collinare"}, (Hill community)
	 *  <li>{@code "Comunita Montana"},  (Mountain community)
	 * </ul>
	 * 
	 * @param file the path of the file
	 */
	public void readData(String file) {
		List<String> lines = null;
		try(BufferedReader in = new BufferedReader(new FileReader(file))){
			lines = in.lines().collect(toList());
		}catch(IOException e) { System.err.println(e.getMessage()); }
		
		
		// each item of the list contains a line of
		// the CSV file that can be split using "," as separator
		

//		String separator=",\\s*";
//		String [] robe=new String[11];
//		
//		boolean flag=false;
//		for(String line:lines) {
//			if(flag) {
//				
//				robe=line.split(separator);
//				
//				Community communityTemp=null;
//				Municipality municipalityTemp=municipalities.get(robe[1]);
//				School schoolTemp=schools.get(robe[5]);
//				
//				if(robe.length==10&&robe[9].length()!=0 && communities.get(robe[9])==null)
//					communityTemp=this.newCommunity(robe[9], Community.Type.COLLINARE);
//
//				
//				else if(robe.length==11&&robe[10].length()!=0 && communities.get(robe[10])==null)
//					communityTemp=this.newCommunity(robe[10], Community.Type.MONTANA);
//
//				if(!municipalities.containsKey(robe[1]))
//					municipalityTemp=this.newMunicipality(robe[1],robe[0],communityTemp);
//				
//				
//				if(!schools.containsKey(robe[5]))
//					schoolTemp=this.newSchool(robe[6], robe[5], Integer.parseInt(robe[2]), robe[3]);
//				
//				this.newBranch(Integer.parseInt(robe[4]), municipalityTemp, robe[7],Integer.parseInt(robe[8]), schoolTemp);
//				
//			}
//			else
//				flag=true;
//		}
//		
		
		/*N.B. Deve poter leggere sia ",,fine" che ",,nome comunità montanafine" che ",nome comunità collinare,fine"*/
//		/*2)*/
//		int flag=0;
//		String line;
//		Scanner s=null;
//		String separator=",\\s*";
//		for(ListIterator<String> iter=lines.listIterator();iter.hasNext();) {
//			
//			line=iter.next();
//			s=new Scanner(line);
//			s.useDelimiter(separator);
//			s.useLocale(Locale.ITALY);
//			if(flag==0) {
//				for(int i=0;i<11;i++)
//					iter.next();
//				flag=1;
//			}
//			else {
//				while(s.hasNext()) {
//					String province=s.next();
//					String municipality=s.next();	/*da convertire in municipality*/
//					Integer grade=s.nextInt();
//					String description=s.next();
//					String branchCode=s.next();
//					String schoolCode=s.next();
//					String schoolName=s.next();
//					String address=s.next();
//					Integer CAP=s.nextInt();
//					Optional<String> communityName=s.next();	/*da convertire in type*/
//					Optional<String> communityName2=s.next();
//				}
//			}
//			s.close();
//			/*controllo se esiste comunità comune e scuola e in caso non esistano le creo*/
//			
//		}
		/*Gestione null con scanner?*/
		
		/*3) con gli stream?*/
		
		lines.remove(0);
		
		lines.forEach(r->{
			String [] robe=r.split(",");
			
			Community communityTemp=null;
			Municipality municipalityTemp=municipalities.get(robe[1]);
			School schoolTemp=schools.get(robe[5]);
			
			if(robe.length==10&&robe[9].length()!=0 && communities.get(robe[9])==null)
				communityTemp=this.newCommunity(robe[9], Community.Type.COLLINARE);

			
			else if(robe.length==11&&robe[10].length()!=0 && communities.get(robe[10])==null)
				communityTemp=this.newCommunity(robe[10], Community.Type.MONTANA);

			if(!municipalities.containsKey(robe[1]))
				municipalityTemp=this.newMunicipality(robe[1],robe[0],communityTemp);
			
			
			if(!schools.containsKey(robe[5]))
				schoolTemp=this.newSchool(robe[6], robe[5], Integer.parseInt(robe[2]), robe[3]);
			
			this.newBranch(Integer.parseInt(robe[4]), municipalityTemp, robe[7],Integer.parseInt(robe[8]), schoolTemp);
		});
	}

	/**
	 * Counts how many schools there exist for each description
	 * @return a map of school count vs. description
	 */
	public TreeMap<String,Long>countSchoolsPerDescription(){
		TreeMap<String,Long> cSPD=	schools.values().stream()
								.collect(groupingBy(School::getDescription, TreeMap::new, counting()));
		
		return cSPD;
	}

	/**
	 * Count how many school branches there exist for each municipality
	 * @return a map of branch count vs. municipality
	 */
	public TreeMap<String,Long>countBranchesPerMunicipality(){
		
		TreeMap<String,Long> cBPM=	schools.values().stream()	//stream di school
									.map(School::getBranches)	//stream di liste di branch
									.flatMap(Collection::stream)	//stream di branch
									.collect(groupingBy(br->br.getMunicipality().getName(), TreeMap::new, counting()));

		return cBPM;
	}

	/**
	 * Counts the number of school branches per municipality
	 * and groups them by province.
	 * @return a map of maps the inner reports count of branches vs. municipality
	 * 			the outer reports provinces as keys
	 */
	public TreeMap<String,Map<String,Long>>countBranchesPerMunicipalityPerProvince(){
		TreeMap<String,Map<String,Long>> cBPMPP=	schools.values().stream()	//stream di school
													.map(School::getBranches)	//stream di liste di branch
													.flatMap(Collection::stream)	//stream di branch
													.collect(groupingBy(br->br.getMunicipality().getProvince(), TreeMap::new, groupingBy(br->br.getMunicipality().getName(),counting())));

		return cBPMPP;
	}


	/**
	 * returns a list of strings with format
	 * {@code "### - XXXXXX"}, where 
	 * {@code ###} represents the number of schools (not branches)
	 * and {@code XXXXXX} represents the name of the municipality.
	 * If a school has more than one branch in a municipality
	 * it must be counted only once.
	 * 
	 * @return a collection of strings with the counts
	 */
	public Collection<String> countSchoolsPerMunicipality(){
		Map<String,Set<School>> intermedia=schools.values().stream()
		.map(School::getBranches)
		.flatMap(Collection::stream)
		.collect(groupingBy(br->br.getMunicipality().getName(),mapping(Branch::getSchool,toSet())));
		
		return 	intermedia.entrySet().stream()
				.map(e->e.getValue().size() + " - " + e.getKey())
				.collect(toList());
	}
	
	/**
	 * returns a list of strings with format
	 * {@code "### - XXXXXX"}, where 
	 * {@code ###} represents the number of schools (not branches)
	 * and {@code XXXXXX} represents the name of the community.
	 * They are sorted by descending number of schools.
	 * The list must contain only schools having at least
	 * a branch in a municipality part of a community.
	 * 
	 * @return a collection of strings with the counts
	 */
	public List<String> countSchoolsPerCommunity(){
		
		/*conto branches che soddisfano*/
		
		Collection <Branch> branch_ok=	schools.values().stream()
										.map(School::getBranches)
										.flatMap(Collection::stream) //stream di branches
										.filter(s->s.getMunicipality().getCommunity().isPresent())
										.collect(toList());
		branch_ok.stream().limit(10).forEach(System.out::println);
		 System.out.println("---branches ---");
		 
		Map<String,Set<School>> mappaFinale=branch_ok.stream()
											.collect(Collectors.groupingBy(br->br.getMunicipality().getCommunity().get().getName(),
																mapping(Branch::getSchool,toSet())));
		
		/*ORDINAMENTO MAPPA PER VALORI*/
		
		List<String> sortedStrings= mappaFinale.entrySet().stream()
				.sorted((entry1,entry2)->(-entry1.getValue().size()+entry2.getValue().size()))		//chi ha più scuole viene prima
				.map(entry->entry.getValue().size() + " - " + entry.getKey())
				.collect(toList());
		
		//sortedStrings.stream().forEach(System.out::println);
		System.out.println("------------------------");
		mappaFinale.entrySet().stream().limit(10).forEach(System.out::println);
		 System.out.println("---sorted set ---");
		sortedStrings.stream().limit(10).forEach(System.out::println);
		 System.out.println("---sorted strings---");
		 
		return sortedStrings;
	}

	
}
