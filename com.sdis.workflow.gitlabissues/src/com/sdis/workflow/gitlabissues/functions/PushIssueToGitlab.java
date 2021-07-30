package com.sdis.workflow.gitlabissues.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.FileHandler;

import com.polarion.alm.projects.model.IUser;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.alm.tracker.workflow.IArguments;
import com.polarion.alm.tracker.workflow.ICallContext;
import com.polarion.alm.tracker.workflow.IFunction;
import com.polarion.core.config.IUiConfiguration.IUsersData;
import com.polarion.core.util.types.Text;
import com.polarion.platform.core.PlatformContext;
import com.polarion.platform.internal.User;
import com.polarion.platform.persistence.model.IPObject;
import com.polarion.platform.security.ISecurityService;
import com.polarion.platform.security.auth.UserProfile;
import com.polarion.subterra.base.data.model.ICustomField;

import ru.sisw.polarion.gitlab.impex.issues.GitlabIssues;
import ru.sisw.polarion.gitlab.impex.issues.IssueModel;
import ru.sisw.polarion.gitlab.impex.issues.IssueResponseModel;
import ru.sisw.polarion.gitlab.impex.models.GitlabModel;

public class PushIssueToGitlab implements IFunction {

	public static String truncate(String value, int length)
	{
	  if (value != null && value.length() > length)
	    value = value.substring(0, length);
	  return value;
	}
	
	public void execute(ICallContext context, IArguments arguments) {
		
		try 
		{
			
		/*java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
		FileHandler fileLogHandler = new FileHandler("C:\\Temp\\PushIssueToGitlab.log");
		logger.addHandler(fileLogHandler);
		/*logger.info("This is an info message");
		logger.severe("This is an error message"); // == ERROR
		logger.fine("Here is a debug message"); // == DEBUG
		*/
	
		ISecurityService securityService = (ISecurityService) PlatformContext.getPlatform()
				.lookupService(ISecurityService.class);
		String currentUser = securityService.getCurrentUser();

		String polarion_server_name = arguments.getAsString("PolarionSrvName");
		String gitlab_url = arguments.getAsString("GitLabURL");
//		String gitlab_private_token = arguments.getAsString("GitLabPrivateToken");
		String gitlab_project_id = arguments.getAsString("GitLabProjectID");
		String bodyIssueText = new String();
		IWorkItem wi = context.getWorkItem();

			IssueModel issueModel = new IssueModel();
			
			if(wi.getDescription()!=null) 
			{
				bodyIssueText=truncate(wi.getDescription().convertToPlainText().getContent().replace("\"","\'").replace(";","").replace("&", "").replace("%", ""), 800).toString()
						+" \nDetails here: http://" + polarion_server_name + "/polarion/redirect/project/"+ wi.getProjectId() + "/workitem?id=" + wi.getId();
			} 
			else if (wi.getDescription()==null){
				bodyIssueText="Details here: http://" + polarion_server_name + "/polarion/redirect/project/"+ wi.getProjectId() + "/workitem?id=" + wi.getId();	
			}
			issueModel.setBody(bodyIssueText);
			issueModel.setTitle(wi.getTitle());
			// issueModel.setAssignees(Arrays.asList("13"));
			issueModel.setProject(gitlab_project_id.toString());
			issueModel.setLabels(Arrays.asList("Polarion",wi.getPriority().getName(),
					wi.getProject().getName()));
			//
			GitlabModel gitlabModel = new GitlabModel();
			// TODO: replace with token
			// get GItLab Token from Description field by current user
			gitlabModel.setTokenValue(context.getTrackerService().getTrackerUser(currentUser).getDescription().getContent().toString());
//		gitlabModel.setTokenValue(gitlab_private_token);
			gitlabModel.setTokenName("PRIVATE-TOKEN");
			// gitlabModel.setGitlabURL("https://code.splm.ru/api/v4");
			gitlabModel.setGitlabURL(gitlab_url.toString());
			//
			/*
			 * ResponseModel rsp; rsp = GitlabIssues.createIssue(issueModel, gitlabModel);
			 */
			IssueResponseModel rsp = GitlabIssues.createIssue(issueModel, gitlabModel);

			wi.setCustomField("gitLabIssueURL", rsp.getFullUrl());
			wi.setCustomField("gitLabIssueID", rsp.getIid());
			// wi.setCustomField("gitLabIssueState", rsp.getIssueState());
			/*logger.info(rsp.getFullUrl().toString());
			logger.info(rsp.getIid().toString());
			logger.info(gitlab_url.toString());*/

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
