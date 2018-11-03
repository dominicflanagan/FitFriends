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

public class RunTimeProgressDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RunTimeProgressDao instance = null;
	protected RunTimeProgressDao() {
		connectionManager = new ConnectionManager();
	}
	public static RunTimeProgressDao getInstance() {
		if(instance == null) {
			instance = new RunTimeProgressDao();
		}
		return instance;
	}

	// Save the RunTimeProgress instance by storing it in your MySQL instance.
	public RunTimeProgress create(RunTimeProgress runTimeProgress) throws SQLException {
		String insertItem = "INSERT INTO RunProgress (MemberId, Created, DistanceMeters, RunTimeSeconds) VALUES (?,?,?,TIME_TO_SEC(?));";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem);
			insertStmt.setInt(1, runTimeProgress.getMemberId());
			insertStmt.setTimestamp(2, new Timestamp(runTimeProgress.getCreated().getTime()));
			insertStmt.setInt(3, runTimeProgress.getDistanceMeters());
			insertStmt.setTimestamp(2, new Timestamp(runTimeProgress.getRunTime().getTime()));

			insertStmt.executeUpdate();

			return runTimeProgress;
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

	// Update the Run Time of the RunTimeProgress instance.
	public RunTimeProgress updateRunTime(RunTimeProgress runTimeProgress, double runTime) throws SQLException {
		String updateItem = "UPDATE RunProgress SET RunTimeSeconds = TIME_TO_SEC(?) WHERE MemberId=? AND Created=? AND DistanceMeters=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItem);
			updateStmt.setInt(2, runTimeProgress.getMemberId());
			updateStmt.setTimestamp(3, new Timestamp(runTimeProgress.getCreated().getTime()));
			updateStmt.setInt(4, runTimeProgress.getDistanceMeters());
			updateStmt.setTimestamp(2, new Timestamp(runTimeProgress.getRunTime().getTime()));
			
	
			updateStmt.executeUpdate();
			
			return runTimeProgress;
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

	
	// Delete the RunTimeProgress instance.
	public RunTimeProgress delete(RunTimeProgress runTimeProgress) throws SQLException {
		String deleteItem = "DELETE FROM RunProgress WHERE MemberId=? AND Created=? AND DistanceMeters=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, runTimeProgress.getMemberId());
			deleteStmt.setTimestamp(2, new Timestamp(runTimeProgress.getCreated().getTime()));
			deleteStmt.setInt(3, runTimeProgress.getDistanceMeters());
			
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the RunTimeProgress instance.
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

	
	// Get the RunTimeProgress record by fetching it from your MySQL instance by its key values
	public RunTimeProgress getRunTimesForUserByRunProgress(RunTimeProgress runTimeProgress) throws SQLException {
		String selectItem = "SELECT R.MemberId, R.Created, R.DistanceMeters, SEC_TO_TIME(R.RunTimeSeconds) AS RunTime " +
				"FROM RunProgress R " + 
				"WHERE R.MemberId=? ORDER BY DistanceMeters, Created;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setInt(1, runTimeProgress.getMemberId());
			selectStmt.setTimestamp(2, new Timestamp(runTimeProgress.getCreated().getTime()));
			selectStmt.setInt(3, runTimeProgress.getDistanceMeters());

			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				Date resultCreated =  new Date(results.getTimestamp("Created").getTime());
				int resultDistance = results.getInt("DistanceMeters");
				Date runTime = results.getDate("RunTime");
				
				RunTimeProgress runTimeProgress1 = new RunTimeProgress(resultMemberId, resultCreated, resultDistance, runTime);
				return runTimeProgress1;
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
	

	// Get the matching RunTimeProgress records by Member ID
	public List<RunTimeProgress> getRunTimeProgressByMemberId(int memberId) throws SQLException {
		List<RunTimeProgress> runTimeProgresss = new ArrayList<RunTimeProgress>();
		String selectRunTimeProgress =
				"SELECT R.MemberId, R.Created, R.DistanceMeters, SEC_TO_TIME(R.RunTimeSeconds) AS RunTime " +
				"FROM RunProgress R " + 
				"WHERE R.MemberId=? ORDER BY DistanceMeters, Created;";
	
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRunTimeProgress);
			selectStmt.setInt(1, memberId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				Date resultCreated =  new Date(results.getTimestamp("Created").getTime());
				int resultDistance = results.getInt("DistanceMeters");
				Date runTime = results.getDate("RunTime");
				
				RunTimeProgress runTimeProgress1 = new RunTimeProgress(resultMemberId, resultCreated, resultDistance, runTime);
				runTimeProgresss.add(runTimeProgress1);
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
		return runTimeProgresss;
	}

	// Get the RunTimeProgress record by fetching it from your MySQL instance by username
	public List<RunTimeProgress> getRunTimeProgressByUserName(String userName) throws SQLException {
		List<RunTimeProgress> runTimeProgress = new ArrayList<RunTimeProgress>();

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
				
				RunTimeProgress runTimeProgress1 = new RunTimeProgress(memberId, created, distance, runTime, resultUserName, firstName, lastName);
				runTimeProgress.add(runTimeProgress1);				
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
		return runTimeProgress;
	}
	
}
