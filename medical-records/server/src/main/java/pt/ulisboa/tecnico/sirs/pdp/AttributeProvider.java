package pt.ulisboa.tecnico.sirs.pdp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.ow2.authzforce.core.pdp.api.*;
import org.ow2.authzforce.core.pdp.api.io.NamedXacmlAttributeParser;
import org.ow2.authzforce.core.pdp.api.io.NonIssuedLikeIssuedStrictXacmlAttributeParser;
import org.ow2.authzforce.core.pdp.api.io.XacmlJaxbParsingUtils.NamedXacmlJaxbAttributeParser;
import org.ow2.authzforce.core.pdp.api.io.XacmlRequestAttributeParser;
import org.ow2.authzforce.core.pdp.api.value.*;
import org.ow2.authzforce.xacml.identifiers.XacmlAttributeId;
import org.ow2.authzforce.xacml.identifiers.XacmlStatusCode;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attribute;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeDesignatorType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attributes;
import pt.ulisboa.tecnico.sirs.api.dataobjects.DocPatRelation;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;
import pt.ulisboa.tecnico.sirs.pdp.attributeprovider.AttributeProviderDescriptor;

import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_1_0_ACCESS_SUBJECT;
import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_3_0_RESOURCE;


public class AttributeProvider extends BaseNamedAttributeProvider {

    private final Set<AttributeDesignatorType> supportedDesignatorTypes;
    private final Map<AttributeFqn, AttributeBag<?>> attrMap;

    private static Logger log = Logger.getLogger(AttributeProvider.class);


    private AttributeProvider(final String id, final Map<AttributeFqn, AttributeBag<?>> attributeMap) throws IllegalArgumentException {
        super(id);
        attrMap = Collections.unmodifiableMap(attributeMap);
        final Set<AttributeDesignatorType> mutableSupportedAttDesignatorSet = attrMap.entrySet().stream().map(
                attEntry -> newAttributeDesignator(attEntry)).collect(Collectors.toSet());
        this.supportedDesignatorTypes = Collections.unmodifiableSet(mutableSupportedAttDesignatorSet);
    }

    private static AttributeDesignatorType newAttributeDesignator(final Entry<AttributeFqn, AttributeBag<?>> attributeEntry) {
        final AttributeFqn attrKey = attributeEntry.getKey();
        final Bag<?> attrVals = attributeEntry.getValue();
        return new AttributeDesignatorType(
                attrKey.getCategory(), attrKey.getId(), attrVals.getElementDatatype().getId(), attrKey.getIssuer().orElse(null), false);
    }

    @Override
    public void close() {
        // nothing to close
    }

    /**
     * This method returns the attributes this PIP resolves (configured in pdp.xml file)
     */
    @Override
    public Set<AttributeDesignatorType> getProvidedAttributes() {
        return supportedDesignatorTypes;
    }

