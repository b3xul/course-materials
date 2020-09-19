package university;

class CoursesDB {
	
	private Course[] courses;
	private int code;
	private final int OFFSET=10;
	private final int MAX_COURSES=50;
	
	public CoursesDB() {
		this.courses=new Course[MAX_COURSES];
		this.code=OFFSET;
	}
	
	public int add(String title,String teacher) {
		if(code<OFFSET+MAX_COURSES) {
			courses[code-OFFSET]= new Course(title,teacher,code);
			code++;
		}
		else
			code=-1;
		
		return code-1;
	}
	
	public Course find(int code) {
		//if(id>10000+MAX_COURSES)
			//TODO: lanciare un'eccezione
			
		return courses[code-OFFSET];
	}
}
