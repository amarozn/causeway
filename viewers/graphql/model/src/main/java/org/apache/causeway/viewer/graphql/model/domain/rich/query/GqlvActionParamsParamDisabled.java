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

import org.apache.causeway.core.metamodel.consent.InteractionInitiatedBy;
import org.apache.causeway.core.metamodel.object.ManagedObject;
import org.apache.causeway.viewer.graphql.model.context.Context;
import org.apache.causeway.viewer.graphql.model.domain.Environment;
import org.apache.causeway.viewer.graphql.model.domain.GqlvAbstract;
import org.apache.causeway.viewer.graphql.model.domain.common.interactors.ActionParamInteractor;
import org.apache.causeway.viewer.graphql.model.fetcher.BookmarkedPojo;
import org.apache.causeway.viewer.graphql.model.types.TypeMapper;

import lombok.val;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class GqlvActionParamsParamDisabled extends GqlvAbstract {

    private final ActionParamInteractor actionParamInteractor;

    public GqlvActionParamsParamDisabled(
            final ActionParamInteractor actionParamInteractor,
            final Context context) {
        super(context);
        this.actionParamInteractor = actionParamInteractor;

        val fieldBuilder = newFieldDefinition()
                .name("disabled")
                .type((GraphQLOutputType) context.typeMapper.outputTypeFor(String.class));
        actionParamInteractor.addGqlArguments(actionParamInteractor.getObjectMember(), fieldBuilder, TypeMapper.InputContext.DISABLE, actionParamInteractor.getParamNum()+1);
        setField(fieldBuilder.build());
    }

    @Override
    protected String fetchData(final DataFetchingEnvironment dataFetchingEnvironment) {

        val sourcePojo = BookmarkedPojo.sourceFrom(dataFetchingEnvironment);
        val objectSpecification = context.specificationLoader.loadSpecification(sourcePojo.getClass());
        if (objectSpecification == null) {
            return "Disabled";
        }

        val objectAction = actionParamInteractor.getObjectMember();
        val managedObject = ManagedObject.adaptSingular(objectSpecification, sourcePojo);
        val actionInteractionHead = objectAction.interactionHead(managedObject);

        val objectActionParameter = objectAction.getParameterById(actionParamInteractor.getObjectActionParameter().getId());
        val argumentManagedObjects = actionParamInteractor.argumentManagedObjectsFor(new Environment.For(dataFetchingEnvironment), objectAction, context.bookmarkService);

        val usable = objectActionParameter.isUsable(actionInteractionHead, argumentManagedObjects, InteractionInitiatedBy.USER);
        return usable.isVetoed() ? usable.getReasonAsString().orElse("Disabled") : null;
    }

}
