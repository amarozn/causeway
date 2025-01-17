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
package org.apache.causeway.viewer.commons.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.causeway.viewer.commons.applib.CausewayModuleViewerCommonsApplib;
import org.apache.causeway.viewer.commons.services.branding.BrandingUiServiceDefault;
import org.apache.causeway.viewer.commons.services.header.HeaderUiServiceDefault;
import org.apache.causeway.viewer.commons.services.i8n.TranslationsResolverDefault;
import org.apache.causeway.viewer.commons.services.menu.MenuUiServiceDefault;
import org.apache.causeway.viewer.commons.services.userprof.UserProfileUiServiceDefault;

@Configuration
@Import({

    // adds object impersonation mixins
    CausewayModuleViewerCommonsApplib.class,

    // @Service's
    BrandingUiServiceDefault.class,
    UserProfileUiServiceDefault.class,
    MenuUiServiceDefault.class,
    HeaderUiServiceDefault.class,
    TranslationsResolverDefault.class,

})
public class CausewayModuleViewerCommonsServices {

    public static final String NAMESPACE = "causeway.viewer.commons";

}
