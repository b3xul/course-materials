package it.polito.po.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestR1_Patient.class, TestR2_Doctor.class, TestR3_AssignPatient.class, TestR4_LoadFile.class, TestR5_Stats.class})

public class AllTests {
}
