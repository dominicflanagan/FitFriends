package FitFriends.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import FitFriends.model.Exercise;
import FitFriends.model.WorkoutExercise;
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
	public List<WorkoutExercise> getAllExercisesByWorkoutId(int workoutId) throws SQLException {
		List<WorkoutExercise> exercises = new ArrayList<WorkoutExercise>();
		String selectExercises = "SELECT WorkoutExercise.WorkoutId, Exercise.ExerciseId, Exercise.MuscleGroup, Exercise.Exercise\n" + 
				"FROM WorkoutExercise JOIN Exercise ON WorkoutExercise.ExerciseId = Exercise.ExerciseId\n" + 
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
				
									
				Exercise tmpExercise = new Exercise(exerciseId, muscleGroup, exercise);
				exercises.add(new WorkoutExercise(results.getInt("WorkoutId"), tmpExercise));
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
	
	public WorkoutExercise getWorkoutExerciseByIds(int workoutId, int exerciseId) throws SQLException {
		String selectWorkoutExercise = "SELECT WorkoutExercise.WorkoutId, Exercise.ExerciseId, Exercise.MuscleGroup, Exercise.Exercise\n" + 
				"FROM WorkoutExercise JOIN Exercise ON WorkoutExercise.ExerciseId = Exercise.ExerciseId\n" + 
				"WHERE WorkoutExercise.ExerciseId =? AND WorkoutId =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWorkoutExercise);
			selectStmt.setInt(1, workoutId);
			selectStmt.setInt(2, exerciseId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultExerciseId = results.getInt("ExerciseId");
				String muscleGroup = results.getString(("MuscleGroup"));
				String exercise = results.getString("Exercise");
				
									
				Exercise tmpExercise = new Exercise(exerciseId, muscleGroup, exercise);
				WorkoutExercise workoutExercise = new WorkoutExercise(results.getInt("WorkoutId"), tmpExercise);
				return workoutExercise;
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
	public WorkoutExercise delete(WorkoutExercise workoutExercise) throws SQLException {
		String deleteUser = "DELETE FROM WorkoutExercise WHERE WorkoutId=? AND ExerciseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, workoutExercise.getWorkoutId());
			deleteStmt.setInt(2, workoutExercise.getExercise().getExerciseId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete ");
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
	public WorkoutExercise create(int workoutId, int exerciseId) throws SQLException {
		String insertWorkoutExercise = "INSERT INTO WorkoutExercise(WorkoutId,ExerciseId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWorkoutExercise);
			insertStmt.setInt(1, workoutId);
			insertStmt.setInt(2, exerciseId);
		
			insertStmt.executeUpdate();

			return null;
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
}
