package it.polito.po.test;
import java.io.IOException;
import java.io.StringReader;

import clinic.*;

import static org.junit.Assert.*;
import org.junit.Test;



public class TestR4_LoadFile {

//  private static void writeFile(String fileName,String content) {
//  	try{
//  		FileOutputStream fos = new FileOutputStream(fileName);
//  		fos.write(content.getBytes());
//  		fos.close();
//	}catch(IOException ioe){
//		throw new RuntimeException(ioe.getMessage());
//	}
//  }


  
  @Test
  public void testLoading() throws NoSuchPatient, NoSuchDoctor, IOException{
  	String regular = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon";
  	
  	Clinic s = new Clinic();
  	s.loadData(new StringReader(regular));
  	String p = s.getPatient("VRDGPP76F09B666I");
  	assertNotNull("Missing patient Verdi",p);
  	assertTrue("Wrong name for patient Verdi", p.contains("Verdi"));

	String d = s.getDoctor(345);
	assertNotNull("Missing doctor Bianchi",d);
	assertTrue("Wrong name for doctor Bianchi", d.contains("Bianchi"));
  }

  @Test
  public void testTrivialErrors() throws IOException, NoSuchPatient, NoSuchDoctor{
	String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
				"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
				"P;Giuseppe;VRDGPP76F09B444I\n" + // missing last name 
				"M;Mario;Bianchi;BNCMRA44C99A320Y;Surgeon\n" + // missing id
				"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

  	Clinic s = new Clinic();
  	s.loadData(new StringReader(normale));
  	String p = s.getPatient("VRDGPP76F09B666I");
  	assertNotNull("Missing patient Verdi",p);
  	assertTrue("Wrong name for Verdi", p.contains("Verdi"));

	String d = s.getDoctor(345);
	assertNotNull("Missing doctor Bianchi",d);
	assertTrue("Wrong name for doctor Bianchi", d.contains("Bianchi"));
  }
  
  @Test
  public void testSpecialErrorsExtraBlanks() throws IOException {
		String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe; Verdi ; VRDGPP76F09B666I \n" + // added spaces
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

	  	Clinic s = new Clinic();
	  	s.loadData(new StringReader(normale));
	  	try{
	  		String p = s.getPatient("VRDGPP76F09B666I");
	  	  	assertNotNull("Missing patient Verdi",p);
	  	  	assertTrue("Verdi is missing", p.contains("Verdi"));
	  	}catch(NoSuchPatient e){
	  		fail("Extra white spaces must be removed form SSN.");
	  	}

  }

  @Test
  public void testSpecialErrorsEmptyLine() throws IOException {
		String normale = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe; Verdi;VRDGPP76F09B666I\n" + 
					"\n" + // empty line 
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

	  	Clinic s = new Clinic();
	  	s.loadData(new StringReader(normale));
	  	try{
	  		String d = s.getDoctor(345);
	  		assertNotNull("Missing doctor Bianchi",d);
	  		assertTrue("Wrong name doctor Bianchi", d.contains("Bianchi"));
	  	}catch(NoSuchDoctor e){
	  		fail("Wrong lines must be skipped.");
	  	}

  }
}
