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
package org.apache.causeway.testing.fixtures.applib;

import org.apache.causeway.testing.fixtures.applib.fixturescripts.FixtureScripts;
import org.apache.causeway.testing.fixtures.applib.modules.ModuleWithFixturesService;
import org.apache.causeway.testing.fixtures.applib.queryresultscache.QueryResultsCacheControlForFixtures;
import org.apache.causeway.testing.fixtures.applib.services.InitialFixtureScriptsInstaller;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @since 2.0 {@index}
 */
@Configuration
@Import({

        // @Service's
        InitialFixtureScriptsInstaller.class,
        ModuleWithFixturesService.class,
        QueryResultsCacheControlForFixtures.class,

        // @DomainService's
        FixtureScripts.class,
})
public class IsisModuleTestingFixturesApplib {

    public static final String NAMESPACE = "isis.testing.fixtures";
}