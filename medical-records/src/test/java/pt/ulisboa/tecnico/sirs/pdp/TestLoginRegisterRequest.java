package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLoginRegisterRequest {
	
	@Test
	public void AnyoneViewLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		DecisionRequest request = policyEnforcementPoint.createRequest("loginRegisterPage", new ArrayList<>(), "view");
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}
	
	@Test
	public void AnyoneEditInLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		DecisionRequest request = policyEnforcementPoint.createRequest("loginRegisterPage", new ArrayList<>(), "edit");
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void NoOneEditInLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		DecisionRequest request = policyEnforcementPoint.createRequest("loginRegisterPage", new ArrayList<>(), "create");
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}
}
