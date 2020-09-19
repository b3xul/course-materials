package university;

class Rector {
	private String FirstName;
	private String LastName;
	
	public Rector(String FirstName,String LastName) {
		this.FirstName=FirstName;
		this.LastName=LastName;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public String toString() {
		return FirstName + " " + LastName;
	}
}
