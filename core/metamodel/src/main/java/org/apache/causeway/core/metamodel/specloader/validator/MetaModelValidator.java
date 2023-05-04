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
package org.apache.causeway.core.metamodel.specloader.validator;

import java.util.function.Predicate;

import org.apache.causeway.core.metamodel.spec.ObjectSpecification;
import org.apache.causeway.core.metamodel.spec.feature.ObjectAction;
import org.apache.causeway.core.metamodel.spec.feature.ObjectActionParameter;
import org.apache.causeway.core.metamodel.spec.feature.OneToManyAssociation;
import org.apache.causeway.core.metamodel.spec.feature.OneToOneAssociation;
import org.apache.causeway.core.metamodel.specloader.specimpl.ObjectSpecificationAbstract;

public interface MetaModelValidator {

    /** whether this validator should run at all; eg. could be disabled via configuration */
    default boolean isEnabled() {
        return true;
    }

    /** this validator only processes {@link ObjectSpecification}(s) that pass this filter */
    Predicate<ObjectSpecification> getFilter();

    /** entry to meta-model validation */
    default void validateEnter() {}

    /** exit from meta-model validation */
    default void validateExit() {}

    /** entry to validation of specified {@code objSpec} */
    default void validateObjectEnter(final ObjectSpecification objSpec) {}

    /** exit from validation of specified {@code objSpec} */
    default void validateObjectExit(final ObjectSpecification objSpec) {}

    // -- MEMBER VALIDATORS

    /**
     * {@link MetaModelValidator}(s) may optionally implement this, to visit all actions.
     * @apiNote factored out into its own interface to allow for runtime optimizations
     */
    static interface ActionValidator {
        /** validate action - mixed-in included */
        void validateAction(final ObjectSpecification objSpec, final ObjectAction act);
    }

    /**
     * {@link MetaModelValidator}(s) may optionally implement this, to visit all action's parameters.
     * @apiNote factored out into its own interface to allow for runtime optimizations
     */
    static interface ParameterValidator {
        /** validate action-parameter - mixed-in included */
        void validateParameter(final ObjectSpecification objSpec, final ObjectAction act, final ObjectActionParameter param);
    }

    /**
     * {@link MetaModelValidator}(s) may optionally implement this, to visit all properties.
     * @apiNote factored out into its own interface to allow for runtime optimizations
     */
    static interface PropertyValidator {
        /** validate property - mixed-in included */
        void validateProperty(final ObjectSpecification objSpec, final OneToOneAssociation prop);
    }

    /**
     * {@link MetaModelValidator}(s) may optionally implement this, to visit all collections.
     * @apiNote factored out into its own interface to allow for runtime optimizations
     */
    static interface CollectionValidator {
        /** validate collection - mixed-in included */
        void validateCollection(final ObjectSpecification objSpec, final OneToManyAssociation coll);
    }

    // -- PREDEFINED FILTERS

    /** all types pass this filter */
    public final static Predicate<ObjectSpecification> ALL = __->true;

    /** no types pass this filter */
    public final static Predicate<ObjectSpecification> NONE = __->false;

    /** types pass this filter, if not-injectable (aka not managed by Spring) */
    public final static Predicate<ObjectSpecification> SKIP_MANAGED_BEANS =
            spec->!spec.isInjectable();

    /** types pass this filter, if not a mixin */
    public final static Predicate<ObjectSpecification> SKIP_MIXINS =
            spec->!spec.isMixin();

    /** types pass this filter, if either not {@link ObjectSpecificationAbstract} or member-annotation is not required */
    public final static Predicate<ObjectSpecification> SKIP_WHEN_MEMBER_ANNOT_REQUIRED =
            spec->(!(spec instanceof ObjectSpecificationAbstract)
                    || !((ObjectSpecificationAbstract)spec)
                    .getIntrospectionPolicy()
                    .getMemberAnnotationPolicy()
                    .isMemberAnnotationsRequired());

}
