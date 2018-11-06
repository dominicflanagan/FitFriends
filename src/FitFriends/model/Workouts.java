package FitFriends.model;

public class Workouts {
	public enum Intensity {
		High, Medium, Low
	}
	
	private int workoutId;
	private String workoutDescription;
	private Intensity intensity;
	
	public Workouts(int workoutId, String workoutDescription, Intensity intensity) {
		super();
		this.workoutId = workoutId;
		this.workoutDescription = workoutDescription;
		this.intensity = intensity;
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public String getWorkoutDescription() {
		return workoutDescription;
	}

	public void setWorkoutDescription(String workoutDescription) {
		this.workoutDescription = workoutDescription;
	}

	public Intensity getIntensity() {
		return intensity;
	}

	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}
	
	
	
}
