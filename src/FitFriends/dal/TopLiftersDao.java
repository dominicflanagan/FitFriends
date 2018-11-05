// FitFriends.  November 5, 2018.

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

public class TopLiftersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static TopLiftersDao instance = null;
	protected TopLiftersDao() {
		connectionManager = new ConnectionManager();
	}
	public static TopLiftersDao getInstance() {
		if(instance == null) {
			instance = new TopLiftersDao();
		}
		return instance;
	}

	
	// Get the matching Lifters records by fetching from your MySQL instance list of Persons by LastName
	public List<TopLifters> getTopLifters(int event, int topN) throws SQLException {
		List<TopLifters> topLifters = new ArrayList<TopLifters>();
		String selectLifters =
			"	SELECT P.MemberId, P.UserName, P.FirstName, P.LastName, " + 
			"			LP.NumReps, " + 
			"			(SUM(N.Solid_Fats)*UN.NumberServings) AS SolidFats, " + 
			"           (Sum(N.Saturated_Fats)*UN.NumberServings) AS SatFats, " + 
			"           (SUM(N.Added_Sugars)*UN.NumberServings) AS Sugars, " + 
			"           (SUM(N.Calories)*UN.NumberServings) as TotalCalories " + 
			"	FROM LiftProgress LP " + 
			"	INNER JOIN Users U ON LP.MemberId = U.MemberID " + 
			"	INNER JOIN Persons P ON U.MemberId = P.MemberId " + 
			"	INNER JOIN UserNutrition UN ON UN.MemberId = U.MemberID " + 
			"	INNER JOIN Nutrition N ON N.NutritionId = UN.NutritionId " + 
			"	WHERE LP.WeightPounds = ? " + 
			"	GROUP BY U.MemberId " + 
			"	ORDER BY NumReps DESC, Calories DESC " + 
			"	LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLifters);
			selectStmt.setInt(1, event);
			selectStmt.setInt(2, topN);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int memberId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int numReps = results.getInt("NumReps");
				double solidFats = results.getDouble("SolidFats");
				double satFats = results.getDouble("SatFats");
				double sugars = results.getDouble("Sugars");
				double totalCalories = results.getDouble("TotalCalories");

				TopLifters topLifter = new TopLifters(memberId, userName, firstName, lastName, 
						numReps, solidFats, satFats, sugars, totalCalories);
				topLifters.add(topLifter);
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
		return topLifters;
	}

}
