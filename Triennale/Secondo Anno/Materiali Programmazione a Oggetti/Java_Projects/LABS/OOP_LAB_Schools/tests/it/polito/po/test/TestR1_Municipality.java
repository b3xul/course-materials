package it.polito.po.test;

import java.util.Collection;
import schools.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestR1_Municipality  {
    static final String MOUNTAINT_COMMUNITY = "ALTA VALLE SUSA";  // mountain community
	static final String HILL_COMMUNITY = "ALTO MONFERRATO"; // hill community
	private Region r ;
	
	@Before
	public void setUp(){
		r = new Region("Piemonte");
	}
	
	@Test
	public void testCommunity(){	
		Community cm = r.newCommunity(MOUNTAINT_COMMUNITY, Community.Type.MONTANA);
		Community cc = r.newCommunity(HILL_COMMUNITY, Community.Type.COLLINARE);
	
		assertNotNull("No community returned by newCommunity()",cm);
		assertNotNull("No community returned by newCommunity()",cc);
		
		assertEquals("Wrong name for mountain community",
					MOUNTAINT_COMMUNITY,cm.getName());
		assertEquals("Wrong name for hill community",
					HILL_COMMUNITY,cc.getName());
		
		assertEquals("Wrong type for mountain community",
					Community.Type.MONTANA,cm.getType());
		assertEquals("Wrong type for hil community",
					Community.Type.COLLINARE,cc.getType());
	}
	@Test
	public void testGetCommunity(){	
		Community cm = r.newCommunity(MOUNTAINT_COMMUNITY, Community.Type.MONTANA);
		Community cc = r.newCommunity(HILL_COMMUNITY, Community.Type.COLLINARE);

		Collection<Community> lc = r.getCommunities();
		assertNotNull("No cummunity collection returned",lc);
		
		assertEquals("Wrong number of communities,",
					2,lc.size());
		assertTrue("Missing a community",lc.contains(cm));
		assertTrue("Missing a community",lc.contains(cc));
	}
	@Test
	public void testMunicipalityConCommunity(){	
		Community cm = r.newCommunity(MOUNTAINT_COMMUNITY, Community.Type.MONTANA);
		Community cc = r.newCommunity(HILL_COMMUNITY, Community.Type.COLLINARE);
		Municipality c1 = r.newMunicipality("Susa","TORINO",cm);
		Municipality c2 = r.newMunicipality("Nizza Monferrato","ASTI",cc);
	
		assertNotNull("Could not create a municipality belonging to community",c1);
		assertNotNull("Could not create a municipality belonging to community",c2);
		
		assertEquals("Wrong municipality name",
					"Susa",c1.getName());
		assertEquals("Wrong municipality name",
					"Nizza Monferrato",c2.getName());
		
		assertEquals("Wrong province name",
					"TORINO",c1.getProvince());
		
		assertTrue("Missing community for municipality", 
					c1.getCommunity().isPresent());
		assertTrue("Missing community for municipality", 
					c2.getCommunity().isPresent());
		
		assertEquals("Wrong community for municipality", 
					cm,c1.getCommunity().get());
		assertEquals("Wrong community for municipality", 
					cc,c2.getCommunity().get());
	}
	@Test
	public void testMunicipalityNoCommunity(){	
		Municipality c1 = r.newMunicipality("Torino","TORINO");
		Municipality c2 = r.newMunicipality("Nizza Monferrato","ASTI");
	
		assertNotNull("Could not create a municipality",c1);
		assertNotNull("Could not create a municipality",c2);
		
		assertEquals("Wrong municipality name",
					"Torino",c1.getName());
		assertEquals("Wrong municipality name",
					"Nizza Monferrato",c2.getName());
		
		assertEquals("Wrong province name",
					"TORINO",c1.getProvince());
	}
	
	@Test
	public void testGetMunicipality(){	
		Community cm = r.newCommunity(MOUNTAINT_COMMUNITY, Community.Type.MONTANA);
		Community cc = r.newCommunity(HILL_COMMUNITY, Community.Type.COLLINARE);
		Municipality m0 = r.newMunicipality("Torino","TORINO");
		Municipality m1 = r.newMunicipality("SUSA","TORINO",cm);
		Municipality m2 = r.newMunicipality("Nizza Monferrato","ASTI",cc);

		Collection<Municipality> lc = r.getMunicipalies();
		assertNotNull("No municipality collection returned",lc);

		assertEquals("Wrong number of municipalities found",
					3,lc.size());
		assertTrue("Missing municipality " + m0.getName(),lc.contains(m0));
		assertTrue("Missing municipality " + m1.getName(),lc.contains(m1));
		assertTrue("Missing municipality " + m2.getName(),lc.contains(m2));
	}
	
}
