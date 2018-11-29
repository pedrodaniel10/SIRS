package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLoginRegisterRequest {
	
	@Test
	public void anyoneViewLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("loginRegisterPage", new ArrayList<>(), "view");
		assertTrue(result);
	}
	
	@Test
	public void anyoneEditInLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("loginRegisterPage", new ArrayList<>(), "edit");
		assertTrue(result);
	}

	@Test
	public void noOneEditInLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("loginRegisterPage", new ArrayList<>(), "create");
		assertFalse(result);
	}
}
