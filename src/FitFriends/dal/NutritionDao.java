// FitFriends. November 02, 2018.

package FitFriends.dal;

import FitFriends.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NutritionDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static NutritionDao instance = null;
	protected NutritionDao() {
		connectionManager = new ConnectionManager();
	}
	public static NutritionDao getInstance() {
		if(instance == null) {
			instance = new NutritionDao();
		}
		return instance;
	}

	
	// Get all Nutrition items 
	public List<Nutrition> getAllNutritionItems() throws SQLException {
		List<Nutrition> nutritions = new ArrayList<Nutrition>();
		String selectNutritions =
			"SELECT * FROM Nutrition;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritions);
			results = selectStmt.executeQuery();
			while(results.next()) {
				 int nutritionId = results.getInt("NutritionId");
				 int foodCode = results.getInt("Food_Code");
				 String displayName = results.getString("Display_Name");
				 int portionDefault = results.getInt("Portion_Default");
				 String portionAmount = results.getString("Portion_Amount");
				 String portionDisplayName = results.getString("Portion_Display_Name");
				 String factor = results.getString("Factor");
				 String increment = results.getString("Increment");
				 String multiplier = results.getString("Multiplier");
				 String grains = results.getString("Grains");
				 String wholeGrains = results.getString("Whole_Grains");
				 String vegetables = results.getString("Vegetables");
				 String orangeVegetables = results.getString("Orange_Vegetables");
				 String drkgreenVegetables = results.getString("Drkgreen_Vegetables");
				 String starchyVegetables = results.getString("Starchy_vegetables");
				 String otherVegetables = results.getString("Other_Vegetables");
				 String fruits = results.getString("Fruits");
				 String milk = results.getString("Milk");
				 String meats = results.getString("Meats");
				 String soy = results.getString("Soy");
				 String dryBeansPeas = results.getString("Drybeans_Peas");
				 String oils = results.getString("Oils");
				 double solidFats = results.getDouble("Solid_Fats");
				 double addedSugars = results.getDouble("Added_Sugars");
				 String alcohol = results.getString("Alcohol");
				 double calories = results.getDouble("Calories");
				double saturatedFats = results.getDouble("Saturated_Fats");

				Nutrition nutrition = new Nutrition(
						 nutritionId,
						  foodCode,
						  displayName,
						  portionDefault,
						  portionAmount,
						  portionDisplayName,
						  factor,
						  increment,
						  multiplier,
						  grains,
						  wholeGrains,
						  vegetables,
						  orangeVegetables,
						  drkgreenVegetables,
						  starchyVegetables,
						  otherVegetables,
						  fruits,
						  milk,
						  meats,
						  soy,
						  dryBeansPeas,
						  oils,
						  solidFats,
						  addedSugars,
						  alcohol,
						  calories,
						  saturatedFats);
				
				nutritions.add(nutrition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return nutritions;
	}
	
	// Get a specific nutrition item
	public Nutrition getNutritionByNutritionId(int nutritionId) throws SQLException{
		Nutrition nutrition = null;
		String selectNutrition = "SELECT * FROM Nutrition WHERE NutritionId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutrition);
			selectStmt.setInt(1, nutritionId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				 int resultNutritionId = results.getInt("NutritionId");
				 int foodCode = results.getInt("Food_Code");
				 String displayName = results.getString("Display_Name");
				 int portionDefault = results.getInt("Portion_Default");
				 String portionAmount = results.getString("Portion_Amount");
				 String portionDisplayName = results.getString("Portion_Display_Name");
				 String factor = results.getString("Factor");
				 String increment = results.getString("Increment");
				 String multiplier = results.getString("Multiplier");
				 String grains = results.getString("Grains");
				 String wholeGrains = results.getString("Whole_Grains");
				 String vegetables = results.getString("Vegetables");
				 String orangeVegetables = results.getString("Orange_Vegetables");
				 String drkgreenVegetables = results.getString("Drkgreen_Vegetables");
				 String starchyVegetables = results.getString("Starchy_vegetables");
				 String otherVegetables = results.getString("Other_Vegetables");
				 String fruits = results.getString("Fruits");
				 String milk = results.getString("Milk");
				 String meats = results.getString("Meats");
				 String soy = results.getString("Soy");
				 String dryBeansPeas = results.getString("Drybeans_Peas");
				 String oils = results.getString("Oils");
				 double solidFats = results.getDouble("Solid_Fats");
				 double addedSugars = results.getDouble("Added_Sugars");
				 String alcohol = results.getString("Alcohol");
				 double calories = results.getDouble("Calories");
				double saturatedFats = results.getDouble("Saturated_Fats");

				nutrition = new Nutrition(
						 resultNutritionId,
						  foodCode,
						  displayName,
						  portionDefault,
						  portionAmount,
						  portionDisplayName,
						  factor,
						  increment,
						  multiplier,
						  grains,
						  wholeGrains,
						  vegetables,
						  orangeVegetables,
						  drkgreenVegetables,
						  starchyVegetables,
						  otherVegetables,
						  fruits,
						  milk,
						  meats,
						  soy,
						  dryBeansPeas,
						  oils,
						  solidFats,
						  addedSugars,
						  alcohol,
						  calories,
						  saturatedFats);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return nutrition;
	}
	
	// Get a list of Nutrition items for a food type
	public List<Nutrition> getNutritionItemsByFoodType(String foodType) throws SQLException {
		List<Nutrition> nutritions = new ArrayList<Nutrition>();
		String selectNutritions =
			"SELECT * FROM Nutrition WHERE Display_Name LIKE ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritions);
			selectStmt.setString(1, '%' + foodType + '%');
			results = selectStmt.executeQuery();
			while(results.next()) {
				 int nutritionId = results.getInt("NutritionId");
				 int foodCode = results.getInt("Food_Code");
				 String displayName = results.getString("Display_Name");
				 int portionDefault = results.getInt("Portion_Default");
				 String portionAmount = results.getString("Portion_Amount");
				 String portionDisplayName = results.getString("Portion_Display_Name");
				 String factor = results.getString("Factor");
				 String increment = results.getString("Increment");
				 String multiplier = results.getString("Multiplier");
				 String grains = results.getString("Grains");
				 String wholeGrains = results.getString("Whole_Grains");
				 String vegetables = results.getString("Vegetables");
				 String orangeVegetables = results.getString("Orange_Vegetables");
				 String drkgreenVegetables = results.getString("Drkgreen_Vegetables");
				 String starchyVegetables = results.getString("Starchy_vegetables");
				 String otherVegetables = results.getString("Other_Vegetables");
				 String fruits = results.getString("Fruits");
				 String milk = results.getString("Milk");
				 String meats = results.getString("Meats");
				 String soy = results.getString("Soy");
				 String dryBeansPeas = results.getString("Drybeans_Peas");
				 String oils = results.getString("Oils");
				 double solidFats = results.getDouble("Solid_Fats");
				 double addedSugars = results.getDouble("Added_Sugars");
				 String alcohol = results.getString("Alcohol");
				 double calories = results.getDouble("Calories");
				double saturatedFats = results.getDouble("Saturated_Fats");

				Nutrition nutrition = new Nutrition(
						 nutritionId,
						  foodCode,
						  displayName,
						  portionDefault,
						  portionAmount,
						  portionDisplayName,
						  factor,
						  increment,
						  multiplier,
						  grains,
						  wholeGrains,
						  vegetables,
						  orangeVegetables,
						  drkgreenVegetables,
						  starchyVegetables,
						  otherVegetables,
						  fruits,
						  milk,
						  meats,
						  soy,
						  dryBeansPeas,
						  oils,
						  solidFats,
						  addedSugars,
						  alcohol,
						  calories,
						  saturatedFats);
				
				nutritions.add(nutrition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return nutritions;
	}

}
