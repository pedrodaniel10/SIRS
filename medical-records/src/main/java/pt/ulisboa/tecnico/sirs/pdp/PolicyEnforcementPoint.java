package pt.ulisboa.tecnico.sirs.pdp;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.DecisionType;
import org.apache.log4j.Logger;
import org.ow2.authzforce.core.pdp.api.*;
import org.ow2.authzforce.core.pdp.api.value.AttributeBag;
import org.ow2.authzforce.core.pdp.api.value.Bags;
import org.ow2.authzforce.core.pdp.api.value.StandardDatatypes;
import org.ow2.authzforce.core.pdp.api.value.StringValue;
import org.ow2.authzforce.core.pdp.impl.BasePdpEngine;
import org.ow2.authzforce.core.pdp.impl.PdpEngineConfiguration;
import org.ow2.authzforce.xacml.identifiers.XacmlAttributeId;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.*;

/* This class represents a XACML PEP that, for every request, creates an XACML authorization decision Request to the PDP */
public class PolicyEnforcementPoint {
	private static final Logger log = Logger.getLogger(PolicyEnforcementPoint.class);

	private BasePdpEngine pdp;

	public PolicyEnforcementPoint() {
		try {
			File file = new ClassPathResource("pdp/pdp.xml").getFile();
			log.info("File:" + file.getAbsolutePath());
			PdpEngineConfiguration pdpEngineConf = PdpEngineConfiguration.getInstance(file.getAbsolutePath());
			pdp = new BasePdpEngine(pdpEngineConf);
		} catch (IOException e) {
			log.error("Enable to find configuration file: ");
		}
	}

	/* Create the XACML request in native model */
	public DecisionRequest createRequest(String resource, List<String> roles, String action) {

		//TODO: Update Attribute and Category numbers in the end
		final DecisionRequestBuilder<?> requestBuilder = pdp.newRequestBuilder(-1, -1);
		addResourceAttribute(requestBuilder, resource);
		addRoleAttribute(requestBuilder, AuthzForceUtils.parseStringList(roles));
		addActionAttribute(requestBuilder, action);
		return requestBuilder.build(false);
	}

	public boolean evaluateRequest(DecisionRequest request) {
		DecisionResult result = pdp.evaluate(request);
		return result.getDecision() == DecisionType.PERMIT;
	}

	private void addResourceAttribute(DecisionRequestBuilder<?> requestBuilder, String resource) {
		final AttributeFqn resourceAttributeId = AttributeFqns.newInstance(
				XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_ID.value());
		final AttributeBag<?> resourceAttributeValues = Bags.singletonAttributeBag(
				StandardDatatypes.STRING, new StringValue(resource));

		requestBuilder.putNamedAttributeIfAbsent(resourceAttributeId, resourceAttributeValues);
	}

	private void addRoleAttribute(DecisionRequestBuilder<?> requestBuilder, List<StringValue> roles) {
		final AttributeFqn roleAttributeId = AttributeFqns.newInstance(
				XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_2_0_SUBJECT_ROLE.value());
		final AttributeBag<?> roleAttributeValues = Bags.newAttributeBag(StandardDatatypes.STRING, roles); //TODO: TEST SEVERAL ROLES

		requestBuilder.putNamedAttributeIfAbsent(roleAttributeId, roleAttributeValues);
	}

	private void addActionAttribute(DecisionRequestBuilder<?> requestBuilder, String action) {
		final AttributeFqn actionAttributeId = AttributeFqns.newInstance(
				XACML_3_0_ACTION.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_ACTION_ID.value());
		final AttributeBag<?> actionAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(action));

		requestBuilder.putNamedAttributeIfAbsent(actionAttributeId, actionAttributeValues);
	}
}