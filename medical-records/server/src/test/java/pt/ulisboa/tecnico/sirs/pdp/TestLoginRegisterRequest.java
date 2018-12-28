package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLoginRegisterRequest {
	
	@Test
	public void anyoneViewLoginRegister() throws IOException {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("", new ArrayList<>(), "view", "loginRegisterPage", "");
		assertTrue(result);
	}
	
	@Test
	public void anyoneEditInLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("", new ArrayList<>(), "edit", "loginRegisterPage","");
		assertTrue(result);
	}

	@Test
	public void noOneEditInLoginRegister() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		boolean result = policyEnforcementPoint.requestEvaluation("", new ArrayList<>(), "create", "loginRegisterPage", "");
		assertFalse(result);
	}
}
