package it.polito.po.arrays.university;

public class University {
	
	public static final int MAX_STUDENTS = 1000;
	public static final int MAX_COURSES = 50;
	public static final int MAX_COURSES_PER_STUDENT = 25;
	public static final int MAX_STUDENTS_PER_COURSE = 100;

	public final static int INITIAL_ID = 10000;
	public final static int INITIAL_CODE = 10;
	
	private String name;
	private String rector;
	
	private Student[] include;
	private int nextId = INITIAL_ID;
	
	private Course[] offers;
	private int nextCode = INITIAL_CODE;
	
	

	public University(String name){
		this.name = name;
		this.rector = "<none>";
		
		include = new Student[MAX_STUDENTS];
		offers = new Course[MAX_COURSES];
	}
	
	 
	
	public String getName(){
		return name;
	}
	
	public void setRector(String first, String last){
		this.rector = first + " " + last;
	}
	
	public String getRector(){
		return rector;
	}
	
	// ***************************************** //
/*	È possibile inserire le informazioni relative ad un nuovo studente 
 *  tramite il metodo enroll() della classe University, che riceve come 
 *  parametri il nome ed il cognome dello studente; il metodo reststituisce 
 *  il numero di matricola che è stato assegnato allo studente.

	I numeri di matricola vengono assegnati, in maniera progressiva per 
	ogni ateneo a partire dal numero 10000.

	Si assuma che ciascun ateneo non possa contenere più di 1000 studenti.
*/	
	public int enroll(String first, String last){
		Student s = new Student( nextId , first, last);
		//TODO: creare classe Student
		
		include[ nextId - INITIAL_ID ] = s;
		
		return nextId++;
	}
/*	
	Per ottenere le informazioni relative ad uno studente si utilizza 
	il metodo student() che riceve come parametro la matricola e 
	restituisce una stringa composta da numero di matricola, nome e 
	cognome separati da spazi.	
*/	
	public String student(int id){
		//TODO: creare classe toString di Course
		Student s = include[ id - INITIAL_ID ];
		return s.toString();
	}
	
/*	Per definire un nuovo insegnamento si utilizza il metodo activate() 
 *  che riceve come parametri il titolo del corso e il nome del docente 
 *  titolare. Il metodo restituisce un intero che corrisponde al codice 
 *  del corso. I codici vengono assegnati progressivamente a partire da 10.

	Si assuma che ciascun ateneo non possa attivare più di 50 insegnamenti.
*/	
	public int activate(String title, String teacher){
		Course c = new Course(nextCode,title,teacher);
		//TODO: creare classe course
		
		offers[nextCode - INITIAL_CODE] = c;
		return nextCode++;
	}
	
/*	Per conoscere le informazioni relative ad un corso si usa il metodo 
	course() che riceve come parametro il codice del corso e resituisce 
	una stringa contenente codice, titolo e titolare del corso.
*/	
	public String course(int code){
		//TODO: creare classe toString di Course
		return offers[code-INITIAL_CODE].toString();
	}
	
/*	Gli studenti possono essere iscritti agli insegnamenti tramite il metodo 
 *  register() che riceve come parametro la matricola dello studente ed il 
 *  codice del corso a cui iscriverlo.
 *  
 *  Si assuma che ciascuno studente non possa essere iscritto a più di 25 
 *  insegnamenti e che un insegnamento non possa avere più di 100 iscritti.
*/
	
	public void register(int studentID, int courseCode){
		Student s = include[studentID-INITIAL_ID];
		Course c = offers[courseCode-INITIAL_CODE];
		
		s.enroll(c);
		c.enroll(s);
	}
	
/*	Per ottenere l'elenco degli iscritti ad un insegnamento è disponibile il 
 *  metodo listAttendees() che riceve come parametro il codice 
 *  dell'insegnamento e restituisce una stringa contenente l'elenco degli 
 *  studenti iscritti.
 *  
 *  Gli studenti compaiono uno per riga (le righe sono terminate da un 
 *  a-capo "\n") secondo il formato descritto al punto R2.
*/	
	public String listAttendees(int courseCode){
		Course c = offers[courseCode-INITIAL_CODE];
		return c.attendees();
	}

/*	Data la matricola di uno studente, tramite il metodo studyPlan(), è 
 * possibile conoscere l'elenco degli insegnamenti a cui è iscritto, 
 * gli insegnamenti sono descritti come al punto precedente.
*/
	
	public String studyPlan(int studentID){
		Student s = include[studentID-INITIAL_ID];
		return s.courses();
	}
}