package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import FitFriends.model.Persons;
import FitFriends.model.Users;
import FitFriends.model.Workouts;

public class WorkoutsDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static WorkoutsDao instance = null;
	protected WorkoutsDao() {
		connectionManager = new ConnectionManager();
	}
	public static WorkoutsDao getInstance() {
		if(instance == null) {
			instance = new WorkoutsDao();
		}
		return instance;
	}

			
	//Get all of the workouts
	public List<Workouts> getAllWorkouts() throws SQLException {
		List<Workouts> workouts = new ArrayList<Workouts>();
		String selectWorkouts = "SELECT * FROM Workout;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWorkouts);
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
	
	//Get all of the workouts
	public List<Workouts> getAllWorkoutsByIntensity(String intensity) throws SQLException {
		List<Workouts> workouts = new ArrayList<Workouts>();
		String selectWorkouts = "SELECT * FROM Workout WHERE Intensity =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWorkouts);
			selectStmt.setString(1, intensity);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
				int workoutId = results.getInt("WorkoutId");
				String workoutDescription = results.getString("WorkoutDescription");
				Workouts.Intensity resultIntensity = Workouts.Intensity.valueOf(results.getString("Intensity"));
									
				workouts.add(new Workouts(workoutId, workoutDescription, resultIntensity));
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
	public Workouts delete(int workoutId) throws SQLException {
		String deletePerson = "DELETE FROM Workout WHERE WorkoutId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1,workoutId);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
