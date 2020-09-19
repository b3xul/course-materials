package clinic;

import java.util.HashSet;
import java.util.Set;

public class Doctor {
	private String first;
	private String last;
	private String ssn;
	private int docID;
	private String specialization;
	private Set<Patient> assigned;
	
	public Doctor(String first, String last, String ssn, int docID, String specialization) {
		this.first=first;
		this.last=last;
		this.ssn=ssn;
		this.docID=docID;
		this.specialization=specialization;
		this.assigned=new HashSet<>();
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

	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public Set<Patient> getAssigned() {
		return assigned;
	}
	
	public void addPatient(Patient p) {
		assigned.add(p);
	}
	@Override
	public String toString() {
		
		return last + " " + first + " (" + ssn + ") [" + docID + "]: " + specialization ;
	}
}