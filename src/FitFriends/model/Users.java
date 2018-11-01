// FitFriends. October 30, 2018.

package FitFriends.model;

import java.sql.Timestamp;
import java.util.Date;

public class Users extends Persons {

	public enum FitnessLevel {
		Beginner, Intermediate, Advanced
	}	
	
	protected FitnessLevel fitnessLevel;
	protected Date dob;
	
	// constructor with all attributes
	public Users(int memberId, String userName, String firstName, String lastName, String email, String psw, Date dob, FitnessLevel fitnessLevel) {
		super (memberId,userName,firstName,lastName,email,psw);
		this.fitnessLevel = fitnessLevel;
		this.dob = dob;
	}
	
	// constructor with only the unique key MemberId attribute
	public Users(int memberId) {
		super(memberId);
	}

	// constructor with all attributes except the unique key MemberId attribute which is auto-generated at db level
	public Users(String userName, String firstName, String lastName, String email, String psw, Date dob, FitnessLevel fitnessLevel) {
		super (userName,firstName,lastName,email,psw);
		this.fitnessLevel = fitnessLevel;
		this.dob = dob;
	}

	// constructor with the memberId and the User attributes
	public Users(int memberId, Date dob, FitnessLevel fitnessLevel) {
		super(memberId);
		this.fitnessLevel = fitnessLevel;
		this.dob = dob;
	}

	public FitnessLevel getFitnessLevel() {
		return fitnessLevel;
	}

	public void setFitnessLevel(FitnessLevel fitnessLevel) {
		this.fitnessLevel = fitnessLevel;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
