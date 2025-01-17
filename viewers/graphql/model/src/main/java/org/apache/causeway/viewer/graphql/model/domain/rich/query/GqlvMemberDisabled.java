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
package org.apache.causeway.viewer.graphql.model.domain.rich.query;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLOutputType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

import org.apache.causeway.applib.annotation.Where;
import org.apache.causeway.core.metamodel.consent.InteractionInitiatedBy;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.core.metamodel.spec.feature.ObjectMember;
import org.apache.causeway.viewer.graphql.model.context.Context;
import org.apache.causeway.viewer.graphql.model.domain.GqlvAbstract;
import org.apache.causeway.viewer.graphql.model.domain.common.interactors.MemberInteractor;
import org.apache.causeway.viewer.graphql.model.fetcher.BookmarkedPojo;

import lombok.val;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GqlvMemberDisabled<T extends ObjectMember> extends GqlvAbstract {

    private final MemberInteractor<T> memberInteractor;

    public GqlvMemberDisabled(
            final MemberInteractor<T> memberInteractor,
            final Context context
    ) {
        super(context);
        this.memberInteractor = memberInteractor;

        setField(newFieldDefinition()
                .name("disabled")
                .type((GraphQLOutputType) context.typeMapper.outputTypeFor(String.class))
                .build());
    }

    @Override
    protected String fetchData(final DataFetchingEnvironment dataFetchingEnvironment) {

        val sourcePojo = BookmarkedPojo.sourceFrom(dataFetchingEnvironment);

        val sourcePojoClass = sourcePojo.getClass();
        val objectSpecification = context.specificationLoader.loadSpecification(sourcePojoClass);
        if (objectSpecification == null) {
            return String.format("Disabled; could not determine target object's type ('%s')", sourcePojoClass.getName());
        }

        val objectMember = memberInteractor.getObjectMember();
        val managedObject = ManagedObject.adaptSingular(objectSpecification, sourcePojo);

        val usable = objectMember.isUsable(managedObject, InteractionInitiatedBy.USER, Where.ANYWHERE);
        return usable.getReasonAsString().orElse(null);
    }

}
