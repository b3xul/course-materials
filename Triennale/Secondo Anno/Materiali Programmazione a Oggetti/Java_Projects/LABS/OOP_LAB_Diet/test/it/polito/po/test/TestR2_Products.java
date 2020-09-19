package it.polito.po.test;
import java.util.Collection;
import java.util.Iterator;

import diet.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestR2_Products {

  @Test
  public void testDefinition(){
  	Food food = new Food();
  	
  	int initialSize = food.products().size();
  	food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
	int finalSize = food.products().size();
	
	assertEquals(0,initialSize);
	assertEquals(1,finalSize);
  }
  
  @Test
  public void testProductColleciton(){
	Food food = new Food();
  	
	food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
	
	Collection<NutritionalElement> c = food.products();
	
	NutritionalElement en = (NutritionalElement)c.iterator().next();
	
	assertEquals("Cracker",en.getName());
	assertEquals(111,en.getCalories(),0.001); 
	assertEquals(2.6,en.getProteins(),0.001); 
	assertEquals(17.2,en.getCarbs(),0.001); 
	assertEquals(3.5,en.getFat(),0.001); 
	assertFalse(en.per100g());
  }

  @Test
  public void testProduct(){
	Food food = new Food();
	food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
	NutritionalElement en = food.getProduct("Cracker");
	
	assertEquals("Cracker",en.getName());
	assertEquals(111,en.getCalories(),0.001); 
	assertEquals(2.6,en.getProteins(),0.001); 
	assertEquals(17.2,en.getCarbs(),0.001); 
	assertEquals(3.5,en.getFat(),0.001); 
	assertFalse(en.per100g());
  }
  
  @Test
  public void testProductCollectionSorted(){
	Food food = new Food();
  	
	food.defineProduct("Cornetto Cioccolato", 230, 3, 27, 11);
	food.defineProduct("Barretta Bueno", 122, 2, 10.6, 8);
	food.defineProduct("Mozzarella Light", 206, 25, 2, 11.25);
	food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);

	Collection<NutritionalElement> c = food.products();
	Iterator<NutritionalElement> it = c.iterator();
	NutritionalElement prev = it.next();
	while(it.hasNext()) {
		NutritionalElement current=it.next();
		assertTrue(current.getName().compareTo(prev.getName())>=0);
	}
  }

}
