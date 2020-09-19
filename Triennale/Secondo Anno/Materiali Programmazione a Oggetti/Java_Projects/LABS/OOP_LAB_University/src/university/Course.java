package university;

class Course {
	private static final int MAX_STUDENTS=100;
	
	private String title;
	private String teacher;
	private int code;
	private int[] studentsList;
	private int listDim;
	
	public int[] getStudentsList() {
		return studentsList;
	}
	
	public int getListDim() {
		return listDim;
	}
	
	public Course(String title,String teacher,int code) {
		this.title=title;
		this.teacher=teacher;
		this.code=code;		
		studentsList=new int[MAX_STUDENTS];
		listDim=0;
	}
	
	public void addStudent(int studentID) {
		if(listDim<MAX_STUDENTS) {
			studentsList[listDim]= studentID;
			listDim++;
		}
		
		return;
	}
	
	@Override
	public String toString() {
		return code + " " + title + " " + teacher;
	}
}
