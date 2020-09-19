package diet;

/**
 * Represent a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	private Food food;
	private String name;
	private double amount;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;
	
	/**
	 * Recipe constructor.
	 * The reference {@code food} of type {@link Food} must be used to
	 * retrieve the information about ingredients. 
	 * 
	 * @param nome unique name of the recipe
	 * @param food object containing the information about ingredients
	 */
	public Recipe(String name, Food food) {
		//food contiene le materie prime che verranno poi usate per aggiungere ingredienti!
		this.name=name;
		this.food=food;
		this.food.addRecipe(this.name, this);		
	}
	
	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material defined with the {@code food}
	 * argument of the constructor.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 */
	public void addIngredient(String material, double quantity) {
		this.amount+=quantity;
		this.calories+=(this.food.getRawMaterial(material).getCalories(quantity));
		this.proteins+=(this.food.getRawMaterial(material).getProteins(quantity));
		this.carbs+=(this.food.getRawMaterial(material).getCarbs(quantity));
		this.fat+=(this.food.getRawMaterial(material).getFat(quantity));		
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getCalories() {
		return this.calories*100/amount;
	}

	@Override
	public double getProteins() {		
		return this.proteins*100/amount;
	}

	@Override
	public double getCarbs() {
		return this.carbs*100/amount;
	}

	@Override
	public double getFat() {
		return this.fat*100/amount;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expressed nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}

}
