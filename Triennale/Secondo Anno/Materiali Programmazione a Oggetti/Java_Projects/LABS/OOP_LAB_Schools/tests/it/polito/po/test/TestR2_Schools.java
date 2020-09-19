package it.polito.po.test;

import java.util.Collection;

import schools.Municipality;
import schools.Community;
import schools.Region;
import schools.School;
import schools.Branch;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class TestR2_Schools {
	private static final String COM_MONTANA = "ALTA VALLE SUSA";
	private static final String COM_COLLINARE = "ALTO MONFERRATO";
	Region r;
	Community cm;
	Community cc;
	Municipality m0;
	Municipality m1;
	Municipality m2;

	@Before
	public void setUp() {
		r = new Region("Piemonte");
		cm = r.newCommunity(COM_MONTANA, Community.Type.MONTANA);
		cc = r.newCommunity(COM_COLLINARE, Community.Type.COLLINARE);
		m0 = r.newMunicipality("Torino", "TORINO");
		m1 = r.newMunicipality("SUSA", "TORINO", cm);
		m2 = r.newMunicipality("Nizza Monferrato", "ASTI", cc);
	}

	@Test
	public void testSchool() {
		School s = r.newSchool("School Media Pinocchio", "SM1234", 2, "School Media Statale");

		assertNotNull("Could not create a new school",s);

		assertEquals("Wrong school name",
					"School Media Pinocchio", s.getName());
		assertEquals("Wrong school code",
					"SM1234", s.getCode());
		assertEquals("Wrong school description",
					"School Media Statale", s.getDescription());
	}

	@Test
	public void testBranch() {
		School s = r.newSchool("School Media Pinocchio", "SM1234", 2, "School Media Statale");
		Branch b = r.newBranch(12345, m0, "Via Collodi 1", 10199, s);

		assertNotNull("Could not create a new branch",b);

		assertEquals("Wrong branch code",12345, b.getCode());
		assertEquals("Wrong branch municipality",m0, b.getMunicipality());
		assertEquals("Wrong branch school",s, b.getSchool());
		assertEquals("Wrong branch address","Via Collodi 1", b.getAddress());
		assertEquals("Wrong branch zip code",10199, b.getCAP());
	}

	@Test
	public void testGetSchools() {
		School s1 = r.newSchool("School Media Pinocchio", "SM1234", 2, "School Media Statale");
		School s2 = r.newSchool("School Media Mangiafuoco", "SM1A76", 2, "School Primaria");

		Collection<School> ls = r.getSchools();
		assertNotNull("No school collection returned",ls);

		assertEquals("Wrong number of schools returned",
					2, ls.size());
		assertTrue("Missing school",ls.contains(s1));
		assertTrue("Missing school",ls.contains(s2));
	}

	@Test
	public void testGetBranches() {
		School s1 = r.newSchool("School Media Pinocchio", "SM1234", 2, "School Media Statale");
		School s2 = r.newSchool("School Media Mangiafuoco", "SM1A76", 2, "School Primaria");
		Branch b1 = r.newBranch(12345, m0, "Via Collodi 1", 10199, s1);
		Branch b2 = r.newBranch(12367, m1, "Via Manzoni 5", 10199, s2);
		Branch b3 = r.newBranch(12398, m2, "Via Roma 9", 14099, s2);

		Collection<Branch> lb = s2.getBranches();
		assertNotNull("No branch collection returned",lb);

		assertEquals("Wrong numner of branches returned",2, lb.size());
		assertTrue("Missing branch",lb.contains(b2));
		assertTrue("Missing branch",lb.contains(b3));

		assertTrue("Branch not found among the relative school's brances",
					s1.getBranches().contains(b1));
	}
}
