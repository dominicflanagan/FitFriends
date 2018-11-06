package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FitFriends.model.Exercise;
import FitFriends.model.Workouts;

public class WorkoutExersisesDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static WorkoutExersisesDao instance = null;
	protected WorkoutExersisesDao() {
		connectionManager = new ConnectionManager();
	}
	public static WorkoutExersisesDao getInstance() {
		if(instance == null) {
			instance = new WorkoutExersisesDao();
		}
		return instance;
	}

			
	//Get all of the exercises for a workout
	public List<Exercise> getAllExercisesByWorkoutId(int workoutId) throws SQLException {
		List<Exercise> exercises = new ArrayList<Exercise>();
		String selectExercises = "SELECT Exercise.ExerciseId, Exercise.MuscleGroup, Exercise.Exercise\n" + 
				"FROM WorkoutExercise INNER JOIN Exercise ON WorkoutExercise.ExerciseId = Exercise.ExerciseId\n" + 
				"WHERE WorkoutId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectExercises);
			selectStmt.setInt(1, workoutId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int exerciseId = results.getInt("ExerciseId");
				String muscleGroup = results.getString(("MuscleGroup"));
				String exercise = results.getString("Exercise");
				
									
				exercises.add(new Exercise(exerciseId, muscleGroup, exercise));
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
		return exercises;
	}
	
//	//Get all of the workouts
//	public List<Workouts> getAllWorkoutsByIntensity(String intensity) throws SQLException {
//		List<Workouts> workouts = new ArrayList<Workouts>();
//		String selectWorkouts = "SELECT * FROM Workout WHERE Intensity =?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectWorkouts);
//			selectStmt.setString(1, intensity);
//			
//			results = selectStmt.executeQuery();
//			while(results.next()) {
//				int workoutId = results.getInt("WorkoutId");
//				String workoutDescription = results.getString("WorkoutDescription");
//				Workouts.Intensity resultIntensity = Workouts.Intensity.valueOf(results.getString("Intensity"));
//									
//				workouts.add(new Workouts(workoutId, workoutDescription, resultIntensity));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return workouts;
//	}
}
