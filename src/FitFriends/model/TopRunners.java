// FitFriends. October 31, 2018.

package FitFriends.model;

import java.util.Date;

public class TopRunners {
	protected int memberId;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected Date runTime;
	protected double solidFats;
	protected double satFats;
	protected double sugars;
	protected double totalCalories;
	
	// constructor with all attributes
	public TopRunners(int memberId, String userName, String firstName, String lastName, Date runTime, double solidFats, double satFats, double sugars, double totalCalories) {
		this.memberId = memberId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.runTime = runTime;
		this.solidFats = solidFats;
		this.satFats = satFats;
		this.sugars = sugars;
		this.totalCalories = totalCalories;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
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

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public double getSolidFats() {
		return solidFats;
	}

	public void setSolidFats(double solidFats) {
		this.solidFats = solidFats;
	}

	public double getSatFats() {
		return satFats;
	}

	public void setSatFats(double satFats) {
		this.satFats = satFats;
	}

	public double getSugars() {
		return sugars;
	}

	public void setSugars(double sugars) {
		this.sugars = sugars;
	}

	public double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}
	

}
