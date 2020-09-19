package university;

class Student {
	private static final int MAX_COURSES=25;
	
	private String firstName;
	private String lastName;
	private int id;
	private int[] coursesList;
	private int listDim;
	
	public int[] getCoursesList() {
		return coursesList;
	}
	
	public int getListDim() {
		return listDim;
	}
	
	public Student(String firstName,String lastName,int id) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.id=id;		
		coursesList= new int[MAX_COURSES];
		listDim=0;
	}
	
	public void addCourse(int courseCode) {
		if(listDim<MAX_COURSES) {
			coursesList[listDim]= courseCode;
			listDim++;
		}
		
		return;
	}
	
	@Override
	public String toString() {
		return id + " " + firstName + " " + lastName;
	}
	
}
