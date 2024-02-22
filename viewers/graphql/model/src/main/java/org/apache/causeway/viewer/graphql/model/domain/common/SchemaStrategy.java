package org.apache.causeway.viewer.graphql.model.domain.common;

import lombok.val;

import java.util.Map;

import org.apache.causeway.core.config.CausewayConfiguration;
import org.apache.causeway.core.metamodel.spec.ObjectSpecification;
import org.apache.causeway.core.metamodel.spec.feature.ObjectAction;
import org.apache.causeway.core.metamodel.spec.feature.OneToManyAssociation;
import org.apache.causeway.core.metamodel.spec.feature.OneToOneAssociation;
import org.apache.causeway.viewer.graphql.model.context.Context;
import org.apache.causeway.viewer.graphql.model.domain.GqlvAbstractCustom;
import org.apache.causeway.viewer.graphql.model.domain.SchemaType;
import org.apache.causeway.viewer.graphql.model.domain.common.query.GqlvDomainObject;
import org.apache.causeway.viewer.graphql.model.domain.common.query.GqlvDomainService;
import org.apache.causeway.viewer.graphql.model.domain.common.query.GqlvMemberHolder;
import org.apache.causeway.viewer.graphql.model.domain.rich.SchemaStrategyRich;
import org.apache.causeway.viewer.graphql.model.domain.simple.SchemaStrategySimple;

import static org.apache.causeway.viewer.graphql.model.domain.common.query.GqlvTopLevelQueryAbstractSchema.superclassesOf;

public interface SchemaStrategy {

    SchemaStrategy RICH = new SchemaStrategyRich();
    SchemaStrategy SIMPLE  = new SchemaStrategySimple();

    SchemaType getSchemaType();

    default GqlvDomainObject domainObjectFor(
            final ObjectSpecification objectSpecification,
            final Context context) {

        mapSuperclassesIfNecessary(this, objectSpecification, context);
        val typeNameFor = GqlvDomainObject.typeNameFor(this, objectSpecification);
        return context.domainObjectByTypeName.computeIfAbsent(typeNameFor, typeName -> new GqlvDomainObject(this, typeName, objectSpecification, context));
    }

    default GqlvDomainService domainServiceFor(
            final ObjectSpecification objectSpecification,
            final Object servicePojo,
            final Context context) {
        val typeNameFor = GqlvDomainService.typeNameFor(this, objectSpecification);
        return context.domainServiceByTypeName.computeIfAbsent(typeNameFor, typeName -> new GqlvDomainService(this, typeName, objectSpecification, servicePojo, context));
    }


    String topLevelFieldNameFrom(CausewayConfiguration.Viewer.Graphql graphqlConfiguration);

    GqlvAbstractCustom newGqlvProperty(
            final GqlvMemberHolder holder,
            final OneToOneAssociation otoa,
            final Context context
    );
    GqlvAbstractCustom newGqlvCollection(
            final GqlvMemberHolder holder,
            final OneToManyAssociation otma,
            final Context context
    );
    GqlvAbstractCustom newGqlvAction(
            final GqlvMemberHolder holder,
            final ObjectAction objectAction,
            final Context context
    );

    GqlvAbstractCustom newGqlvMeta(
            final GqlvDomainObject gqlvDomainObject,
            final Context context);

    private static void mapSuperclassesIfNecessary(
            final SchemaStrategy schemaStrategy,
            final ObjectSpecification objectSpecification,
            final Context context) {
        // no need to map if the target subclass has already been built
        val typeName = GqlvDomainObject.typeNameFor(schemaStrategy, objectSpecification);
        if(context.domainObjectByTypeName.containsKey(typeName)) {
            return;
        }
        val superclasses = superclassesOf(objectSpecification);
        superclasses.forEach(objectSpec -> {
            val typeNameForSuperclass = GqlvDomainObject.typeNameFor(schemaStrategy, objectSpecification);
            context.domainObjectByTypeName.computeIfAbsent(typeNameForSuperclass, typeNm -> new GqlvDomainObject(schemaStrategy, typeNm, objectSpecification, context));
        });
    }


}
