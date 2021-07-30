package ru.sisw.polarion.gitlab.impex.models;
public class ResponseModel extends AbstractModel {
	private Integer statusCode;
	private String reasonPhrase;
	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		firePropertyChange("statusCode", this.statusCode, this.statusCode = statusCode);
	}
	/**
	 * @return the reasonPhrase
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	/**
	 * @param reasonPhrase the reasonPhrase to set
	 */
	public void setReasonPhrase(String reasonPhrase) {
		firePropertyChange("reasonPhrase", this.reasonPhrase, this.reasonPhrase = reasonPhrase);
	}
}
