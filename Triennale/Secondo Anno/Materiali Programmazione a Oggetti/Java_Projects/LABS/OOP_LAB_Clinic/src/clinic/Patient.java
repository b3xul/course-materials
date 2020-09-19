package clinic;

public class Patient {
	private String first;
	private String last;
	private String ssn;
	private Doctor doctor;
	
	public Patient(String first, String last, String ssn) {
		this.first=first;
		this.last=last;
		this.ssn=ssn;
		this.doctor=null;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@Override
	public String toString() {
		
		return last + " " + first + " (" + ssn + ")";
	}
}
