package it.polito.po.test;

import org.junit.Test;
import static org.junit.Assert.*;
import diet.*;
import java.util.Collection;
import java.util.Iterator;


public class TestR1_RawMaterials {

  @Test
  public void testDefinition(){
  	Food dieta = new Food();
  	
  	int initialSize = dieta.rawMaterials().size();
  	dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
	int finalSize = dieta.rawMaterials().size();
	
	assertEquals(0,initialSize);
	assertEquals(1,finalSize);
  }
  
  @Test
  public void testRawMaterialsCollection(){
	Food dieta = new Food();
  	
	dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
	
	Collection<NutritionalElement> c = dieta.rawMaterials();
	
	NutritionalElement en = (NutritionalElement)c.iterator().next();
	
	assertEquals("Pasta",en.getName());
	assertEquals(350,en.getCalories(),0.001); 
	assertEquals(12,en.getProteins(),0.001); 
	assertEquals(72.2,en.getCarbs(),0.001); 
	assertEquals(1.5,en.getFat(),0.001); 
	assertTrue(en.per100g());
  }

  @Test
  public void testRawMaterials(){
	Food dieta = new Food();
	dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
	NutritionalElement en = dieta.getRawMaterial("Pasta");
	
	assertEquals("Pasta",en.getName());
	assertEquals(350,en.getCalories(),0.001); 
	assertEquals(12,en.getProteins(),0.001); 
	assertEquals(72.2,en.getCarbs(),0.001); 
	assertEquals(1.5,en.getFat(),0.001); 
	assertTrue(en.per100g());
  }

  @Test
  public void testRawMaterialsCollectionsSorted(){
	Food dieta = new Food();
  	
	dieta.defineRawMaterial("Zucchero", 400, 0, 100, 0);
	dieta.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
	dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
	
	Collection<NutritionalElement> c = dieta.rawMaterials();
	Iterator<NutritionalElement> it = c.iterator();
	NutritionalElement prev = it.next();
	while(it.hasNext()) {
		NutritionalElement current=it.next();
		assertTrue(current.getName().compareTo(prev.getName())>=0);
	}
  }

}
