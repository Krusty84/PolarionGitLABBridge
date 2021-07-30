package ru.sisw.polarion.gitlab.impex.issues;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import ru.sisw.polarion.gitlab.impex.helpers.URLBeautifier;
import ru.sisw.polarion.gitlab.impex.models.GitlabModel;
import ru.sisw.polarion.gitlab.impex.models.ResponseModel;
import ru.sisw.polarion.gitlab.impex.models.UserModel;

import java.util.LinkedList;
import java.util.List;

public class GitlabIssues {
	/**
	 * @param rsp - response
	 * @return IssueResponseModel - IssueResponseModel
	 * @throws Exception - Exception
	 */
	private static IssueResponseModel parseCreatedIssue(CloseableHttpResponse rsp) throws Exception {
		//
		StatusLine statusLine = rsp.getStatusLine();
		ResponseModel responseModel = new ResponseModel();
		responseModel.setReasonPhrase(statusLine.getReasonPhrase());
		responseModel.setStatusCode(statusLine.getStatusCode());
		//
		IssueResponseModel issueResponseModel = new IssueResponseModel();
		issueResponseModel.setResponseModel(responseModel);
		{//parse json
			JSONObject jsonObject = new JSONObject(EntityUtils.toString(rsp.getEntity()));
			issueResponseModel.setFullUrl(jsonObject.isNull("web_url") ? null : String.valueOf(jsonObject.get("web_url")));
			issueResponseModel.setIssueDescription(jsonObject.isNull("description") ? null : String.valueOf(jsonObject.get("description")));
			issueResponseModel.setIssueTitle(jsonObject.isNull("title") ? null : String.valueOf(jsonObject.get("title")));
			issueResponseModel.setIssueState(jsonObject.isNull("state") ? null : String.valueOf(jsonObject.get("state")));
			issueResponseModel.setIid(jsonObject.isNull("iid") ? null : String.valueOf(jsonObject.get("iid")));
			issueResponseModel.setIssueState(jsonObject.isNull("state") ? null : String.valueOf(jsonObject.get("state")));
			//
			{
				UserModel assignedUser = new UserModel();
				JSONObject assignee = jsonObject.isNull("assignee") ? null : (JSONObject) jsonObject.get("assignee");
				if (null != assignee) {
					assignedUser.setUserID(assignee.isNull("id") ? null : String.valueOf(assignee.get("id")));
					assignedUser.setUserName(assignee.isNull("username") ? null : String.valueOf(assignee.get("username")));
					assignedUser.setName(assignee.isNull("name") ? null : String.valueOf(assignee.get("name")));
				}
				issueResponseModel.setAssignedUser(assignedUser);
			}
		}
		//
		return issueResponseModel;
	}
	/**
	 * @param issueModel - issueModel
	 * @return List<String>
	 */
	private static List<String> prepareCreateIssue(IssueModel issueModel) {
		List<String> qry = new LinkedList<>();
		//
		if (null != issueModel.getAssignees())
			qry.add("assignee_ids=" + URLBeautifier.beautify(StringUtils.join(issueModel.getAssignees(), ',')));
		if (null != issueModel.getBody())
			qry.add("description=" + URLBeautifier.beautify(issueModel.getBody()));
		if (null != issueModel.getLabels())
			qry.add("labels=" + URLBeautifier.beautify(StringUtils.join(issueModel.getLabels(), ',')));
		if (null != issueModel.getTitle())
			qry.add("title=" + URLBeautifier.beautify(issueModel.getTitle()));
		//
		return qry;
	}
	/**
	 * @param issueModel  - issueModel
	 * @param gitlabModel - gitlabModel
	 * @return IssueResponseModel
	 */
	public static IssueResponseModel createIssue(IssueModel issueModel, GitlabModel gitlabModel) throws Exception {
		CloseableHttpResponse rsp = null;
		CloseableHttpClient httpclient = null;
		try {
			List<String> qry = prepareCreateIssue(issueModel);
			//
			httpclient = HttpClients.createDefault();
			HttpUriRequest request = RequestBuilder.post().setUri(gitlabModel.getGitlabURL() + "/projects/" + issueModel.getProject() + "/issues?" + StringUtils.join(qry, '&')
					//
			).setHeader(gitlabModel.getTokenName(), gitlabModel.getTokenValue()).build();
			rsp = httpclient.execute(request);
			//
			return parseCreatedIssue(rsp);
		} finally {
			if (null != rsp) rsp.close();
			if (null != httpclient) httpclient.close();
		}
	}
	
	public static IssueResponseModel getIssue(GitlabModel gitlabModel, String projectId, String issueId) throws Exception {
		CloseableHttpResponse rsp = null;
		CloseableHttpClient httpclient = null;
		try {
			//List<String> qry = prepareCreateIssue(issueModel);
			//
			httpclient = HttpClients.createDefault();
			
			HttpUriRequest request = RequestBuilder.get().setUri(gitlabModel.getGitlabURL()+"/projects/"+projectId+"/issues/"+issueId
			).setHeader(gitlabModel.getTokenName(), gitlabModel.getTokenValue()).build();
			
			rsp = httpclient.execute(request);
			//
			return parseCreatedIssue(rsp);
		} finally {
			if (null != rsp) rsp.close();
			if (null != httpclient) httpclient.close();
		}
	}
}
