package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import FitFriends.model.MeetUps;
import FitFriends.model.UserMeetUps;
import FitFriends.model.UserWorkout;
import FitFriends.model.Users;
import FitFriends.model.Workouts;

public class UserMeetUpsDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static UserMeetUpsDao instance = null;
	protected UserMeetUpsDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserMeetUpsDao getInstance() {
		if(instance == null) {
			instance = new UserMeetUpsDao();
		}
		return instance;
	}

	public UserMeetUps create(UserMeetUps userMeetUp) throws SQLException{
		UsersDao usersDao = UsersDao.getInstance();
		Users user = usersDao.getUserFromUserName(userMeetUp.getUserName());
		MeetUpsDao meetUpsDao = MeetUpsDao.getInstance();
		MeetUps meetUp = meetUpsDao.getMeetUpByName(userMeetUp.getMeetUpName());
		
		String insertUserMeetUp = "INSERT INTO UserMeetUp(MeetUpId,MemberId, MeetUpTime) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserMeetUp);
			insertStmt.setInt(1, userMeetUp.getMeetUpId());
			insertStmt.setInt(2, userMeetUp.getMemberId());
			insertStmt.setTimestamp(3, userMeetUp.getMeetUpTime());
			// insertStmt.setTimestamp(2, new Timestamp(user.getDob().getTime()));

			insertStmt.executeUpdate();
			
			return userMeetUp;
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
			
	//Get all of the meet ups for a username
	public List<UserMeetUps> getAllUserMeetUpsByUserName(String username) throws SQLException {
		List<UserMeetUps> meetUps = new ArrayList<UserMeetUps>();
		String selectMeetUps = "SELECT Persons.UserName, Users.MemberId, MeetUP.MeetUpName, UserMeetUP.MeetUpId, UserMeetUP.MeetUpTime\n" + 
				"FROM Users JOIN UserMeetUp ON Users.MemberId = UserMeetUP.MemberId \n" + 
				"					  JOIN MeetUP ON UserMeetUP.MeetUpId = MeetUP.MeetUpId\n" + 
				"					  JOIN Persons ON Persons.MemberId = Users.MemberId\n" + 
				"WHERE Persons.UserName =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMeetUps);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int meetUpId = results.getInt("MeetUpId");
				String meetUpName = results.getString("MeetUpName");
				int memberId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				Timestamp meetUpTime = results.getTimestamp("MeetUpTime");
									
				meetUps.add(new UserMeetUps(meetUpId, meetUpName, memberId, userName, meetUpTime));
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
		return meetUps;
	}
	
	public UserMeetUps getUserMeetUpByProperties(String meetUpId, String memberId, String meetUpTime) throws SQLException {
		String selectMeetUps = "SELECT *\n" + 
				"FROM UserMeetUP\n" + 
				"WHERE MeetUpId = ? AND MemberId = ? AND MeetUpTime = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMeetUps);
			selectStmt.setInt(1, Integer.parseInt(meetUpId));
			selectStmt.setInt(2, Integer.parseInt(memberId));
			selectStmt.setTimestamp(3, Timestamp.valueOf(meetUpTime));
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultMeetUpId = results.getInt("MeetUpId");
				//String meetUpName = results.getString("MeetUpName");
				int resultMemberId = results.getInt("MemberId");
				//String userName = results.getString("UserName");
				Timestamp resultMeetUpTime = results.getTimestamp("MeetUpTime");
									
				UserMeetUps meetUp = new UserMeetUps(resultMeetUpId, resultMemberId, resultMeetUpTime);
				
				return meetUp;
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
	
	public UserMeetUps updateUserMeetUpTime(UserMeetUps userMeetUp, String newMeetUpTime) throws SQLException {
		String updateMeetUp = "UPDATE UserMeetUP SET MeetUpTime=? WHERE MeetUpId=? AND MemberId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMeetUp);
			updateStmt.setTimestamp(1, Timestamp.valueOf(newMeetUpTime));
			updateStmt.setInt(2, userMeetUp.getMeetUpId());
			updateStmt.setInt(3, userMeetUp.getMemberId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			userMeetUp.setMeetUpTime(Timestamp.valueOf(newMeetUpTime));
			return userMeetUp;
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
	
	public UserMeetUps delete(UserMeetUps userMeetUp) throws SQLException {
		String deleteUserMeetUp = "DELETE FROM UserMeetUP WHERE MeetUpId=? AND MemberId=? AND MeetUpTime=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserMeetUp);
			deleteStmt.setInt(1, userMeetUp.getMeetUpId());
			deleteStmt.setInt(2, userMeetUp.getMemberId());
			deleteStmt.setTimestamp(3, userMeetUp.getMeetUpTime());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete");
			}

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
