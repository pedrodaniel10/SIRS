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

import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_3_0_RESOURCE;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/* This class represents a XACML PEP that, for every request, creates an XACML authorization decision Request to the PDP */
public class Pep {
	private static final Logger log = Logger.getLogger(Pep.class);

	private BasePdpEngine pdp;

	public Pep() {
		try {
			File file = new ClassPathResource("pdp.xml").getFile();
			log.info("File:" + file.getAbsolutePath());
			PdpEngineConfiguration pdpEngineConf = PdpEngineConfiguration.getInstance(file.getAbsolutePath());
			pdp = new BasePdpEngine(pdpEngineConf);
		} catch (IOException e) {
			log.error("Enable to find configuration file: ");
		}
	}

	/* Create the XACML request in native model */
	public DecisionRequest createRequest(String page) {

		//TODO: Update Attribute and Category numbers in the end
		final DecisionRequestBuilder<?> requestBuilder = pdp.newRequestBuilder(-1, -1);
		addResourceId(requestBuilder, page);
		return requestBuilder.build(false);

	}

	public boolean evaluateRequest(DecisionRequest request) {
		DecisionResult result = pdp.evaluate(request);
		if(result.getDecision() == DecisionType.PERMIT) {
			return true;
		} else {
			return false;
		}
	}

	private void addResourceId(DecisionRequestBuilder<?> requestBuilder, String page) {
		final AttributeFqn resourceIdAttributeName = AttributeFqns.newInstance(
				XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_ID.value());
		final AttributeBag<?> resourceIdAttributeValues = Bags.singletonAttributeBag(
				StandardDatatypes.STRING, new StringValue(page));

		requestBuilder.putNamedAttributeIfAbsent(resourceIdAttributeName, resourceIdAttributeValues);
	}


}