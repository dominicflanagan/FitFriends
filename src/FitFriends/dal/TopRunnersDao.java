// FitFriends.  October 31, 2018.

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

public class TopRunnersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static TopRunnersDao instance = null;
	protected TopRunnersDao() {
		connectionManager = new ConnectionManager();
	}
	public static TopRunnersDao getInstance() {
		if(instance == null) {
			instance = new TopRunnersDao();
		}
		return instance;
	}

	
	// Get the matching Runners records by fetching from your MySQL instance list of Persons by LastName
	public List<TopRunners> getTopRunners(int event, int topN) throws SQLException {
		List<TopRunners> topRunners = new ArrayList<TopRunners>();
		String selectRunners =
			"	SELECT P.MemberId, P.UserName, P.FirstName, P.LastName, " + 
			"			SEC_TO_TIME(RP.RunTimeSeconds) AS RunTime, " + 
			"			(SUM(N.Solid_Fats)*UN.NumberServings) AS SolidFats, " + 
			"           (Sum(N.Saturated_Fats)*UN.NumberServings) AS SatFats, " + 
			"           (SUM(N.Added_Sugars)*UN.NumberServings) AS Sugars, " + 
			"           (SUM(N.Calories)*UN.NumberServings) as TotalCalories " + 
			"	FROM RunProgress RP " + 
			"	INNER JOIN Users U ON RP.MemberId = U.MemberID " + 
			"	INNER JOIN Persons P ON U.MemberId = P.MemberId " + 
			"	INNER JOIN UserNutrition UN ON UN.MemberId = U.MemberID " + 
			"	INNER JOIN Nutrition N ON N.NutritionId = UN.NutritionId " + 
			"	WHERE RP.DistanceMeters = ? " + 
			"	GROUP BY U.MemberId " + 
			"	ORDER BY RunTimeSeconds, Calories DESC " + 
			"	LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRunners);
			selectStmt.setInt(1, event);
			selectStmt.setInt(2, topN);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int memberId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date runTime = results.getDate("RunTime");
				double solidFats = results.getDouble("SolidFats");
				double satFats = results.getDouble("SatFats");
				double sugars = results.getDouble("Sugars");
				double totalCalories = results.getDouble("TotalCalories");

				TopRunners topRunner = new TopRunners(memberId, userName, firstName, lastName, 
						runTime, solidFats, satFats, sugars, totalCalories);
				topRunners.add(topRunner);
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
		return topRunners;
	}

}
