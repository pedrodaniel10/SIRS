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
		boolean result = policyEnforcementPoint.requestEvaluation("indexPage", new ArrayList<>(), "view");
		assertTrue(result);
	}
	
	@Test
	public void NoOneCreateInIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("indexPage", new ArrayList<>(), "create");
		assertFalse(result);
	}

	@Test
	public void NoOneEditInIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("indexPage", new ArrayList<>(), "edit");
		assertFalse(result);
	}
}
