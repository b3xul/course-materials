package it.polito.po.university.students.streamexamples;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.polito.po.university.students.Course;
import it.polito.po.university.students.Student;
import it.polito.po.university.students.Student.Gender;




public class StudentStreams {
	List<Student> students;

	public StudentStreams() {
		students = new LinkedList<>();
		
		// Setup a sample of data structures
		students.add(new Student(100,"John","Smith",Gender.M));
		students.add(new Student(101,"Mary","Johnson",Gender.F));
		students.add(new Student(201,"Andrea","Rossi",Gender.M));
		students.add(new Student(202,"Giulia","Ferrari",Gender.F));
		students.add(new Student(301,"Wei","Wang",Gender.M));
		students.add(new Student(302,"Fang","Li",Gender.F));

		Course c1 = new Course("01ABC","Object-Oriented Programming");
		Course c2 = new Course("02DEF","Databases");
		Course c3 = new Course("03GHI","Computer Networks");

		//prende ogni studente nello stream e lo iscrive a c1
		students.stream().forEach( s -> s.enroll(c1));
//		INVECE DI
//		for(Student s : students){
//			s.enroll(c1);
//		}
		
		//iscrive al corso c2 solo i primi 3 studenti dello stream
		students.stream().limit(3).forEach( s -> s.enroll(c2));
//		INVECE DI
//		for(int i = 0;i<3;i++) {
//			students.get(0).enroll(c2);
//		}
//		
		//prende i primi 3 studenti a partire dal 3° (salta i primi 2)
		//e li iscrive a c3
		students.stream().skip(2).limit(3)//stream di studenti, gli ultimi 3
		 .forEach( s -> s.enroll(c3));
	}
	
	public StudentStreams(List<Student> students) {
		this.students = students;
	}


	/*---------------- Esercizi -------------------*/
	/* Esempi di metodi che lavorano con gli stream*/
	
	
	// scrivere il metodo che ottiene la collezione delle studentesse
	public Collection<Student> femaleStudents(){
		return students.stream() // -->stream di Student, tutti
//				.filter(s -> s.isFemale()) // --> stream di Studenti, ma s.isFemale() == true per tutti
//				// versione alternativa della filter con la sysout per vedere come vengono chiamati i metodi
//				.filter(s -> { System.out.println("Filter: "+s); 
//					return s.isFemale();
//				})
				.filter(Student::isFemale) // versione alternativa con :: (double column)
				.collect(toList()); 
		
//		INVECE DI
//		List<Student> ris = new LinkedList<Student>();
//		for (Student s: students) {
//			if (s.isFemale()) {
//				ris.add(s);
//			}
//		}
//		return ris;
	}

	// ottiene la collezione dei cognomi delle studentesse
	public Collection<String> femaleStudentSurnames(){
		return students.stream()
//				.filter(s -> s.isFemale())
//				.filter(Student::isFemale)
				.filter(s -> { System.out.println("Filter: "+s);
					return s.isFemale();
				})
				.map(s->{
					System.out.println("Map: "+s);
					return s.getLast();
				}) // stream --> non più studenti ma stringhe
//				.map(Student::getLast) // versione veloce da scrivere...
				.collect(toList());

	}	
	
	
	
	// ottiene gli studenti di nome "John"
	public Collection<Student> studentsNamedJohn(){
		return students.stream()
				// metodo lungo e noiso
				// nuova classe che estende Predicate e fa override del suo univo metodo test()
				.filter(
					new Predicate<Student>() {
					@Override
					public boolean test(Student s) {
						return s.getFirst().equals("John");
					}
				})
//				.filter(s -> s.getFirst().equals("John")) // metodo veloce con lambda 
				.collect(toList());
	}
	
	// ottiene la collezione dei cognomi (distinti) degli studenti
	public Collection<String> lastNames(){
			return students.stream()
					.map(Student::getLast)
					.distinct()
					.collect(toList());
					//.collect(toSet()); / alternativo a distinct+collect su toList
	}

	
	// ottiene le lunghezze dei nomi propri degli studenti
		public Collection<Integer> namesLenghts(){
			return students.stream()
					.map(s-> s.getFirst().length())
//					.collect(toList());
//					.collect(toSet());
					.collect(Collectors.toCollection(HashSet::new));
		}

		// ottiene gli studenti iscritti a due o piÃ¹ corsi
		public Collection<Student> studentsWithTwoEnrollments(){
			return students.stream()
					.filter(s -> s.enrolledIn().size()>1)
					.collect(toList());
		}

		// trova il numero di studenti iscritti a 2 o più corsi
		public int numStudentWithTwoEnrollments(){
			return (int)students.stream()
					.filter(s -> s.enrolledIn().size()>1)
					//.collect(Collectors.counting()).intValue();
					.count();
		}

		// ottiene tutti i corsi a cui gli studenti sono iscritti
		// = almeno uno studente iscritto
		public Collection<Course> coursesWithStudents(){
			return students.stream()
//					.map(Student::enrolledIn) // --> List<Course>
//					.flatMap(list->list.stream()) //--> Course
					.flatMap(s -> s.enrolledIn().stream())
					.distinct()
					.collect(toList());
		}

