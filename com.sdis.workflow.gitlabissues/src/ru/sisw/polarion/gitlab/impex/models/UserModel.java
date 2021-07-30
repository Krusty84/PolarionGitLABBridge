package ru.sisw.polarion.gitlab.impex.models;
public class UserModel extends AbstractModel {
	private String userName;
	private String userID;
	private String name;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		firePropertyChange("userName", this.userName, this.userName = userName);
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		firePropertyChange("userID", this.userID, this.userID = userID);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		firePropertyChange("name", this.name, this.name = name);
	}
}
