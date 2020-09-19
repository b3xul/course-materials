package university;

class StudentsDB {
	
	private Student[] students;
	private int id;
	private final int OFFSET=10000;
	private final int MAX_STUDENTS=1000;
	
	public StudentsDB() {
		this.students = new Student[MAX_STUDENTS];
		this.id = OFFSET;
	}
	
	public int add(String first,String last) {
		if(id<OFFSET+MAX_STUDENTS) {
			students[id-OFFSET]= new Student(first,last,id);
			id++;
		}
		else
		//TODO: lanciare un'eccezione invece di usare il valore di ritorno, + pulire
			id=-1;
		return id-1;
	}
	
	public Student find(int id) {
		//if(id>10000+MAX_STUDENTS)
			//TODO: lanciare un'eccezione
			
		return students[id-OFFSET];
	}
}