		// ottiene tutti i titoli dei corsi a cui gli studenti sono iscritti (senza ripetizioni)
		public Collection<String> nameCoursesWithStudents(){
			return students.stream()
					.flatMap(s->s.enrolledIn().stream())
					.distinct()
					.map(Course::getTitle)
					.collect(toList());
		}

		// ottiene la lunghezza del cognome più lungo (tra quelli degli studenti) 
		public int maxLastNameLength(){
			return students.stream()
					.mapToInt(s -> s.getLast().length())
					.max().orElse(0);
		}

		// ottiene lo studente con il nome più lungo
		public Optional<Student> studentWithLongestName(){
			return students.stream()
					.collect(Collectors.maxBy(
							Comparator.comparingInt(s->s.getFirst().length())));

		}
		
		// ottiene la lista ordinata alfabeticamente
		// dei nomi propri degli studenti maschi
		public Collection<String> maleNames(){
			return students.stream()
					.filter(Student::isMale)
					.map(Student::getFirst)
					.sorted()
					.collect(toList());
		}

		// ottiene una stringa con la lista ordinata alfabeticamente
		// dei nomi propri degli studenti maschi\
		public String maleNamesString(){
			return students.stream()
					.filter(Student::isMale)
					.map(Student::getFirst)
					.sorted()	
					.collect(Collectors.joining(", ","{","}"));
		}

		// raggruppa gli studenti per genere
		public Map<Student.Gender,List<Student>> studentsByGender(){
			return students.stream()
					.collect(Collectors.groupingBy(Student::getGender,toList()));
		}

		// trova il numero di studenti per genere
		public Map<Student.Gender,Long> numStudentsByGender(){
			return students.stream()
					.collect(Collectors.groupingBy(
							Student::getGender,Collectors.counting()));
		}

		// raggruppa gli studenti per numero di corsi frequentati
		public Map<Long,List<Student>> studentsByNumCourses(){
			return students.stream()
					.collect(Collectors.groupingBy(s->(long)s.enrolledIn().size(),toList()));
		}

		// un metodo che dice se esistono studenti con il nome passato come parametro
		 public Boolean isNamePresent(String name){
			 return students.stream()
					 .anyMatch(s->s.getFirst().equals(name));
		 }
		 
		// un metodo che dice se tutti gli studenti sono di genere maschile
		public Boolean allMales(){
			return students.stream()
//					.filter(Student::isMale)
					.allMatch(s->s.getGender()==Gender.M);			
		}

		//un metodo che dice se nessuno studente è di genere maschile
		public Boolean noMales(){
			return students.stream()
//					.filter(Student::isFemale)
					.noneMatch(s->s.getGender()==Gender.M);
			
		}


		//un metodo che trova il primo studente con nome passato come parametro
		public Optional<Student> getFirstStudentWithName(String name){
			return students.stream()
					.filter(s->s.getFirst().equals(name))
					.findFirst();
//					.findFirst(s->s.getName().equals(name)).;
		}
		
		//un metodo che stampa il numero medio di corsi a cui è iscritto ogni studenti
		public double getAverageCourseNumber(){
			return students.stream()
					.collect(Collectors.averagingInt(s->s.enrolledIn().size()));
			
		}

		//un metodo che stampa informazioni statistiche sul numero di corsi a cui è iscritto ogni studenti
		public IntSummaryStatistics getCourseNumberStat(){
			return students.stream()
					.collect(Collectors.summarizingInt(s->s.enrolledIn().size()));
		}

		

	
	public static void main(String[] args) {
		StudentStreams studStream = new StudentStreams();
		
//		System.out.println(studStream.femaleStudents());
//		System.out.println(studStream.femaleStudentSurnames());		
//		System.out.println(studStream.studentsNamedJohn());
		
//		System.out.println(studStream.lastNames());
//		System.out.println(studStream.namesLenghts());
		
//		System.out.println(studStream.studentsWithTwoEnrollments());
		
		System.out.println(studStream.coursesWithStudents());
		
		System.out.println(studStream.nameCoursesWithStudents());
		
		System.out.println(studStream.maxLastNameLength());
		
//		System.out.println(studStream.studentWithLongestName());
//		
//		System.out.println(studStream.maleNames());

//		System.out.println(studStream.maleNamesString());
		
//		System.out.println(studStream.studentsByGender());
//		System.out.println(studStream.numStudentsByGender());
//		System.out.println(studStream.studentsByNumCourses());
//		
//		System.out.println(studStream.isNamePresent("Johnjhsgdfs"));
		
//		System.out.println(studStream.allMales());
//		System.out.println(studStream.noMales());
		
//		System.out.println(studStream.getFirstStudentWithName("John"));
		Optional<Student> o = studStream.getFirstStudentWithName("Johnatan");
		if(o.isPresent())
			System.out.println(o.get());
		else
			System.out.println("Nessuno");

		System.out.println(studStream.getAverageCourseNumber());
		System.out.println(studStream.getCourseNumberStat());
		

		
	}
	
	
}
