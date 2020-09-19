package it.polito.po.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import schools.Municipality;
import schools.Community;
import schools.Region;
import schools.School;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestR3_ReadData {
	
    static String file;
	static {
		//System.out.println(TestR3_ReadData.class.getResource("."));
		URL resource = TestR3_ReadData.class.getResource("testSchools.csv");
//		if(System.getProperty("os.name").toLowerCase().startsWith("window")){
		File outFile;
		try {
			outFile=File.createTempFile("school", ".csv");
//			File data=new File("data");
//			data.mkdirs();
//			data.deleteOnExit();
//			File outFile = new File(data,"Dati.csv");
			outFile.deleteOnExit();
			System.out.println("Extracting data file... " + outFile.getAbsolutePath());
			try(InputStream in = resource.openStream();
				FileOutputStream out=new FileOutputStream(outFile)){
				byte[]b = new byte[2048];
				int n=0;
				while((n=in.read(b))!=-1){
					out.write(b,0,n);
				}
				file = outFile.getCanonicalPath();
			} 
		}catch (IOException e) {
				file=null;
				System.err.println(e);
				outFile=null;
		}
//		}else{
//			url = resource.toString();
//		}
	}
	
	private Region r ;

	@Before
	public void setUp() throws IOException{
		r = new Region("Piemonte");
	}
	
	
	@Test
	public void testCommunity() throws IOException{
		r.readData(file);
		
		Collection<Community> communities = r.getCommunities();
		assertNotNull("No communities collection returned",communities);

		
		assertEquals("Wrong number of communities found",
					76,communities.size());
		Map<Community.Type,Long> counts = communities.stream().collect(Collectors.groupingBy(Community::getType,Collectors.counting()));
		assertEquals("Wrong number of hill communities",28,counts.get(Community.Type.COLLINARE).longValue());
		assertEquals("Wrong number of mountain communities",48,counts.get(Community.Type.MONTANA).longValue());
	}

	@Test
	public void testMuniciaplities() throws IOException{
		r.readData(file);
		
		Collection<Municipality> municipalities = r.getMunicipalies();
		assertNotNull("No communities municipalities returned",municipalities);
		
		assertEquals("Wrong number of municipalities",
					886,municipalities.size());
		long numProvinces = municipalities.stream().map(Municipality::getProvince).distinct().count();
		assertEquals("Wrong number of provinces",8,numProvinces);
	}

	@Test
	public void testSchools() throws IOException{
		r.readData(file);
		
		Collection<School> schools = r.getSchools();
		assertNotNull("No communities schools returned",schools);
		
		assertEquals("Wrong number of schools",
					4057,schools.size());
		assertEquals("Wrong number of branches",
					4377,schools.stream().flatMap(s->s.getBranches().stream()).count());
		Map<Integer,Long> gradesCount = schools.stream().collect(Collectors.groupingBy(School::getGrade,Collectors.counting()));
		int[] grades = {1,2,3,4};
		long[] gradeNo = {1654,1414,543,446};
		for(int g : grades){
			assertEquals("The number of school with grade " + g+ " is wrong",
						gradeNo[g-1],gradesCount.get(g).longValue());
		}
	}

}
