// FitFriends. October 30, 2018.

package FitFriends.model;

public class Persons {
	protected int memberId;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String psw;
	
	// constructor with all attributes
	public Persons(int memberId, String userName, String firstName, String lastName, String email, String psw) {
		this.memberId = memberId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.psw = psw;
	}
	
	// constructor with only the unique key MemberId attribute
	public Persons(int memberId) {
		this.memberId = memberId;
	}

	// constructor with all attributes except the unique key MemberId attribute which is auto-generated at db level
	public Persons(String userName, String firstName, String lastName, String email, String psw) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.psw = psw;
	}
	
	// constructor with only a username
	public Persons(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}


}
