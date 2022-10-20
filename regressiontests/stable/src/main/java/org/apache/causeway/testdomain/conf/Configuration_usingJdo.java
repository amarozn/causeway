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
package org.apache.causeway.testdomain.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.causeway.core.config.presets.IsisPresets;
import org.apache.causeway.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.causeway.persistence.jdo.datanucleus.IsisModulePersistenceJdoDatanucleus;
import org.apache.causeway.persistence.jdo.datanucleus.IsisModulePersistenceJdoDatanucleusMixins;
import org.apache.causeway.security.bypass.IsisModuleSecurityBypass;
import org.apache.causeway.testdomain.jdo.JdoTestDomainModule;
import org.apache.causeway.testdomain.model.stereotypes.MyService;
import org.apache.causeway.testdomain.util.kv.KVStoreForTesting;
import org.apache.causeway.testing.fixtures.applib.IsisModuleTestingFixturesApplib;

@Configuration
@Import({
    MyService.class, // testing injection into entities
    IsisModuleCoreRuntimeServices.class,
    IsisModuleSecurityBypass.class,
    IsisModulePersistenceJdoDatanucleus.class,
    IsisModulePersistenceJdoDatanucleusMixins.class,
    IsisModuleTestingFixturesApplib.class,
    KVStoreForTesting.class, // Helper for JUnit Tests

    JdoTestDomainModule.class
})
@PropertySources({
    @PropertySource(IsisPresets.NoTranslations),
    @PropertySource(IsisPresets.DatanucleusAutocreateNoValidate),
    @PropertySource(IsisPresets.H2InMemory_withUniqueSchema),
})
public class Configuration_usingJdo {


}