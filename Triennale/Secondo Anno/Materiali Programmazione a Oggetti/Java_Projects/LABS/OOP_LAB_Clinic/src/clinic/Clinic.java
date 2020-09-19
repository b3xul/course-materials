package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {

	Map<String,Patient> patients=new HashMap<>();
	Map<Integer,Doctor> doctors=new HashMap<>();
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		patients.put(ssn, new Patient(first,last,ssn));
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		
		checkPatient(ssn);
		
		return patients.get(ssn).toString();
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		doctors.put(docID, new Doctor(first,last,ssn,docID,specialization));
		patients.put(ssn, new Patient(first,last,ssn));
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		
		checkDoctor(docID);
		return doctors.get(docID).toString();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		checkDoctor(docID);
		Doctor tempD=doctors.get(docID);
		
		checkPatient(ssn);
		Patient tempP=patients.get(ssn);
		
		tempP.setDoctor(tempD);
		tempD.addPatient(tempP);
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		checkPatient(ssn);
		Patient tempP=patients.get(ssn);
		if(tempP.getDoctor()==null)
			throw new NoSuchDoctor("Nessun dottore assegnato.");
		
		return tempP.getDoctor().getDocID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSN
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		checkDoctor(id);
		
		return doctors.get(id).getAssigned().stream().map(Patient::getSsn).collect(Collectors.toSet());
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param path the path of the required file
	 * @throws IOException in case of IO error
	 */
	public void loadData(Reader reader) throws IOException {
		try(BufferedReader in=new BufferedReader(reader)){

			in.lines().map(l->l.split("\\s*;\\s*"))	//stream di lista di stringhe
					  .forEach(item->{
						  try{
							  switch(item[0].trim()) {
								  case "P":
									  addPatient(item[1],item[2],item[3].trim());
									  break;
								  case "M":
									  addDoctor(item[2], item[3], item[4], Integer.parseInt(item[1]), item[5].trim());
									  break;
								  default:
									  System.err.println("Tipo di linea sconosciuto");
									  break;
							  }
						  }
						  catch(ArrayIndexOutOfBoundsException e){
								System.err.println("Elemento mancante nella linea");}
						  catch(NumberFormatException e){
								System.err.println("Impossibile convertire campo in int");}
					  });
		}
		//catch(IOExc) no perche' vuole che la si lanci
	}




	/**
	 * Retrieves the collection of doctors that have no patient at all, sorted in alphabetic order.
	 * 
	 * @return the collection of doctors with no patient sorted in alphabetic order (last name and then first name)
	 */
	public Collection<Integer> idleDoctors(){
		
		return 	doctors.values().stream()
				.filter(d->d.getAssigned().isEmpty())
				.sorted(Comparator.comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
				.map(Doctor::getDocID)
				.collect(Collectors.toSet());
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors
	 */
	public Collection<Integer> busyDoctors(){
		Double avg=	doctors.values().stream()
					.mapToInt(d->d.getAssigned().size())
					.average()
					.orElse(0.0);
		
		return 	doctors.values().stream()
				.filter(d->d.getAssigned().size()>avg)
				.map(Doctor::getDocID)
				.collect(Collectors.toSet());
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
										
		return 	doctors.values().stream()
				.sorted(Comparator.comparing((Doctor d)->d.getAssigned().size()).reversed())	//dottori ordinati per numero di pazienti decrescenti
				.map(d->String.format("%3d", d.getAssigned().size()) + " : " + d.getDocID() +  " " + d.getLast() + " " + d.getFirst() )
				.collect(Collectors.toList());
				/*.collect(Collectors.groupingBy ((Doctor d)->d.getAssigned().size() , ()->new TreeMap<Integer,Set<Doctor>>(reverseOrder()) , Collectors.mapping(d->d,Collectors.toSet())));*/
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  specialization 
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic specialization.
	 * 
	 * @return the collection of strings with specialization and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		Map<String,Long> tempM=	patients.values().stream()
								.filter(p->p.getDoctor()!=null)
								.collect(Collectors.groupingBy(p->p.getDoctor().getSpecialization() , TreeMap::new , Collectors.counting()));
					
		return 	tempM.entrySet().stream()
				.sorted(Comparator.comparing(e->e.getValue(),Comparator.reverseOrder()))
				.map(e->String.format("%3d - %s", e.getValue(), e.getKey()))
				.collect(Collectors.toList());
	}
	
	private void checkPatient(String ssn) throws NoSuchPatient{
		if(!patients.containsKey(ssn))
			throw new NoSuchPatient();
	}
	
	private void checkDoctor(Integer docID) throws NoSuchDoctor{
		if(!doctors.containsKey(docID))
			throw new NoSuchDoctor();
	}
}
