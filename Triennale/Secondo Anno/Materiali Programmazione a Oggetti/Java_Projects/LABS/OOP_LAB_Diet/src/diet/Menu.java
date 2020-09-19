package diet;

import java.util.SortedMap;
import java.util.TreeMap;
/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	private Food food;
	private String name;
	private SortedMap<String,NutritionalElement> plates;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;
	/**
	 * Menu constructor.
	 * The reference {@code food} of type {@link Food} must be used to
	 * retrieve the information about recipes and products 
	 * 
	 * @param nome unique name of the menu
	 * @param food object containing the information about products and recipes
	 */
	public Menu(String name, Food food){
		this.name=name;
		this.food=food;
		this.plates= new TreeMap<>();
		this.food.addMenu(this.name,this);
	}
	
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 */
	public void addRecipe(String recipe, double quantity) {
		NutritionalElement rec=food.getRecipe(recipe);
		plates.put(recipe,rec);
		this.calories+=(rec.getCalories(quantity));
		this.proteins+=(rec.getProteins(quantity));
		this.carbs+=(rec.getCarbs(quantity));
		this.fat+=(rec.getFat(quantity));
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 */
	public void addProduct(String product) {
		NutritionalElement prod=food.getProduct(product);
		plates.put(product,prod);

		this.calories+=(prod.getCalories());
		this.proteins+=(prod.getProteins());
		this.carbs+=(prod.getCarbs());
		this.fat+=(prod.getFat());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getCalories() {
		return this.calories;
	}

	@Override
	public double getProteins() {
		
		return this.proteins;
	}

	@Override
	public double getCarbs() {
		return this.carbs;
	}

	@Override
	public double getFat() {
		return this.fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
