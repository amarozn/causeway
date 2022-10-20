/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.causeway.viewer.wicket.viewer.wicketapp.config;

import javax.inject.Inject;

import org.apache.wicket.devutils.debugbar.DebugBarInitializer;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.context.annotation.Configuration;

import org.apache.causeway.core.config.IsisConfiguration;
import org.apache.causeway.core.config.environment.IsisSystemEnvironment;
import org.apache.causeway.viewer.wicket.model.isis.WicketApplicationInitializer;

@Configuration
public class DebugInitWkt implements WicketApplicationInitializer {

    @Inject private IsisSystemEnvironment systemEnvironment;
    @Inject private IsisConfiguration configuration;

    @Override
    public void init(final WebApplication webApplication) {

        webApplication.getDebugSettings()
            .setAjaxDebugModeEnabled(configuration.getViewer().getWicket().isAjaxDebugMode());

        if(systemEnvironment.isPrototyping()
                && configuration.getViewer().getWicket().getDevelopmentUtilities().isEnable()) {
            new DebugBarInitializer().init(webApplication);
        }
    }

}