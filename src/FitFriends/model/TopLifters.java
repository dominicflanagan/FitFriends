// FitFriends. Nov 5, 2018.

package FitFriends.model;

import java.util.Date;

public class TopLifters {
	protected int memberId;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected int numReps;
	protected double solidFats;
	protected double satFats;
	protected double sugars;
	protected double totalCalories;
	
	// constructor with all attributes
	public TopLifters(int memberId, String userName, String firstName, String lastName, int numReps, double solidFats, double satFats, double sugars, double totalCalories) {
		this.memberId = memberId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numReps = numReps;
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

	public int getNumReps() {
		return numReps;
	}

	public void setNumReps(int numReps) {
		this.numReps = numReps;
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
