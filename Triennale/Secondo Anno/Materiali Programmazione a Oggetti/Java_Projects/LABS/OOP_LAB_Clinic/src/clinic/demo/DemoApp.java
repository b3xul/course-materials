package clinic.demo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;

import clinic.Clinic;
import clinic.NoSuchDoctor;
import clinic.NoSuchPatient;


public class DemoApp {

	public static void main(String[] args) throws NoSuchPatient, NoSuchDoctor {

		Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN00A00B123Z");
		System.out.println(clinic.getPatient("ALCGRN00A00B123Z")); // Green Alice (ALCGRN00A00B123Z)
		clinic.addPatient("Robert", "Smith", "RBTSMT00A00B123Z");
		clinic.addPatient("Steve", "Moore", "STVMRE00A00B123Z");
		clinic.addPatient("Susan", "Madison", "SSNMDS00A00B123Z");
		

		clinic.addDoctor("George", "Sun","SNUGRG00A00B123Z", 14,"Physician");
		System.out.println(clinic.getDoctor(14)); // Sun George (SNUGRG00A00B123Z) [14]: Physician
		clinic.addDoctor("Kate", "Love", "LVOKTA00A00B123Z",86,"Physician");
		clinic.addDoctor("Felix", "Donothing", "FLXNTH00A00B123Z",9,"Homeopatist");

		clinic.assignPatientToDoctor("SSNMDS00A00B123Z", 86);
		clinic.assignPatientToDoctor("ALCGRN00A00B123Z", 9);
		clinic.assignPatientToDoctor("RBTSMT00A00B123Z", 9);
		clinic.assignPatientToDoctor("STVMRE00A00B123Z", 9);
		System.out.println(clinic.getAssignedDoctor("SSNMDS00A00B123Z")); // 86
		System.out.println(clinic.getAssignedPatients(14)); // [ALCGRN00A00B123Z, STVMRE00A00B123Z, RBTSMT00A00B123Z]
		
		try {
			clinic.getDoctor(-1);
		} catch (NoSuchDoctor ex) {
			System.out.println("Expected exception: " + ex);
		}
		
		String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
				"P;Giuseppe; Verdi ; VRDGPP76F09B666I \n" + // added spaces
				"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

	  	Clinic s = new Clinic();
	  	try{
	  		s.loadData(new StringReader(normale));
	  		try{
		  		String p = s.getPatient("VRDGPP76F09B666I");
		  	  	assertNotNull("Missing patient Verdi",p);
		  	  	assertTrue("Verdi is missing", p.contains("Verdi"));
		  	}catch(NoSuchPatient e){
		  		fail("Extra white spaces must be removed from SSN.");
		  	}
	  	}
	  	catch(IOException ex){}
	  	
  		
		System.out.println(clinic.idleDoctors()); // [9]
		System.out.println(clinic.busyDoctors()); // [14]
		
		System.out.println(clinic.doctorsByNumPatients()); // [  3 : 14 Sun George,   1 : 86 Love Kate]
		System.out.println(clinic.countPatientsPerSpecialization()); // [  4 - Physician]
	}

}
