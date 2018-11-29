package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestIndexRequest {
	
	@Test
	public void AnyoneViewIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		DecisionRequest request = policyEnforcementPoint.createRequest("indexPage", new ArrayList<>(), "view");
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}
	
	@Test
	public void NoOneCreateInIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		DecisionRequest request = policyEnforcementPoint.createRequest("indexPage", new ArrayList<>(), "create");
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void NoOneEditInIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		DecisionRequest request = policyEnforcementPoint.createRequest("indexPage", new ArrayList<>(), "edit");
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}
}
