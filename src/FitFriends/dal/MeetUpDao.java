package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import FitFriends.model.MeetUp;
import FitFriends.model.Users;

public class MeetUpDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static MeetUpDao instance = null;
	protected MeetUpDao() {
		super();
	}
	public static MeetUpDao getInstance() {
		if(instance == null) {
			instance = new MeetUpDao();
		}
		return instance;
	}

	//get all list
				
	// Get meetup by meet up id
	public MeetUp getMeetUpById(int meetUpId) throws SQLException {
		String selectMeetUp =
			"SELECT * FROM MeetUP WHERE MeetUpId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMeetUp);
			selectStmt.setInt(1, meetUpId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultMeetUpId = results.getInt("MeetUpId");
				String meetUpName = results.getString("MeetUpName");
				String district = results.getString("District");
				String address = results.getString("Address");
				MeetUp.Type type = MeetUp.Type.valueOf(results.getString("Type"));
									
				MeetUp meetUp = new MeetUp(resultMeetUpId, meetUpName, district, address, type);
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
		
	// Get meetup by meet up name
		public MeetUp getMeetUpByName(String meetUpName) throws SQLException {
			String selectMeetUp =
				"SELECT * FROM MeetUP WHERE MeetUpName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectMeetUp);
				selectStmt.setString(1, meetUpName);
				results = selectStmt.executeQuery();
				if(results.next()) {
					int meetUpId = results.getInt("MeetUpId");
					String resultMeetUpName = results.getString("MeetUpName");
					String district = results.getString("District");
					String address = results.getString("Address");
					MeetUp.Type type = MeetUp.Type.valueOf(results.getString("Type"));
										
					MeetUp meetUp = new MeetUp(meetUpId, resultMeetUpName, district, address, type);
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
	
		//get list by district
		
		//get list by type
}
