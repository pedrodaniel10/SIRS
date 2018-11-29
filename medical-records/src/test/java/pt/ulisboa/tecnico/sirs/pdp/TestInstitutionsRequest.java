package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestInstitutionsRequest {
	
	@Test
	public void superUserViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "view" );
		assertTrue(result);
	}

	@Test
	public void superUserCreatesInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "create" );
		assertTrue(result);
	}

	@Test
	public void superUserDeletesInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "edit" );
		assertTrue(result);
	}

	@Test
	public void superUserNoOtherActionInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "destroy" );
		assertFalse(result);
	}

	@Test
	public void patientCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "view" );
		assertFalse(result);
	}

	@Test
	public void doctorCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "view" );
		assertFalse(result);
	}

	@Test
	public void adminCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"institutionsPage", roles, "view" );
		assertFalse(result);
	}
}
