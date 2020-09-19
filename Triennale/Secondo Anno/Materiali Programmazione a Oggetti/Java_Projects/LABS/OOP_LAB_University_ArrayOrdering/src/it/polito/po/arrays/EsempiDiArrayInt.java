package it.polito.po.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class EsempiDiArrayInt {

	public static void main(String[] args) {
		Integer[] array = {4,6,3,78,34,5,44,2,90,12,45,66,89};
		
		
		Integer[] a = Arrays.copyOf(array, array.length);
		
		Arrays.sort(a);
		
		System.out.println("array iniziale: "+Arrays.toString(array));
		System.out.println("array ordinato: "+Arrays.toString(a));
		
		Arrays.sort(a, (i1,i2)-> i2-i1 );

		System.out.println("array ordinato 2: "+Arrays.toString(a));
		
		Arrays.sort(array, new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return ((int)i1/10)-((int)i2/10);
			}
		});
		
		System.out.println("array ordinato 3: "+Arrays.toString(array));
		

	}

}
