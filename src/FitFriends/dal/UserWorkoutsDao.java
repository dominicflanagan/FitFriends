package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import FitFriends.model.UserWorkout;
import FitFriends.model.Workouts;

public class UserWorkoutsDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static UserWorkoutsDao instance = null;
	protected UserWorkoutsDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserWorkoutsDao getInstance() {
		if(instance == null) {
			instance = new UserWorkoutsDao();
		}
		return instance;
	}

	public UserWorkout create(UserWorkout userWorkout) throws SQLException{
		String insertUserWorkout = "INSERT INTO UserWorkout(WorkoutId,MemberId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserWorkout);
			insertStmt.setInt(1, userWorkout.getWorkoutId());
			insertStmt.setInt(2, userWorkout.getMemberId());

			insertStmt.executeUpdate();
			
			return userWorkout;
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
			
	//Get all of the workouts for a username
	public List<Workouts> getAllWorkoutsByUserName(String username) throws SQLException {
		List<Workouts> workouts = new ArrayList<Workouts>();
		String selectWorkouts = "SELECT Persons.UserName, Users.MemberId, UserWorkout.WorkoutId, Workout.WorkoutDescription, Workout.Intensity\n" + 
				"FROM Users JOIN UserWorkout ON Users.MemberId = UserWorkout.MemberId\n" + 
				"				JOIN Workout ON UserWorkout.WorkoutId = Workout.WorkoutId\n" + 
				"            	JOIN Persons ON Persons.MemberId = Users.MemberId\n" + 
				"WHERE Persons.UserName =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWorkouts);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int workoutId = results.getInt("WorkoutId");
				String workoutDescription = results.getString("WorkoutDescription");
				Workouts.Intensity intensity = Workouts.Intensity.valueOf(results.getString("Intensity"));
									
				workouts.add(new Workouts(workoutId, workoutDescription, intensity));
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
		return workouts;
	}
	
	
}
