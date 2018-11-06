package FitFriends.model;

import FitFriends.model.MeetUps.Type;

public class MeetUps {
	public enum Type {
		Malls, Parks, NaturalWilds, Cemeteries, Parkways, Community
	}	
	
	private int meetUpId;
	private String meetUpName;
	private String district;
	private String address;
	private Type type;
	
	public MeetUps(int meetUpId, String meetUpName, String district, String address, Type type) {
		this.meetUpId = meetUpId;
		this.meetUpName = meetUpName;
		this.district = district;
		this.address = address;
		this.type = type;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	} 
	
}
