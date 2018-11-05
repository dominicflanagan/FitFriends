// FitFriends. October 30, 2018.

package FitFriends.dal;

import FitFriends.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunProgressDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RunProgressDao instance = null;
	protected RunProgressDao() {
		connectionManager = new ConnectionManager();
	}
	public static RunProgressDao getInstance() {
		if(instance == null) {
			instance = new RunProgressDao();
		}
		return instance;
	}

	// Save the RunProgress instance by storing it in your MySQL instance.
	public RunProgress create(RunProgress runProgress) throws SQLException {
		String insertItem = "INSERT INTO RunProgress (MemberId, Created, DistanceMeters, RunTimeSeconds) VALUES (?,?,?,TIME_TO_SEC(?));";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem);
			insertStmt.setInt(1, runProgress.getMemberId());
			insertStmt.setTimestamp(2, new Timestamp(runProgress.getCreated().getTime()));
			insertStmt.setInt(3, runProgress.getDistanceMeters());
			insertStmt.setTimestamp(2, new Timestamp(runProgress.getRunTime().getTime()));

			insertStmt.executeUpdate();

			return runProgress;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	// Update the Run Time of the RunProgress instance.
	public RunProgress updateRunTime(RunProgress runProgress, double runTime) throws SQLException {
		String updateItem = "UPDATE RunProgress SET RunTimeSeconds=? WHERE MemberId=? AND Created=? AND DistanceMeters=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItem);
			updateStmt.setInt(2, runProgress.getMemberId());
			updateStmt.setTimestamp(3, new Timestamp(runProgress.getCreated().getTime()));
			updateStmt.setInt(4, runProgress.getDistanceMeters());
			updateStmt.setDouble(1, runTime);
	
			updateStmt.executeUpdate();
			
			runProgress = this.getRunTimesForUserByRunProgress(runProgress);
			return runProgress;
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

	// Delete the RunProgress instance.
	public RunProgress delete(RunProgress runProgress) throws SQLException {
		String deleteItem = "DELETE FROM RunProgress WHERE MemberId=? AND Created=? AND DistanceMeters=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, runProgress.getMemberId());
			deleteStmt.setTimestamp(2, new Timestamp(runProgress.getCreated().getTime()));
			deleteStmt.setInt(3, runProgress.getDistanceMeters());
			
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the RunProgress instance.
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

	// Get the RunProgress record by fetching it from your MySQL instance by its key values
	public RunProgress getRunTimesForUserByRunProgress(RunProgress runProgress) throws SQLException {
		String selectItem = "SELECT R.MemberId, R.Created, R.DistanceMeters, SEC_TO_TIME(R.RunTimeSeconds) AS RunTime, P.UserName, P.FirstName, P.LastName " +
				"FROM RunProgress R INNER JOIN Persons P ON R.MemberId = P.MemberId " + 
				"WHERE R.MemberId=? AND R.Created=? AND R.DistanceMeters=? ORDER BY DistanceMeters, Created;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setInt(1, runProgress.getMemberId());
			selectStmt.setTimestamp(2, new Timestamp(runProgress.getCreated().getTime()));
			selectStmt.setInt(3, runProgress.getDistanceMeters());

			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				Date resultCreated =  new Date(results.getTimestamp("Created").getTime());
				int resultDistance = results.getInt("DistanceMeters");
				Date runTime = results.getDate("RunTime");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				RunProgress runProgress1 = new RunProgress(resultMemberId, resultCreated, resultDistance, runTime, userName, firstName, lastName);
				return runProgress1;
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
		return null;
	}
	

	// Get the matching RunProgress records by Member ID
	public List<RunProgress> getRunProgressByMemberId(int memberId) throws SQLException {
		List<RunProgress> runProgress = new ArrayList<RunProgress>();
		String selectRunProgress =
				"SELECT R.MemberId, R.Created, R.DistanceMeters, SEC_TO_TIME(R.RunTimeSeconds) AS RunTime " +
				"FROM RunProgress R " + 
				"WHERE R.MemberId=? ORDER BY DistanceMeters, Created;";
	
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRunProgress);
			selectStmt.setInt(1, memberId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				Date resultCreated =  new Date(results.getTimestamp("Created").getTime());
				int resultDistance = results.getInt("DistanceMeters");
				Date runTime = results.getDate("RunTime");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				RunProgress runProgress1 = new RunProgress(resultMemberId, resultCreated, resultDistance, runTime, userName, firstName, lastName);
				runProgress.add(runProgress1);
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
		return runProgress;
	}

	// Get the RunProgress record by fetching it from your MySQL instance by username
	public List<RunProgress> getRunProgressByUserName(String userName) throws SQLException {
		List<RunProgress> runProgress = new ArrayList<RunProgress>();

		String selectRunTimes = "SELECT R.MemberId, R.Created, R.DistanceMeters, SEC_TO_TIME(R.RunTimeSeconds) AS RunTime, P.UserName, P.FirstName, P.LastName " +
				"FROM RunProgress R INNER JOIN Persons P ON R.MemberId = P.MemberId " + 
				"WHERE P.UserName=? ORDER BY DistanceMeters, Created;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRunTimes);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			while(results.next()) {
				int memberId = results.getInt("MemberId");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				int distance = results.getInt("DistanceMeters");
				Date runTime = results.getDate("RunTime");
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				RunProgress runProgress1 = new RunProgress(memberId, created, distance, runTime, resultUserName, firstName, lastName);
				runProgress.add(runProgress1);				
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
		return runProgress;
	}
	
}
