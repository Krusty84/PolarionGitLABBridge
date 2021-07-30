package ru.sisw.polarion.gitlab.impex.models;
public class GitlabModel extends AbstractModel {
	private String gitlabURL;
	private String tokenName;
	private String tokenValue;
	/**
	 * @return the gitlabURL
	 */
	public String getGitlabURL() {
		return gitlabURL;
	}
	/**
	 * @param gitlabURL the gitlabURL to set
	 */
	public void setGitlabURL(String gitlabURL) {
		firePropertyChange("gitlabURL", this.gitlabURL, this.gitlabURL = gitlabURL);
	}
	/**
	 * @return the tokenName
	 */
	public String getTokenName() {
		return tokenName;
	}
	/**
	 * @param tokenName the tokenName to set
	 */
	public void setTokenName(String tokenName) {
		firePropertyChange("tokenName", this.tokenName, this.tokenName = tokenName);
	}
	/**
	 * @return the token
	 */
	public String getTokenValue() {
		return tokenValue;
	}
	/**
	 * @param token the token to set
	 */
	public void setTokenValue(String token) {
		firePropertyChange("tokenValue", this.tokenValue, this.tokenValue = token);
	}
}
