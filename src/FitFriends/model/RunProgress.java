// FitFriends. October 30, 2018.

package FitFriends.model;

import java.util.Date;

public class RunProgress {
	protected int memberId;
	protected Date created; 
	protected int distanceMeters;
	protected Date runTime;
	protected String userName;
	protected String firstName;
	protected String lastName;

	
	// constructor with all Run Progress attributes
	public RunProgress(int memberId, Date created, int distanceMeters, Date runTime) {
		this.memberId = memberId;
		this.created = created; 
		this.distanceMeters = distanceMeters;
		this.runTime = runTime;
	}
	
	// constructor with all Run Progress attributes
	public RunProgress(int memberId, Date created, int distanceMeters) {
		this.memberId = memberId;
		this.created = created; 
		this.distanceMeters = distanceMeters;
	}
	
	// constructor with only the unique key MemberId attribute
	public RunProgress(int memberId) {
		this.memberId = memberId;
	}

	// constructor with all Run Progress and User attributes
	public RunProgress(int memberId, Date created, int distanceMeters, Date runTime,
			String userName, String firstName, String lastName) {
		this.memberId = memberId;
		this.created = created; 
		this.distanceMeters = distanceMeters;
		this.runTime = runTime;
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

	public int getDistanceMeters() {
		return distanceMeters;
	}

	public void setDistanceMeters(int distanceMeters) {
		this.distanceMeters = distanceMeters;
	}

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTimeSeconds) {
		this.runTime = runTime;
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
