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
package org.apache.causeway.extensions.audittrail.jdo.integtests;

import org.apache.causeway.core.config.presets.IsisPresets;
import org.apache.causeway.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.causeway.extensions.audittrail.applib.integtests.AuditTrail_IntegTestAbstract;
import org.apache.causeway.extensions.audittrail.applib.integtests.model.AuditTrailTestDomainModel;
import org.apache.causeway.extensions.audittrail.jdo.IsisModuleExtAuditTrailPersistenceJdo;
import org.apache.causeway.extensions.audittrail.jdo.integtests.model.Counter;
import org.apache.causeway.extensions.audittrail.jdo.integtests.model.CounterRepository;
import org.apache.causeway.security.bypass.IsisModuleSecurityBypass;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = AuditTrail_IntegTest.AppManifest.class
)
@ActiveProfiles("test")
public class AuditTrail_IntegTest extends AuditTrail_IntegTestAbstract {


    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({
            IsisModuleCoreRuntimeServices.class,
            IsisModuleSecurityBypass.class,
            IsisModuleExtAuditTrailPersistenceJdo.class,
    })
    @PropertySources({
            @PropertySource(IsisPresets.UseLog4j2Test),
    })
    @ComponentScan(basePackageClasses = {AppManifest.class, AuditTrailTestDomainModel.class, CounterRepository.class})
    public static class AppManifest {
    }

    @Override
    protected org.apache.causeway.extensions.audittrail.applib.integtests.model.Counter newCounter(final String name) {
        return Counter.builder().name(name).build();
    }


}