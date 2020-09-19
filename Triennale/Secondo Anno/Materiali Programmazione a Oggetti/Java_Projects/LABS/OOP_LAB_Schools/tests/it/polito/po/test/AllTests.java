package it.polito.po.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestR1_Municipality.class, 
				TestR2_Schools.class, 
				TestR3_ReadData.class, 
				TestR4_Queries.class })
public class AllTests {}
