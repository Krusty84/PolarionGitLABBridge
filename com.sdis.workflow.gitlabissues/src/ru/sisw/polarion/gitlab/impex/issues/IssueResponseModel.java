package ru.sisw.polarion.gitlab.impex.issues;
import ru.sisw.polarion.gitlab.impex.models.AbstractModel;
import ru.sisw.polarion.gitlab.impex.models.ResponseModel;
import ru.sisw.polarion.gitlab.impex.models.UserModel;

/**
 *
 */
public class IssueResponseModel extends AbstractModel {
	private ResponseModel responseModel;
	private UserModel assignedUser;
	private String fullUrl;
	private String iid;
	private String issueDescription;
	private String issueTitle;
	private String issueState;
	private String state;
	
	
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		firePropertyChange("issueDescription", this.issueDescription, this.issueDescription = issueDescription);
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		firePropertyChange("issueTitle", this.issueTitle, this.issueTitle = issueTitle);
	}
	public String getIssueState() {
		return issueState;
	}
	public void setIssueState(String issueState) {
		firePropertyChange("issueState", this.issueState, this.issueState = issueState);
	}
	public UserModel getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(UserModel assignedUser) {
		firePropertyChange("assignedUser", this.assignedUser, this.assignedUser = assignedUser);
	}
	
	/**
	 * @param fullUrl - fullUrl
	 */
	public String getFullUrl() {
		return fullUrl;
	}
	
	public void setFullUrl(String fullUrl) {
		firePropertyChange("fullUrl", this.fullUrl, this.fullUrl = fullUrl);
	}
	/**
	 * @return responseModel
	 */
	public ResponseModel getResponseModel() {
		return responseModel;
	}
	/**
	 * @param responseModel - responseModel
	 */
	public void setResponseModel(ResponseModel responseModel) {
		firePropertyChange("responseModel", this.responseModel, this.responseModel = responseModel);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		firePropertyChange("state", this.state, this.state = state);
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		firePropertyChange("iid", this.iid, this.iid = iid);
	}
	
	
	
}
