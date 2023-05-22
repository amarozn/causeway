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
package demoapp.dom.domain.actions.progmodel.bulk.jpa;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import demoapp.dom._infra.values.ValueHolderRepository;
import demoapp.dom.domain.actions.progmodel.bulk.BulkActionItemEntity;
import demoapp.dom.domain.actions.progmodel.bulk.BulkActionItemEntityRepository;

@Profile("demo-jpa")
@Service
public class BulkActionItemEntityImplRepository
extends ValueHolderRepository<String, BulkActionItemEntityImpl> implements BulkActionItemEntityRepository {

    protected BulkActionItemEntityImplRepository() {
        super(BulkActionItemEntityImpl.class);
    }

    @Override
    protected BulkActionItemEntityImpl newDetachedEntity(String value) {
        return new BulkActionItemEntityImpl(value);
    }

    @Override
    public List<? extends BulkActionItemEntity> allInstances() {
        return all();
    }

    public List<? extends BulkActionItemEntity> allMatches(final String s) {
        return all();
    }
    public List<? extends BulkActionItemEntity> allMatches() {
        return all();
    }
}