// FitFriends. November 02, 2018.

package FitFriends.model;

public class Nutrition {
	protected int nutritionId;
	protected int foodCode;
	protected String displayName;
	protected int portionDefault;
	protected String portionAmount;
	protected String portionDisplayName;
	protected String factor;
	protected String increment;
	protected String multiplier;
	protected String grains;
	protected String wholeGrains;
	protected String vegetables;
	protected String orangeVegetables;
	protected String drkgreenVegetables;
	protected String starchyVegetables;
	protected String otherVegetables;
	protected String fruits;
	protected String milk;
	protected String meats;
	protected String soy;
	protected String dryBeansPeas;
	protected String oils;
	protected double solidFats;
	protected double addedSugars;
	protected String alcohol;
	protected double calories;
	protected double saturatedFats;
	
	// constructor with all attributes
	public Nutrition(
			int nutritionId,
			 int foodCode,
			 String displayName,
			 int portionDefault,
			 String portionAmount,
			 String portionDisplayName,
			 String factor,
			 String increment,
			 String multiplier,
			 String grains,
			 String wholeGrains,
			 String vegetables,
			 String orangeVegetables,
			 String drkgreenVegetables,
			 String starchyVegetables,
			 String otherVegetables,
			 String fruits,
			 String milk,
			 String meats,
			 String soy,
			 String dryBeansPeas,
			 String oils,
			 double solidFats,
			 double addedSugars,
			 String alcohol,
			 double calories,
			 double saturatedFats) {
		this.nutritionId = nutritionId;
		this.foodCode = foodCode;
		this.displayName = displayName;
		this.portionDefault = portionDefault;
		this.portionAmount = portionAmount;
		this.portionDisplayName = portionDisplayName;
		this.factor = factor;
		this.increment = increment;
		this.multiplier = multiplier;
		this.grains = grains;
		this.wholeGrains = wholeGrains;
		this.vegetables = vegetables;
		this.orangeVegetables = orangeVegetables;
		this.drkgreenVegetables = drkgreenVegetables;
		this.starchyVegetables = starchyVegetables;
		this.otherVegetables = otherVegetables;
		this.fruits = fruits;
		this.milk = milk;
		this.meats = meats;
		this.soy = soy;
		this.dryBeansPeas = dryBeansPeas;
		this.oils = oils;
		this.solidFats = solidFats;
		this.addedSugars = addedSugars;
		this.alcohol = alcohol;
		this.calories = calories;
		this.saturatedFats = saturatedFats;
	}

	// constructor with just the primary key
	public Nutrition(int nutritionId) {
		this.nutritionId = nutritionId;
	}

	public int getNutritionId() {
		return nutritionId;
	}

	public void setNutritionId(int nutritionId) {
		this.nutritionId = nutritionId;
	}

	public int getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(int foodCode) {
		this.foodCode = foodCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getPortionDefault() {
		return portionDefault;
	}

	public void setPortionDefault(int portionDefault) {
		this.portionDefault = portionDefault;
	}

	public String getPortionAmount() {
		return portionAmount;
	}

	public void setPortionAmount(String portionAmount) {
		this.portionAmount = portionAmount;
	}

	public String getPortionDisplayName() {
		return portionDisplayName;
	}

	public void setPortionDisplayName(String portionDisplayName) {
		this.portionDisplayName = portionDisplayName;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	public String getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}

	public String getGrains() {
		return grains;
	}

	public void setGrains(String grains) {
		this.grains = grains;
	}

	public String getWholeGrains() {
		return wholeGrains;
	}

	public void setWholeGrains(String wholeGrains) {
		this.wholeGrains = wholeGrains;
	}

	public String getVegetables() {
		return vegetables;
	}

	public void setVegetables(String vegetables) {
		this.vegetables = vegetables;
	}

	public String getOrangeVegetables() {
		return orangeVegetables;
	}

	public void setOrangeVegetables(String orangeVegetables) {
		this.orangeVegetables = orangeVegetables;
	}

	public String getDrkgreenVegetables() {
		return drkgreenVegetables;
	}

	public void setDrkgreenVegetables(String drkgreenVegetables) {
		this.drkgreenVegetables = drkgreenVegetables;
	}

	public String getStarchyVegetables() {
		return starchyVegetables;
	}

	public void setStarchyVegetables(String starchyVegetables) {
		this.starchyVegetables = starchyVegetables;
	}

	public String getOtherVegetables() {
		return otherVegetables;
	}

	public void setOtherVegetables(String otherVegetables) {
		this.otherVegetables = otherVegetables;
	}

	public String getFruits() {
		return fruits;
	}

	public void setFruits(String fruits) {
		this.fruits = fruits;
	}

	public String getMilk() {
		return milk;
	}

	public void setMilk(String milk) {
		this.milk = milk;
	}

	public String getMeats() {
		return meats;
	}

	public void setMeats(String meats) {
		this.meats = meats;
	}

	public String getSoy() {
		return soy;
	}

	public void setSoy(String soy) {
		this.soy = soy;
	}

	public String getDryBeansPeas() {
		return dryBeansPeas;
	}

	public void setDryBeansPeas(String dryBeansPeas) {
		this.dryBeansPeas = dryBeansPeas;
	}

	public String getOils() {
		return oils;
	}

	public void setOils(String oils) {
		this.oils = oils;
	}

	public double getSolidFats() {
		return solidFats;
	}

	public void setSolidFats(double solidFats) {
		this.solidFats = solidFats;
	}

	public double getAddedSugars() {
		return addedSugars;
	}

	public void setAddedSugars(double addedSugars) {
		this.addedSugars = addedSugars;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getSaturatedFats() {
		return saturatedFats;
	}

	public void setSaturatedFats(double saturatedFats) {
		this.saturatedFats = saturatedFats;
	}


}
