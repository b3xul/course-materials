package it.polito.po.arrays;


import java.util.Arrays;

import it.polito.po.arrays.university.Student;

public class EsempioArrayStudenti {
	
	public static void main(String[] args) {
		
//		University poliTO = new University("Politecnico di Torino");
		
		Student s1 = new Student(1,"Giuseppe", "Verdi");
		Student s2 = new Student(2,"Mario", "Rossi");
		Student s3 = new Student(3,"Ugo", "Neri");
		Student s4 = new Student(4,"Francesco", "Gialli");
		
		Student[] studenti = new Student[4];
		studenti[0] = s2;
		studenti[1] = s4;
		studenti[2] = s1;
		studenti[3] = s3;
		
		System.out.println(Arrays.toString(studenti));
		
		
		
		Arrays.sort(studenti, (o1,o2)->o1.toString().compareTo(o2.toString()));

		System.out.println(Arrays.toString(studenti));
		
		Arrays.sort(studenti, (o1,o2)->o1.getID()-o2.getID());

		System.out.println(Arrays.toString(studenti));

		Arrays.sort(studenti, (o1,o2)->o1.getFirst().compareTo(o2.getFirst()));
		System.out.println(Arrays.toString(studenti));

		Arrays.sort(studenti, (o1,o2)->o1.getLast().compareTo(o2.getLast()));
		System.out.println(Arrays.toString(studenti));
		
		
		
	}

}
