package university;

/*throw nella university, nella ExampleApp farò try catch*/
public class University {

	String name;
	Rector rector;
	StudentsDB databaseS;
	CoursesDB databaseC;
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name=name;
		databaseS=new StudentsDB();
		databaseC=new CoursesDB();
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		
		rector=new Rector(first,last);
	}
	
	/**
	 * Retrieves the rector of the university
	 * @return
	 */
	public String getRector(){
		
		return rector.toString();
	}
	
	/**
	 * Enroll a student in the university
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return id number
	 */
	public int enroll(String first, String last){

		return databaseS.add(first,last);
	}
	
	/**
	 * Retrieves the information for a given student
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		
		return databaseS.find(id).toString();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		
		return databaseC.add(title,teacher);
	}
	
	/**
	 * Retrieve the information for a given course
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		
		return databaseC.find(code).toString();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		
		databaseC.find(courseCode).addStudent(studentID);
		databaseS.find(studentID).addCourse(courseCode);
	}
	
	/**
	 * Retrieve a list of attendees
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		StringBuilder list=new StringBuilder();
		int studentID;
		
		for(int i=0;i<databaseC.find(courseCode).getListDim();i++) {
			studentID=databaseC.find(courseCode).getStudentsList()[i];
			list.append(student(studentID));
			list.append('\n');
		}
		
		return list.toString();
	}

	/**
	 * Retrieves the study plan for a student
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		StringBuilder list=new StringBuilder();	//N.B. Dentro i for è + efficiente stringbuilder.append di '+', negli altri casi sarà il compilatore
						//a decidere se vale la pena appoggiarsi a una stringbuilder per fare l'operazione '+'
						// oppure se non ne valga la pena.
		int courseCode;
		
		for(int i=0;i<databaseS.find(studentID).getListDim();i++) {
			courseCode=databaseS.find(studentID).getCoursesList()[i];
			list.append(course(courseCode));
			list.append('\n');
		}
		
		return list.toString();
	}
}
