package it.polito.po.test;

import clinic.*;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestR3_AssignPatient {

	@Test
	public void testAssignDoctor() throws NoSuchPatient, NoSuchDoctor {
		Clinic s = new Clinic();

		String ssn = "THEPID12I99F181K";
		s.addPatient("Giova", "Reds", ssn);

		int id = 124;
		s.addDoctor("Mario", "White", "THEFIT12I97F181Z", id, "Surgeon");
		s.assignPatientToDoctor(ssn, id);

		int res = s.getAssignedDoctor(ssn);

		assertEquals("Wrong sssigned doctor id.", id, res);
	}

	@Test
	public void testNoAssignedDoctor() throws NoSuchPatient {
		Clinic s = new Clinic();

		String ssn = "THEPID12I99F181K";
		s.addPatient("Giova", "Reds", ssn);

		try {
			s.getAssignedDoctor(ssn);
			fail("There should be no doctor assigned");
		} catch (NoSuchDoctor e) {
			// OK
		}
	}

	@Test
	public void testList() throws NoSuchPatient, NoSuchDoctor {
		Clinic s = new Clinic();

		String ssn1 = "THEPID12I99F181K";
		s.addPatient("Giova", "Reds", ssn1);

		String ssn2 = "BLKSRS11I88F981K";
		s.addPatient("Sirius", "Black", ssn2);

		int id = 124;
		s.addDoctor("Mario", "White", "THEFIT12I97F181Z", id, "Surgeon");
		s.assignPatientToDoctor(ssn1, id);
		s.assignPatientToDoctor(ssn2, id);

		Collection<String> patients = s.getAssignedPatients(id);

		assertNotNull("Missing list of patients assigned to doctor White.",patients);
		assertEquals("Patient list should containt two patients.",2,patients.size());
		assertTrue(patients.contains(ssn1));
		assertTrue(patients.contains(ssn2));
	}

	@Test
	public void testNoList() throws NoSuchPatient, NoSuchDoctor {
		Clinic s = new Clinic();

		String ssn1 = "THEPID12I99F181K";
		s.addPatient("Giova", "Reds", ssn1);

		String ssn2 = "BLKSRS11I88F981K";
		s.addPatient("Sirius", "Black", ssn2);

		int id = 124;
		s.addDoctor("Mario", "White", "THEFIT12I97F181Z", id, "Surgeon");

		Collection<String> patients = s.getAssignedPatients(id);

		assertNotNull("Missing list of patients assigned to doctor White.",patients);
		assertEquals("Patient list should be empty.",0,patients.size());
	}
	// public void testSortedList() throws NoSuchPatient, NoSuchDoctor{
	// Clinic s = new Clinic();
	//
	// String cf3 = "RSSPLA13I88F981K";
	// s.addPatient("Paolo","Reds",cf3);
	//
	// String cf1 = "THEPID12I99F181K";
	// s.addPatient("Giova","Reds",cf1);
	//
	// String cf2 = "BLKSRS11I88F981K";
	// s.addPatient("Sirius","Black",cf2);
	//
	// int mat = 123;
	// s.addDoctor("Mario", "White", "THEFIT12I97F181Z",mat, "Dentist");
	// s.assignPatientToDoctor(cf1,mat);
	// s.assignPatientToDoctor(cf2,mat);
	// s.assignPatientToDoctor(cf3,mat);
	//
	// Doctor m = s.getDoctor(mat);
	//
	// Collection<Person> pazienti = m.getPatients();
	// Iterator<Person> it = pazienti.iterator();
	//
	// Person p1 = (Person)it.next();
	// Person p2 = (Person)it.next();
	// Person p3 = (Person)it.next();
	//
	// assertEquals("Black",p1.getLast());
	// assertEquals("Reds",p2.getLast());
	// assertEquals("Reds",p3.getLast());
	// assertEquals("Paolo",p3.getFirst());
	// }

}
