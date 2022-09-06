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
package org.apache.isis.testdomain.model.good;

import java.util.Set;

import org.apache.isis.applib.annotation.Collection;

/**
 * @see <a href="https://issues.apache.org/jira/browse/ISIS-3164">ISIS-3164</a>
 */
public abstract class ProperElementTypeVmAbstract {

    @Collection
    public abstract Set<ElementTypeInterface> getSetOfInterfaceType();

    @Collection
    public abstract Set<? extends ElementTypeConcrete> getSetOfConcreteType();

    // -- TESTING ITERABLE OVERRIDDEN BY CAN

    @Collection
    public abstract Iterable<ElementTypeInterface> getIterableOfInterfaceType();

    @Collection
    public abstract Iterable<? extends ElementTypeConcrete> getIterableOfConcreteType();

  //FIXME add Can support
//    @Action
//    abstract void act(ImmutableCollection<ElementTypeInterface> coll);
//
//    @MemberSupport
//    protected Can<ElementTypeInterface> choices0Act() {
//        return Can.ofSingleton(new ElementTypeConcrete());
//    }

}
