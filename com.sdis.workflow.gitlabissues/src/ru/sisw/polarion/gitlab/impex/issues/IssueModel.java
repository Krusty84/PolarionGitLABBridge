package ru.sisw.polarion.gitlab.impex.issues;
import ru.sisw.polarion.gitlab.impex.models.AbstractModel;

import java.util.LinkedList;
import java.util.List;

public class IssueModel extends AbstractModel {
	private List<String> assignees;
	private String body;
	private List<String> labels;
	private String title;
	private String project;
	
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		firePropertyChange("project", this.project, this.project = project);
	}
	/**
	 * @return the assignee
	 */
	public List<String> getAssignees() {
		return assignees;
	}
	/**
	 * @param assignees the assignee to set
	 */
	public void setAssignees(List<String> assignees) {
		List<String> old = null == this.assignees ? new LinkedList<>() : new LinkedList<>(this.assignees);
		firePropertyChange("assignees", old, this.assignees = assignees);
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		firePropertyChange("body", this.body, this.body = body);
	}
	/**
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<String> labels) {
		List<String> old = null == this.labels ? new LinkedList<>() : new LinkedList<>(this.labels);
		firePropertyChange("labels", this.labels, this.labels = labels);
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		firePropertyChange("title", this.title, this.title = title);
	}
}
