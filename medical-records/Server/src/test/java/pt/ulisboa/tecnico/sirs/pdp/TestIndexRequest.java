package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestIndexRequest {
	
	@Test
	public void AnyoneViewIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("", new ArrayList<>(), "view", "indexPage", "");
		assertTrue(result);
	}
	
	@Test
	public void NoOneCreateInIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("", new ArrayList<>(), "create", "indexPage", "");
		assertFalse(result);
	}

	@Test
	public void NoOneEditInIndex() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("", new ArrayList<>(), "edit", "indexPage", "");
		assertFalse(result);
	}
}
