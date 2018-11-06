package FitFriends.model;

public class Exercise {
	private int exerciseId;
	private String muscleGroup;
	private String exercise;

	public Exercise(int exerciseId, String muscleGroup, String exercise) {
		this.exerciseId = exerciseId;
		this.muscleGroup = muscleGroup;
		this.exercise = exercise;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	
	
}
