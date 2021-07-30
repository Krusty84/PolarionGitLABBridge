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

import com.google.inject.AbstractModule;
import com.polarion.alm.ui.server.forms.extensions.FormExtensionContribution;
import com.polarion.platform.guice.internal.Contributions;

/**
 * Guice module for this plugin. Must be referenced in the MANIFETS.MF in section Guice-Modules.
 */
public class FormExtensionModule extends AbstractModule {

    @Override
    protected void configure() {
        Contributions<FormExtensionContribution> contributions = new Contributions<FormExtensionContribution>(binder(), FormExtensionContribution.class);
        contributions.addBinding().toInstance(new FormExtensionContribution(GitLabInformation.class, GitLabInformation.ID));
    }

}
