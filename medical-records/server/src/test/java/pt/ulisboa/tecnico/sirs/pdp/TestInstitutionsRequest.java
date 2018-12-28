package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestInstitutionsRequest {
	
	@Test
	public void superUserViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "institutionsPage", "");
		assertTrue(result);
	}

	@Test
	public void superUserCreatesInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "create", "institutionsPage", "");
		assertTrue(result);
	}

	@Test
	public void superUserDeletesInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "edit", "institutionsPage", "");
		assertTrue(result);
	}

	@Test
	public void superUserNoOtherActionInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "destroy", "institutionsPage", "");
		assertFalse(result);
	}

	@Test
	public void patientCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "institutionsPage", "");
		assertFalse(result);
	}

	@Test
	public void doctorCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "institutionsPage", "");
		assertFalse(result);
	}

	@Test
	public void adminCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "institutionsPage", "");
		assertFalse(result);
	}
}
