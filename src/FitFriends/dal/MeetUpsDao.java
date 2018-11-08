package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FitFriends.model.MeetUps;
import FitFriends.model.MeetUps.Type;

public class MeetUpsDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static MeetUpsDao instance = null;
	protected MeetUpsDao() {
		connectionManager = new ConnectionManager();
	}
	public static MeetUpsDao getInstance() {
		if(instance == null) {
			instance = new MeetUpsDao();
		}
		return instance;
	}

			
	//Get all of the meetups
	public List<MeetUps> getAllMeetUps() throws SQLException {
		List<MeetUps> meetups = new ArrayList<MeetUps>();
		String selectMeetUps = "SELECT * FROM MeetUP;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMeetUps);
			results = selectStmt.executeQuery();
			while(results.next()) {
				
				int meetUpId = results.getInt("MeetUpId");
				String meetUpName = results.getString("MeetUpName");
				String district = results.getString("District");
				String address = results.getString("District");
				MeetUps.Type type = MeetUps.Type.valueOf(results.getString("Type"));
									
				meetups.add(new MeetUps(meetUpId, meetUpName, district, address, type));
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
		return meetups;
	}
	
	//Get all of the workouts
	public List<MeetUps> getAllMeetUpsByDistrict(String district) throws SQLException {
		List<MeetUps> meetups = new ArrayList<MeetUps>();
		String selectMeetUps = "SELECT * FROM MeetUP WHERE District =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMeetUps);
			selectStmt.setString(1, district);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
				int meetUpId = results.getInt("MeetUpId");
				String meetUpName = results.getString("MeetUpName");
				String resultDistrict = results.getString("District");
				String address = results.getString("District");
				MeetUps.Type type = MeetUps.Type.valueOf(results.getString("Type"));
									
				meetups.add(new MeetUps(meetUpId, meetUpName, resultDistrict, address, type));
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
		return meetups;
	}
	
	public MeetUps getMeetUpByName(String meetUpName) throws SQLException {
		String selectMeetUp = "SELECT * FROM MeetUP WHERE MeetUpName =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMeetUp);
			selectStmt.setString(1, meetUpName);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
				int meetUpId = results.getInt("MeetUpId");
				String resultMeetUpName = results.getString("MeetUpName");
				String district = results.getString("District");
				String address = results.getString("District");
				MeetUps.Type type = MeetUps.Type.valueOf(results.getString("Type"));
									
				MeetUps meetUp = new MeetUps(meetUpId, resultMeetUpName, district, address, type);
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
	

}
