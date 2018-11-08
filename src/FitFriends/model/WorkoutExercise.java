package FitFriends.model;

public class WorkoutExercise {
	private int workoutId;
	private Exercise exercise;
	
	public WorkoutExercise(int workoutId, Exercise exercise) {
		this.workoutId = workoutId;
		this.exercise = exercise;
	}

	public WorkoutExercise() {
		// TODO Auto-generated constructor stub
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	
}
