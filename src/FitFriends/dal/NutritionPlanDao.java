// FitFriends. October 31, 2018.

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

public class NutritionPlanDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static NutritionPlanDao instance = null;
	protected NutritionPlanDao() {
		connectionManager = new ConnectionManager();
	}
	public static NutritionPlanDao getInstance() {
		if(instance == null) {
			instance = new NutritionPlanDao();
		}
		return instance;
	}

	
	// Get the matching Nutrition plans for a user by UserName
	public List<NutritionPlan> getNutritionPlanForUser(String userName) throws SQLException {
		List<NutritionPlan> nutritionPlans = new ArrayList<NutritionPlan>();
		String selectNutritionPlans =
			"SELECT U.MemberId,UserName,FirstName,LastName, NumberServings, N.* " + 
			"FROM Users U " + 
			"INNER JOIN Persons P ON U.MemberId = P.MemberId " + 
			"INNER JOIN UserNutrition UN ON U.Memberid = UN.MemberId " + 
			"INNER JOIN Nutrition N on N.NutritionId = UN.NutritionId " + 
			"WHERE P.Username = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritionPlans);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int memberId = results.getInt("MemberId");
				String resultUserName = results.getString("UserName");
				 String firstName = results.getString("FirstName");
				 String lastName = results.getString("LastName");
				 int numberServings = results.getInt("NumberServings");
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
				 double solidFats = results.getDouble("Solid_Fats") * results.getInt("NumberServings");
				 double addedSugars = results.getDouble("Added_Sugars") * results.getInt("NumberServings");
				 String alcohol = results.getString("Alcohol");
				 double calories = results.getDouble("Calories") * results.getInt("NumberServings");
				double saturatedFats = results.getDouble("Saturated_Fats") * results.getInt("NumberServings");

				NutritionPlan nutritionPlan = new NutritionPlan(
						memberId,
						 nutritionId,
						 numberServings,
						 resultUserName,
						  firstName,
						  lastName,
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
				
				nutritionPlans.add(nutritionPlan);
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
		return nutritionPlans;
	}
	
	public NutritionPlan getNutritionPlanByUserAndNutrition(String userName, int nutritionId) throws SQLException{
		NutritionPlan nutritionPlan = null;
		String selectNutritionPlan =
			"SELECT U.MemberId,UserName,FirstName,LastName, NumberServings, N.* " + 
			"FROM Users U " + 
			"INNER JOIN Persons P ON U.MemberId = P.MemberId " + 
			"INNER JOIN UserNutrition UN ON U.Memberid = UN.MemberId " + 
			"INNER JOIN Nutrition N on N.NutritionId = UN.NutritionId " + 
			"WHERE P.Username = ? AND N.NutritionId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritionPlan);
			selectStmt.setString(1, userName);
			selectStmt.setInt(2, nutritionId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int memberId = results.getInt("MemberId");
				String resultUserName = results.getString("UserName");
				 String firstName = results.getString("FirstName");
				 String lastName = results.getString("LastName");
				 int numberServings = results.getInt("NumberServings");
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
				 double solidFats = results.getDouble("Solid_Fats") * results.getInt("NumberServings");
				 double addedSugars = results.getDouble("Added_Sugars") * results.getInt("NumberServings");
				 String alcohol = results.getString("Alcohol");
				 double calories = results.getDouble("Calories") * results.getInt("NumberServings");
				double saturatedFats = results.getDouble("Saturated_Fats") * results.getInt("NumberServings");

				nutritionPlan = new NutritionPlan(
						memberId,
						 resultNutritionId,
						 numberServings,
						 resultUserName,
						  firstName,
						  lastName,
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
		return nutritionPlan;
	}
	
	public NutritionPlan getNutritionPlanByMemberIdAndNutrition(int memberId, int nutritionId) throws SQLException{
		NutritionPlan nutritionPlan = null;
		String selectNutritionPlan =
			"SELECT U.MemberId,UserName,FirstName,LastName, NumberServings, N.* " + 
			"FROM Users U " + 
			"INNER JOIN Persons P ON U.MemberId = P.MemberId " + 
			"INNER JOIN UserNutrition UN ON U.Memberid = UN.MemberId " + 
			"INNER JOIN Nutrition N on N.NutritionId = UN.NutritionId " + 
			"WHERE U.MemberId = ? AND N.NutritionId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritionPlan);
			selectStmt.setInt(1, memberId);
			selectStmt.setInt(2, nutritionId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				 String firstName = results.getString("FirstName");
				 String lastName = results.getString("LastName");
				 int numberServings = results.getInt("NumberServings");
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
				 double solidFats = results.getDouble("Solid_Fats") * results.getInt("NumberServings");
				 double addedSugars = results.getDouble("Added_Sugars") * results.getInt("NumberServings");
				 String alcohol = results.getString("Alcohol");
				 double calories = results.getDouble("Calories") * results.getInt("NumberServings");
				double saturatedFats = results.getDouble("Saturated_Fats") * results.getInt("NumberServings");

				nutritionPlan = new NutritionPlan(
						resultMemberId,
						 resultNutritionId,
						 numberServings,
						 userName,
						  firstName,
						  lastName,
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
		return nutritionPlan;
	}
	
	// Update the number of servings for a user food item
	public NutritionPlan updateNumberServings(NutritionPlan nutritionPlan, int newNumberServings) throws SQLException {
		String updateServings = "UPDATE UserNutrition SET NumberServings=? WHERE NutritionID=? and MemberId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateServings);
			updateStmt.setInt(1, newNumberServings);
			updateStmt.setInt(2, nutritionPlan.getNutritionId());
			updateStmt.setInt(3, nutritionPlan.getMemberId());
			updateStmt.executeUpdate();
			
			// Update the number of servings before returning to the caller.
			nutritionPlan.setNumberServings(newNumberServings);
			nutritionPlan.setSolidFats(nutritionPlan.getSolidFats() * newNumberServings);
			nutritionPlan.setAddedSugars(nutritionPlan.getAddedSugars() * newNumberServings);
			nutritionPlan.setCalories(nutritionPlan.getCalories() * newNumberServings);
			nutritionPlan.setSaturatedFats(nutritionPlan.getSaturatedFats() * newNumberServings);

			return nutritionPlan;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public NutritionPlan addItemToNutritionPlan(NutritionPlan nutritionPlan) throws SQLException {
		String addNutritionItem = "INSERT INTO UserNutrition (MemberId, NutritionId, NumberServings) Values (?,?,?);";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(addNutritionItem);
			updateStmt.setInt(1, nutritionPlan.getMemberId());
			updateStmt.setInt(2, nutritionPlan.getNutritionId());
			updateStmt.setInt(3, nutritionPlan.getNumberServings());
			updateStmt.executeUpdate();
			
			// Update the instance before returning to the caller.
			nutritionPlan = this.getNutritionPlanByUserAndNutrition(nutritionPlan.getUserName(), nutritionPlan.getNutritionId());

			return nutritionPlan;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	// Delete the nutrition plan item
	public NutritionPlan delete(NutritionPlan nutritionPlan) throws SQLException {
		String deleteItem = "DELETE FROM UserNutrition WHERE MemberId=? AND NutritionId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, nutritionPlan.getMemberId());
			deleteStmt.setInt(2, nutritionPlan.getNutritionId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the nutrition plan instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
