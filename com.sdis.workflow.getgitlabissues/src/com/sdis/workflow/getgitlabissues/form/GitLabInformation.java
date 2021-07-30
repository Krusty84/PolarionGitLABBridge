/*
 * Copyright (C) 2004-2014 Polarion Software
 * All rights reserved.
 * Email: dev@polarion.com
 *
 *
 * Copyright (C) 2004-2014 Polarion Software
 * All Rights Reserved.  No use, copying or distribution of this
 * work may be made except in accordance with a valid license
 * agreement from Polarion Software.  This notice must be
 * included on all copies, modifications and derivatives of this
 * work.
 *
 * POLARION SOFTWARE MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESSED OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. POLARION SOFTWARE
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.sdis.workflow.getgitlabissues.form;

import java.util.Map;
import javax.inject.Inject;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.alm.ui.server.forms.extensions.IFormExtension;
import com.polarion.platform.persistence.model.IPObject;
import com.polarion.platform.security.ISecurityService;
import com.polarion.platform.security.auth.UserProfile;

import ru.sisw.polarion.gitlab.impex.issues.GitlabIssues;
import ru.sisw.polarion.gitlab.impex.issues.IssueResponseModel;
import ru.sisw.polarion.gitlab.impex.models.GitlabModel;

import com.polarion.alm.projects.model.IUser;
import com.polarion.alm.shared.api.model.user.User;
import com.polarion.alm.tracker.model.ISeverityOpt;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.alm.ui.server.forms.extensions.IFormExtension;
import com.polarion.core.util.EscapeChars;
import com.polarion.platform.core.PlatformContext;
import com.polarion.platform.persistence.IDataService;
import com.polarion.platform.persistence.model.IPObject;

/*
 <extension GitLabPrivateToken="MqumTAB81E1HdC-ApuT4" GitLabProjectID="304" id="getgitlabissue" label="_____GitLab_____"/>
 */
public class GitLabInformation implements IFormExtension {
	public static final String ID = "getgitlabissue";

	@Inject
	private IDataService service;

	@Override
	public String render(IPObject object, Map<String, String> attributes) {

		try {

			if (object.isPersisted() && object instanceof IWorkItem) {

				IWorkItem workItem = (IWorkItem) object;
				String gitLabURL = workItem.getValue("gitLabIssueURL").toString();
				String gitLabIssueID = workItem.getValue("gitLabIssueID").toString();
				// get token-value form extension parameter
//	            String gitlab_private_token = attributes.get("GitLabPrivateToken");
				String gitlab_project_id = attributes.get("GitLabProjectID");
				String gitLabURL_short = attributes.get("GitLabURL");
				//
				StringBuffer result = new StringBuffer();
				String colorState = new String();

				GitlabModel gitlabModel = new GitlabModel();
//            			gitlabModel.setTokenValue(gitlab_private_token);    			
				gitlabModel.setTokenValue(workItem.getAuthor().getDescription().getContent().toString());
				gitlabModel.setTokenName("PRIVATE-TOKEN");
				//gitlabModel.setGitlabURL("https://code.splm.ru/api/v4");
				//gitlabModel.setGitlabURL("https://gitlab.com/api/v4");
				gitlabModel.setGitlabURL(gitLabURL_short.toString());
				IssueResponseModel rsp;

				rsp = GitlabIssues.getIssue(gitlabModel, gitlab_project_id, gitLabIssueID);

				if (rsp.getIid().length() != 0) {

					if (rsp.getIssueState().equals("opened")) {
						colorState = "<span style=\"color: #0000ff;\">opened</span>";
					} else if (rsp.getIssueState().equals("closed")) {
						colorState = "<span style=\"color: #339966;\">closed</span>";
					}

					if (rsp.getAssignedUser().getUserID() != null) {

						String label1 = "<p><strong>This issue in GitLab: <a href=" + gitLabURL + " target=" + "_blank "
								+ "rel=" + "noopener noreferrer" + ">" + rsp.getIid() + "</a></strong></p>";
						String label2 = "<p><strong>The status of this issue: </strong><em>" + colorState + "</em>"
								+ "</p>";
						String label3 = "<p><strong>This issue was assigned on: </strong><em>"
								+ rsp.getAssignedUser().getUserName().toString() + "</em>" + "</p>";

						result.append(label1);
						result.append("");
						result.append(label2);
						result.append("");
						result.append(label3);
						return result.toString();

					} else {
						String label1 = "<p><strong>This issue in GitLab: <a href=" + gitLabURL + " target=" + "_blank "
								+ "rel=" + "noopener noreferrer" + ">" + rsp.getIid() + "</a></strong></p>";
						String label2 = "<p><strong>The status of this issue: </strong><em>" + colorState + "</em>"
								+ "</p>";
						String label3 = "<p><span style=\"color: #ff0000;\"><strong>This issue has not been assigned on somebody</strong></span>"
								+ "</p>";
						result.append(label1);
						result.append("");
						result.append(label2);
						result.append("");
						result.append(label3);
						return result.toString();

					}
				} else {
					return "This issue has not been submitted to GitLab. Or there is a problem with connect to GitLab.";
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "ERROR:"+e.getLocalizedMessage();
			return "This issue has not been submitted to GitLab. Or there is a problem with connect to GitLab. "
					+ e.getLocalizedMessage();

		}
		return null;
	}
}
