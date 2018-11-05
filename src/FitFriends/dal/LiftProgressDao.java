// FitFriends. Nov 5, 2018.

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

public class LiftProgressDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static LiftProgressDao instance = null;
	protected LiftProgressDao() {
		connectionManager = new ConnectionManager();
	}
	public static LiftProgressDao getInstance() {
		if(instance == null) {
			instance = new LiftProgressDao();
		}
		return instance;
	}

	// Save the LiftProgress instance by storing it in your MySQL instance.
	public LiftProgress create(LiftProgress liftProgress) throws SQLException {
		String insertItem = "INSERT INTO LiftProgress (MemberId, Created, WeightPounds, NumReps) VALUES (?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem);
			insertStmt.setInt(1, liftProgress.getMemberId());
			insertStmt.setTimestamp(2, new Timestamp(liftProgress.getCreated().getTime()));
			insertStmt.setInt(3, liftProgress.getWeightPounds());
			insertStmt.setInt(4, liftProgress.getNumReps());

			insertStmt.executeUpdate();

			return liftProgress;
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

	// Update the Lift Time of the LiftProgress instance.
	public LiftProgress updateNumReps(LiftProgress liftProgress, double numReps) throws SQLException {
		String updateItem = "UPDATE LiftProgress SET NumReps=? WHERE MemberId=? AND Created=? AND WeightPounds=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItem);
			updateStmt.setInt(2, liftProgress.getMemberId());
			updateStmt.setTimestamp(3, new Timestamp(liftProgress.getCreated().getTime()));
			updateStmt.setInt(4, liftProgress.getWeightPounds());
			updateStmt.setDouble(1, numReps);
	
			updateStmt.executeUpdate();
			
			liftProgress = this.getNumRepsForUserByLiftProgress(liftProgress);
			return liftProgress;
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

	// Delete the LiftProgress instance.
	public LiftProgress delete(LiftProgress liftProgress) throws SQLException {
		String deleteItem = "DELETE FROM LiftProgress WHERE MemberId=? AND Created=? AND WeightPounds=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteItem);
			deleteStmt.setInt(1, liftProgress.getMemberId());
			deleteStmt.setTimestamp(2, new Timestamp(liftProgress.getCreated().getTime()));
			deleteStmt.setInt(3, liftProgress.getWeightPounds());
			
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the LiftProgress instance.
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

	// Get the LiftProgress record by fetching it from your MySQL instance by its key values
	public LiftProgress getNumRepsForUserByLiftProgress(LiftProgress liftProgress) throws SQLException {
		String selectItem = "SELECT L.MemberId, L.Created, L.WeightPounds, L.NumReps, P.UserName, P.FirstName, P.LastName " +
				"FROM LiftProgress L INNER JOIN Persons P ON L.MemberId = P.MemberId " + 
				"WHERE L.MemberId=? AND L.Created=? AND L.WeightPounds=? ORDER BY WeightPounds, Created;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setInt(1, liftProgress.getMemberId());
			selectStmt.setTimestamp(2, new Timestamp(liftProgress.getCreated().getTime()));
			selectStmt.setInt(3, liftProgress.getWeightPounds());

			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				Date resultCreated =  new Date(results.getTimestamp("Created").getTime());
				int resultWeightPounds = results.getInt("WeightPounds");
				int numReps = results.getInt("NumReps");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				LiftProgress liftProgress1 = new LiftProgress(resultMemberId, resultCreated, resultWeightPounds, numReps, userName, firstName, lastName);
				return liftProgress1;
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
	

	// Get the matching LiftProgress records by Member ID
	public List<LiftProgress> getLiftProgressByMemberId(int memberId) throws SQLException {
		List<LiftProgress> liftProgress = new ArrayList<LiftProgress>();
		String selectLiftProgress =
				"SELECT MemberId, Created, WeightPounds, NumReps " +
				"FROM LiftProgress " + 
				"WHERE MemberId=? ORDER BY WeightPounds, Created;";
	
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLiftProgress);
			selectStmt.setInt(1, memberId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				Date resultCreated =  new Date(results.getTimestamp("Created").getTime());
				int resultWeightPounds = results.getInt("WeightPounds");
				int numReps = results.getInt("NumReps");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				LiftProgress liftProgress1 = new LiftProgress(resultMemberId, resultCreated, resultWeightPounds, numReps, userName, firstName, lastName);
				liftProgress.add(liftProgress1);
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
		return liftProgress;
	}

	// Get the LiftProgress record by fetching it from your MySQL instance by username
	public List<LiftProgress> getLiftProgressByUserName(String userName) throws SQLException {
		List<LiftProgress> liftProgress = new ArrayList<LiftProgress>();

		String selectRunTimes = "SELECT L.MemberId, L.Created, L.WeightPounds, L.NumReps, P.UserName, P.FirstName, P.LastName " +
				"FROM LiftProgress L INNER JOIN Persons P ON L.MemberId = P.MemberId " + 
				"WHERE P.UserName=? ORDER BY WeightPounds, Created;";
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
				int weightPounds = results.getInt("WeightPounds");
				int numReps = results.getInt("NumReps");
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				LiftProgress liftProgress1 = new LiftProgress(memberId, created, weightPounds, numReps, resultUserName, firstName, lastName);
				liftProgress.add(liftProgress1);				
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
		return liftProgress;
	}
	
}