    /**
     *
     * @param attributeGUID identifies an XACML attribute's fully qualified name
     * @param attributeDatatype expected attribute datatype
     * @param context request context, including the content from the current XACML Request and possibly extra attributes
     * @return the resolved value (retrieved from database)
     * @throws IndeterminateEvaluationException
     */
    @Override
    public <AV extends AttributeValue> AttributeBag<AV> get(
            final AttributeFqn attributeGUID, final Datatype<AV> attributeDatatype, final EvaluationContext context)
            throws IndeterminateEvaluationException {

        final AttributeBag<?> attrVals = attrMap.get(attributeGUID);
        if (attrVals == null) {
            return null;
        }

        if (attrVals.getElementDatatype().equals(attributeDatatype)) {

            /* get ResourceId from request context */
            final AttributeFqn resourceIdAttributeId = AttributeFqns.newInstance(
                    XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_ID.value());
            String resourceId = context.getNamedAttributeValue(resourceIdAttributeId, StandardDatatypes.STRING).getSingleElement().toString();

            /* get subjectId from request context */
            final AttributeFqn subjectIdAttributeId = AttributeFqns.newInstance(
                    XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_SUBJECT_ID.value());
            String subjectId = context.getNamedAttributeValue(subjectIdAttributeId, StandardDatatypes.STRING).getSingleElement().toString();

            /* Check if medical record patient is the subject id*/
            if(attributeGUID.getId().equals("pt.ulisboa.tecnico.sirs.medicalRecordPatient")) {
                boolean result = false;
                try {
                    Connection connection = (new DatabaseConnector()).getConnection();
                    List<DocPatRelation> relations = DatabaseUtils.getDocPatRelationsByDoctorId(connection, subjectId);
                    for (DocPatRelation relation : relations) {
                        if (relation.getPatientCitizenId().equals(resourceId)
                                && relation.getBeginDate().compareTo(new Date()) <= 0
                                && relation.getEndDate().compareTo(new Date()) >= 0) {
                            result = true;
                        }
                    }
                } catch (DatabaseConnectionException | SQLException e) {
                    log.error(e.getMessage());
                }
                AttributeBag<?> validRelationAttrValue = Bags.singletonAttributeBag(StandardDatatypes.BOOLEAN, new BooleanValue(true));
                return (AttributeBag<AV>) validRelationAttrValue;
            }

            /* Check if doctor has valid relation with the subject id (medical record patient) */
            if(attributeGUID.getId().equals("pt.ulisboa.tecnico.sirs.validRelation")) {
                boolean result = false;
                try {
                    Connection connection = (new DatabaseConnector()).getConnection();
                    List<DocPatRelation> relations = DatabaseUtils.getDocPatRelationsByDoctorId(connection, subjectId);
                    for (DocPatRelation relation : relations) {
                        if (relation.getPatientCitizenId().equals(resourceId)
                                && relation.getBeginDate().compareTo(new Date()) <= 0
                                && relation.getEndDate().compareTo(new Date()) >= 0) {
                            result = true;
                        }
                    }
                } catch (DatabaseConnectionException | SQLException e) {
                    log.error(e.getMessage());
                }
                AttributeBag<?> validRelationAttrValue = Bags.singletonAttributeBag(StandardDatatypes.BOOLEAN, new BooleanValue(result));
                return (AttributeBag<AV>) validRelationAttrValue;
            }
        }

        throw new IndeterminateEvaluationException("Requested datatype (" + attributeDatatype + ") != provided by " + this + " (" + attrVals.getElementDatatype() + ")",
                XacmlStatusCode.MISSING_ATTRIBUTE.value());
    }

    /**
     * Factory Design Pattern for creating AttributeProviders.
     * This class is given by Authzforce.
     * https://github.com/authzforce/core/blob/develop/pdp-testutils/src/main/java/org/ow2/authzforce/core/pdp/testutil/ext/TestAttributeProvider.java
     */
    public static class Factory extends CloseableNamedAttributeProvider.FactoryBuilder<AttributeProviderDescriptor> {

        @Override
        public Class<AttributeProviderDescriptor> getJaxbClass() {
            return AttributeProviderDescriptor.class;
        }

        @Override
        public DependencyAwareFactory getInstance(final AttributeProviderDescriptor conf, final EnvironmentProperties environmentProperties) {
            return new DependencyAwareFactory() {

                @Override
                public Set<AttributeDesignatorType> getDependencies() {
                    // no dependency
                    return null;
                }

                @Override
                public CloseableNamedAttributeProvider getInstance(final AttributeValueFactoryRegistry attributeValueFactories, final org.ow2.authzforce.core.pdp.api.AttributeProvider depAttrProvider) {
                    final NamedXacmlAttributeParser<Attribute> namedXacmlAttParser = new NamedXacmlJaxbAttributeParser(attributeValueFactories);
                    final XacmlRequestAttributeParser<Attribute, AttributeBag<?>> xacmlAttributeParser = new NonIssuedLikeIssuedStrictXacmlAttributeParser<>(namedXacmlAttParser);
                    final Set<String> attrCategoryNames = new HashSet<>();
                    final Map<AttributeFqn, AttributeBag<?>> mutableAttMap = new HashMap<>();
                    for (final Attributes jaxbAttributes : conf.getAttributes()) {
                        final String categoryName = jaxbAttributes.getCategory();
                        if (!attrCategoryNames.add(categoryName)) {
                            throw new IllegalArgumentException("Unsupported repetition of Attributes[@Category='" + categoryName + "']");
                        }

                        for (final Attribute jaxbAttr : jaxbAttributes.getAttributes()) {
                            xacmlAttributeParser.parseNamedAttribute(categoryName, jaxbAttr, null, mutableAttMap);
                        }
                    }

                    return new AttributeProvider(conf.getId(), mutableAttMap);
                }
            };
        }

    }
}