package it.polito.po.test;

import org.junit.Test;
import static org.junit.Assert.*;
import diet.*;


public class TestR3_Recipes {

  @Test
  public void testCreateRecipe() {
  	Food dieta = new Food();

  	/*Ricetta r = */ new Recipe("Pasta col Mais",dieta);
  	
	assertTrue(dieta.getRecipe("Pasta col Mais")!=null);
	assertEquals(1,dieta.recipes().size());
  }

  @Test
  public void testRecipe() {
	Food dieta = new Food();
	dieta.defineRawMaterial("Zucchero", 400, 0, 100, 0);
	dieta.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
	dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
	dieta.defineRawMaterial("Olio", 900, 0, 0, 100);
	dieta.defineRawMaterial("Nutella", 530, 6.8, 56, 31);

	Recipe r = new Recipe("Pasta e Nutella",dieta);
  	
	r.addIngredient("Pasta",70);
	r.addIngredient("Nutella",30);
	assertEquals(350 *0.7 + 530 *0.3, r.getCalories(), 0.001);
	assertEquals(12 *0.7 + 6.8 *0.3 , r.getProteins(), 0.001);
	assertEquals(72.2 *0.7 + 56 *0.3, r.getCarbs(), 0.001);
	assertEquals(1.5 *0.7 + 31 *0.3 , r.getFat(), 0.001);
	assertTrue(r.per100g());
  }
	
  @Test
  public void testRecipe2() {
	Food dieta = new Food();
	dieta.defineRawMaterial("Zucchero", 400, 0, 100, 0);
	dieta.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
	dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
	dieta.defineRawMaterial("Olio", 900, 0, 0, 100);

	Recipe r = new Recipe("Pasta col Mais",dieta);
  	
	r.addIngredient("Pasta",70);
	r.addIngredient("Mais",70);
	r.addIngredient("Olio",13);
	
	assertEquals((350 *0.7 + 70 *0.7 + 900 *0.13)*100/153, r.getCalories(), 0.001);
	assertEquals((12 *0.7 + 2.7 *0.7 + 0 *0.13)*100/153, r.getProteins(), 0.001);
	assertEquals((72.2 *0.7 + 12 *0.7 + 0 *0.13)*100/153, r.getCarbs(), 0.001);
	assertEquals((1.5 *0.7 + 1.3 *0.7 + 100 *0.13)*100/153, r.getFat(), 0.001);
  }

}
