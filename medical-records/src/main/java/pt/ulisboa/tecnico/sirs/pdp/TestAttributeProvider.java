package pt.ulisboa.tecnico.sirs.pdp;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.ow2.authzforce.core.pdp.api.AttributeFqn;
import org.ow2.authzforce.core.pdp.api.AttributeProvider;
import org.ow2.authzforce.core.pdp.api.BaseNamedAttributeProvider;
import org.ow2.authzforce.core.pdp.api.CloseableNamedAttributeProvider;
import org.ow2.authzforce.core.pdp.api.EnvironmentProperties;
import org.ow2.authzforce.core.pdp.api.EvaluationContext;
import org.ow2.authzforce.core.pdp.api.IndeterminateEvaluationException;
import org.ow2.authzforce.core.pdp.api.io.NamedXacmlAttributeParser;
import org.ow2.authzforce.core.pdp.api.io.NonIssuedLikeIssuedStrictXacmlAttributeParser;
import org.ow2.authzforce.core.pdp.api.io.XacmlJaxbParsingUtils.NamedXacmlJaxbAttributeParser;
import org.ow2.authzforce.core.pdp.api.io.XacmlRequestAttributeParser;
import org.ow2.authzforce.core.pdp.api.value.AttributeBag;
import org.ow2.authzforce.core.pdp.api.value.AttributeValue;
import org.ow2.authzforce.core.pdp.api.value.AttributeValueFactoryRegistry;
import org.ow2.authzforce.core.pdp.api.value.Bag;
import org.ow2.authzforce.core.pdp.api.value.Datatype;
import org.ow2.authzforce.xacml.identifiers.XacmlStatusCode;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attribute;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeDesignatorType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.Attributes;
import pt.ulisboa.tecnico.sirs.pdp.attributeprovider.TestAttributeProviderDescriptor;

/**
 * Fake AttributeProviderModule for test purposes only that can be configured to support a specific set of attribute Providers, but always return an empty bag as attribute value.
 */
public class TestAttributeProvider extends BaseNamedAttributeProvider {

    private static AttributeDesignatorType newAttributeDesignator(final Entry<AttributeFqn, AttributeBag<?>> attributeEntry) {
        final AttributeFqn attrKey = attributeEntry.getKey();
        final Bag<?> attrVals = attributeEntry.getValue();
        return new AttributeDesignatorType(attrKey.getCategory(), attrKey.getId(), attrVals.getElementDatatype().getId(), attrKey.getIssuer().orElse(null), false);
    }

    private final Set<AttributeDesignatorType> supportedDesignatorTypes;
    private final Map<AttributeFqn, AttributeBag<?>> attrMap;

    private TestAttributeProvider(final String id, final Map<AttributeFqn, AttributeBag<?>> attributeMap) throws IllegalArgumentException {
        super(id);
        attrMap = Collections.unmodifiableMap(attributeMap);
        final Set<AttributeDesignatorType> mutableSupportedAttDesignatorSet = attrMap.entrySet().stream().map(attEntry -> newAttributeDesignator(attEntry)).collect(Collectors.toSet());
        this.supportedDesignatorTypes = Collections.unmodifiableSet(mutableSupportedAttDesignatorSet);
    }

    @Override
    public void close() throws IOException {
        // nothing to close
    }

    @Override
    public Set<AttributeDesignatorType> getProvidedAttributes() {
        return supportedDesignatorTypes;
    }

    @Override
    public <AV extends AttributeValue> AttributeBag<AV> get(final AttributeFqn attributeGUID, final Datatype<AV> attributeDatatype, final EvaluationContext context)
            throws IndeterminateEvaluationException {
        final AttributeBag<?> attrVals = attrMap.get(attributeGUID);
        if (attrVals == null) {
            return null;
        }

        if (attrVals.getElementDatatype().equals(attributeDatatype)) {
            return (AttributeBag<AV>) attrVals;
        }

        throw new IndeterminateEvaluationException("Requested datatype (" + attributeDatatype + ") != provided by " + this + " (" + attrVals.getElementDatatype() + ")",
                XacmlStatusCode.MISSING_ATTRIBUTE.value());
    }

    /**
     * {@link TestAttributeProvider} factory
     */
    public static class Factory extends CloseableNamedAttributeProvider.FactoryBuilder<TestAttributeProviderDescriptor> {

        @Override
        public Class<TestAttributeProviderDescriptor> getJaxbClass() {
            return TestAttributeProviderDescriptor.class;
        }

        @Override
        public DependencyAwareFactory getInstance(final TestAttributeProviderDescriptor conf, final EnvironmentProperties environmentProperties) {
            return new DependencyAwareFactory() {

                @Override
                public Set<AttributeDesignatorType> getDependencies() {
                    // no dependency
                    return null;
                }

                @Override
                public CloseableNamedAttributeProvider getInstance(final AttributeValueFactoryRegistry attributeValueFactories, final AttributeProvider depAttrProvider) {
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

                    return new TestAttributeProvider(conf.getId(), mutableAttMap);
                }
            };
        }

    }
}