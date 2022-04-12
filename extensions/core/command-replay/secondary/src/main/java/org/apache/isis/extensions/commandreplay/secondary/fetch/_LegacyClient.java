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
package org.apache.isis.extensions.commandreplay.secondary.fetch;

import java.net.URI;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@Deprecated
@UtilityClass
class _LegacyClient {

    public static interface JaxRsResponse {
        int getStatus();
        <T> T readEntity(final Class<T> entityType);
    }

    public static interface JaxRsClient {
        @RequiredArgsConstructor
        public enum ReprType {
            ACTION_RESULT("action-result");
            @Getter private final String suffix;
        }
        JaxRsResponse get(URI uri, Class<?> dtoClass, ReprType reprType, String username, String password);
    }

    public static class JaxRsClientDefault implements JaxRsClient {

        @Override
        public JaxRsResponse get(final URI uri, final Class<?> dtoClass, final ReprType reprType, final String username, final String password) {
            // TODO Auto-generated method stub
            return null;
        }

    }


}