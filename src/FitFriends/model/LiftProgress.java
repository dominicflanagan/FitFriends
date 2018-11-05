// FitFriends. Nov 5, 2018.

package FitFriends.model;

import java.util.Date;

public class LiftProgress {
	protected int memberId;
	protected Date created; 
	protected int weightPounds;
	protected int numReps;
	protected String userName;
	protected String firstName;
	protected String lastName;

	
	// constructor with all Lift Progress attributes
	public LiftProgress(int memberId, Date created, int weightPounds, int numReps) {
		this.memberId = memberId;
		this.created = created; 
		this.weightPounds = weightPounds;
		this.numReps = numReps;
	}
	
	// constructor with all Lift Progress attributes
	public LiftProgress(int memberId, Date created, int weightPounds) {
		this.memberId = memberId;
		this.created = created; 
		this.weightPounds = weightPounds;
	}
	
	// constructor with only the unique key MemberId attribute
	public LiftProgress(int memberId) {
		this.memberId = memberId;
	}

	// constructor with all Lift Progress and User attributes
	public LiftProgress(int memberId, Date created, int weightPounds, int numReps,
			String userName, String firstName, String lastName) {
		this.memberId = memberId;
		this.created = created; 
		this.weightPounds = weightPounds;
		this.numReps = numReps;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(int weightPounds) {
		this.weightPounds = weightPounds;
	}

	public int getNumReps() {
		return numReps;
	}

	public void setNumReps(int numReps) {
		this.numReps = numReps;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
