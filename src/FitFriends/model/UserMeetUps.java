package FitFriends.model;

import java.sql.Timestamp;

public class UserMeetUps {
	private int meetUpId;
	private String meetUpName;
	private int memberId;
	private String userName;
	private Timestamp meetUpTime;
	
	public UserMeetUps(int meetUpId, String meetUpName, int memberId, String userName, Timestamp meetUpTime) {
		this.meetUpId = meetUpId;
		this.meetUpName = meetUpName;
		this.memberId = memberId;
		this.userName = userName;
		this.meetUpTime = meetUpTime;
	}

	public UserMeetUps(int meetUpId, int memberId, Timestamp meetUpTime) {
		this.meetUpId = meetUpId;
		this.memberId = memberId;
		this.meetUpTime = meetUpTime; 
	}

	public UserMeetUps() {
		// TODO Auto-generated constructor stub
	}

	public int getMeetUpId() {
		return meetUpId;
	}

	public void setMeetUpId(int meetUpId) {
		this.meetUpId = meetUpId;
	}

	public String getMeetUpName() {
		return meetUpName;
	}

	public void setMeetUpName(String meetUpName) {
		this.meetUpName = meetUpName;
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

	public Timestamp getMeetUpTime() {
		return meetUpTime;
	}

	public void setMeetUpTime(Timestamp meetUpTime) {
		this.meetUpTime = meetUpTime;
	}

	
	
}
