package it.polito.po.test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import clinic.*;

public class TestR2_Doctor {
	// "<Last> <First> (<SSN>) [<ID>]: <Specialization>"
	static Pattern doctorFormat = Pattern.compile(
			"\\s*([a-zA-Z',-]+)\\s*([a-zA-Z',-]+)\\s*\\(\\s*(\\w{16})\\s*\\)\\s*\\[(\\d+)\\]\\s*:\\s*(\\w+)\\s*");

  @Test
  public void testAdd() throws NoSuchDoctor {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEPID12I99F181K";
    String name = "Giova";
    String surname = "Reds";
    String specialization = "Surgeon";
	s.addDoctor(name, surname, ssn,id, specialization);

    String d = s.getDoctor(id);
    
    assertNotNull("Missing doctor with id " + id, d);
    
    Matcher m = doctorFormat.matcher(d);
    assertTrue("Wrong format for doctor info: " + d,m.matches());

    assertEquals("Wrong Name in doctor info",name, m.group(2));
    assertEquals("Wrong Last name in doctor info",surname, m.group(1));
    assertEquals("Wrong Id in doctor info",(""+id), m.group(4));
    assertEquals("Wrong specialization in doctor info",specialization, m.group(5));

  }

  @Test
 public void testAddDoctorGetPatient() {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEFIT12I97F181Z";
    String name = "Mario";
    String surname = "White";
    s.addDoctor(name, surname, ssn,id, "Surgeon");

    try {
      String p = s.getPatient(ssn);
      assertNotNull("Doctor "+surname+" should be lister among the patients too",p);
      assertTrue("Wrong name in doctor "+surname+" info", p.contains(name));
    } catch (NoSuchPatient e) {
      fail("A doctor should be a patient too");
    }

  }

  @Test
  public void testNotFound() {
    Clinic s = new Clinic();

    int id = 123;
    String ssn = "THEFIT12I97F181Z";
    String name = "Mario";
    String surname = "White";
    s.addDoctor(name, surname, ssn,id, "Surgeon");

    try {
      /*Doctor p =*/ s.getDoctor(id + 1000);
      fail("There should be no doctor with such id");
    } catch (NoSuchDoctor e) {
      assertTrue(true);
    }
  }

}
