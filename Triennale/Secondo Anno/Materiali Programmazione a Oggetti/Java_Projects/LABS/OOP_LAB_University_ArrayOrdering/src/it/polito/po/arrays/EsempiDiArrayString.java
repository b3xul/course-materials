package it.polito.po.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class EsempiDiArrayString {

	public static void main(String[] args) {
		String[] array = {"quattro","sei","tre","cinque", "due", "dieci"};
		
	
		String[] a = Arrays.copyOf(array, array.length);
		String[] b = Arrays.copyOf(array, array.length);
		
		System.out.println("Uguali:"+Arrays.equals(a, b));
		
		Arrays.sort(a);
		
		System.out.println("Uguali: "+Arrays.equals(a, b));
		
		System.out.println("array iniziale: "+Arrays.toString(array));
		System.out.println("array ordinato: "+Arrays.toString(a));
		
		//in base al valore 'semantico'
		Arrays.sort(a, new Comparator<String>() {
			public int compare(String s1, String s2) {
			return str_to_int(s1)-str_to_int(s2);}
		});
		
		System.out.println("array ordinato 2: "+Arrays.toString(a));
		
		Arrays.sort(a, (s1,s2)-> -str_to_int(s1)+str_to_int(s2));
		
		
		System.out.println("array ordinato 3: "+Arrays.toString(a));
	
		
//		System.out.println("Uguali:"+Arrays.equals(a, b));
//		Arrays.sort(b, (s1,s2)-> -str_to_int(s1)+str_to_int(s2));
//		System.out.println("Uguali:"+Arrays.equals(a, b));
//		
//		String[] c = Arrays.copyOf(a, a.length+1);
//		System.out.println("Uguali:"+Arrays.equals(a, c));
//		
//		
		int index = Arrays.binarySearch(a, "uno");
		int index2 = Arrays.binarySearch(a, "due");
		
		System.out.println("Ricerca uno: "+index);
		System.out.println("Ricerca due: "+index2);
		
		Arrays.sort(a);
		index = Arrays.binarySearch(a, "uno");
		index2 = Arrays.binarySearch(a, "due");
		System.out.println("Ricerca uno: "+index);
		System.out.println("Ricerca due: "+index2);
		System.out.println(Arrays.toString(a));
		System.out.println(a[index2]);
	}
	
	
	private static int str_to_int(String s1) {
		int output=-1;
		
		if(s1.equals("uno")) output=1;
		if(s1.equals("due")) output=2;
		if(s1.equals("tre")) output=3;
		if(s1.equals("quattro")) output=4;
		if(s1.equals("cinque")) output=5;
		if(s1.equals("sei")) output=6;
		if(s1.equals("sette")) output=7;
		if(s1.equals("otto")) output=8;
		if(s1.equals("nove")) output=9;
		if(s1.equals("dieci")) output=10;
		
		return output;
	}

	private class StringNumberComparator implements Comparator<String>{

		@Override
		public int compare(String s1, String s2) {
			return str_to_int(s1)-str_to_int(s2);
		}
		
	}

}


