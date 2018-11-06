package FitFriends.model;

public class UserWorkout {
	private int workoutId;
	private int memberId;
	
	public UserWorkout(int workoutId, int memberId) {
		super();
		this.workoutId = workoutId;
		this.memberId = memberId;
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	
	
}
