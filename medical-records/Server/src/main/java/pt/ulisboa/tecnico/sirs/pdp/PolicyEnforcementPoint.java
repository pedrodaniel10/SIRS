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

public class PolicyEnforcementPoint {
	private static final Logger log = Logger.getLogger(PolicyEnforcementPoint.class);

	private BasePdpEngine pdp;

	public PolicyEnforcementPoint() {
		try {
			/* confLocation - location of the PDP configuration file */
			File confLocation = new ClassPathResource("pdp/pdp.xml").getFile();
			/* catalogLocation - location of the XML catalog, used to resolve the configuration schemas */
			File catalogLocation = new ClassPathResource("jaxb/catalog.xml").getFile();
			/* extensionXsdLocation - location of the PDP extensions schema file */
			File extensionXsdLocation = new ClassPathResource("pdp/pdp-ext.xsd").getFile();

			PdpEngineConfiguration pdpEngineConf = PdpEngineConfiguration.getInstance(
					confLocation.getAbsolutePath(), catalogLocation.getAbsolutePath(), extensionXsdLocation.getAbsolutePath());
			pdp = new BasePdpEngine(pdpEngineConf);

		} catch (IOException e) {
			log.error("Enable to find configuration file" + e);
			System.exit(-1);
		}
	}

	/**
	 * This method creates the XACML request and evaluates the request.
	 * @param subjectId - citizenId of the person that makes the request
	 * @param roles - roles of the person that makes the request
	 * @param action - action performed (create/view/edit)
	 * @param resourceName - page
	 * @param resourceId - id associated with the page. p.ex medicalrecord id
	 * @return request evaluation (permit/deny)
	 */
	public boolean requestEvaluation(String subjectId, List<String> roles, String action, String resourceName, String resourceId) {
		DecisionRequest request = createRequest(subjectId, roles, action, resourceName, resourceId);
		return evaluateRequest(request);
	}

	/**
	 * This method translates the given params into a XACML request in native model
	 * @param subjectId - citizenId of the person that makes the request
	 * @param roles - roles of the person that makes the request
	 * @param action - action performed (create/view/edit)
	 * @param resourceName - page
	 * @param resourceId - id associated with the page. p.ex medicalrecord id
	 * @return XACML request
	 */
	private DecisionRequest createRequest(
			String subjectId, List<String> roles, String action, String resourceName, String resourceId) {

		final DecisionRequestBuilder<?> requestBuilder = pdp.newRequestBuilder(-1, -1);
		addResourceIdAttribute(requestBuilder, resourceId);
		addResourceNameAttribute(requestBuilder, resourceName);
		addSubjectIdAttribute(requestBuilder, subjectId);
		addRoleAttribute(requestBuilder, AuthzForceUtils.parseStringList(roles));
		addActionAttribute(requestBuilder, action);
		return requestBuilder.build(false);
	}

	/**
	 * This method evaluates the XACML request - permit/deny
	 * @param request XACML request
	 * @return request evaluation
	 */
	private boolean evaluateRequest(DecisionRequest request) {
		DecisionResult result = pdp.evaluate(request);
		return result.getDecision() == DecisionType.PERMIT;
	}

	/**
	 * This method adds the resourceId attribute to the XACML request
	 */
	private void addResourceIdAttribute(DecisionRequestBuilder<?> requestBuilder, String resourceId) {
		final AttributeFqn resourceIdAttributeId = AttributeFqns.newInstance(
				XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_ID.value());
		final AttributeBag<?> resourceIdAttributeValues = Bags.singletonAttributeBag(
				StandardDatatypes.STRING, new StringValue(resourceId));
		
		requestBuilder.putNamedAttributeIfAbsent(resourceIdAttributeId, resourceIdAttributeValues);
	}

	/**
	 * This method adds the resourceName attribute to the XACML request
	 */
	private void addResourceNameAttribute(DecisionRequestBuilder<?> requestBuilder, String resourceName) {
		final AttributeFqn resourceAttributeId = AttributeFqns.newInstance(
				XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_LOCATION.value());
		final AttributeBag<?> resourceAttributeValues = Bags.singletonAttributeBag(
				StandardDatatypes.STRING, new StringValue(resourceName));

		requestBuilder.putNamedAttributeIfAbsent(resourceAttributeId, resourceAttributeValues);
	}

	/**
	 * This method adds the subjectId attribute to the XACML request
	 */
	private void addSubjectIdAttribute(DecisionRequestBuilder<?> requestBuilder, String subjectId) {
		final AttributeFqn subjectIdAttributeId = AttributeFqns.newInstance(
				XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_SUBJECT_ID.value());
		final AttributeBag<?> subjectIdAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(subjectId));

		requestBuilder.putNamedAttributeIfAbsent(subjectIdAttributeId, subjectIdAttributeValues);
	}

	/**
	 * This method adds the subject roles attribute to the XACML request
	 */
	private void addRoleAttribute(DecisionRequestBuilder<?> requestBuilder, List<StringValue> roles) {
		final AttributeFqn roleAttributeId = AttributeFqns.newInstance(
				XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_2_0_SUBJECT_ROLE.value());
		final AttributeBag<?> roleAttributeValues = Bags.newAttributeBag(StandardDatatypes.STRING, roles);

		requestBuilder.putNamedAttributeIfAbsent(roleAttributeId, roleAttributeValues);
	}

	/**
	 * This method adds the action attribute to the XACML request
	 */
	private void addActionAttribute(DecisionRequestBuilder<?> requestBuilder, String action) {
		final AttributeFqn actionAttributeId = AttributeFqns.newInstance(
				XACML_3_0_ACTION.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_ACTION_ID.value());
		final AttributeBag<?> actionAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(action));

		requestBuilder.putNamedAttributeIfAbsent(actionAttributeId, actionAttributeValues);
	}
}