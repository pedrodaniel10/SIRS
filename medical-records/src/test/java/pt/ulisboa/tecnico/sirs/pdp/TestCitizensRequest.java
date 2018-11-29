package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.security.Principal;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCitizensRequest {
	
	@Test
	public void superUserViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "view" );
		assertTrue(result);
	}

	@Test
	public void superUserCreatesCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "create" );
		assertTrue(result);
	}

	@Test
	public void superUserCannotDeleteCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "edit" );
		assertFalse(result);
	}

	@Test
	public void superUserNoOtherActionCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "destroy" );
		assertFalse(result);
	}

	@Test
	public void patientCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "view" );
		assertFalse(result);
	}

	@Test
	public void doctorCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "view" );
		assertFalse(result);
	}

	@Test
	public void adminCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"citizensPage", roles, "view" );
		assertFalse(result);
	}
}
