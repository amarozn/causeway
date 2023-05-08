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
package demoapp.dom.domain.collections.CollectionLayout.sequence.child;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Named;
import javax.xml.bind.annotation.*;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.Title;

//tag::class[]
@Named("demo.CollectionLayoutSequenceChildVm")
@XmlRootElement(name = "demo.CollectionLayoutSequenceChildVm")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@DomainObject(nature=Nature.VIEW_MODEL)
@NoArgsConstructor
public class CollectionLayoutSequenceChildVm implements HasAsciiDocDescription {

//end::class[]
    public CollectionLayoutSequenceChildVm(final String value) {
        this.value = value;
    }

//tag::class[]
    @Title
    @Property()
    @XmlElement(required = true)
    @Getter @Setter
    private String value;

}
//end::class[]