package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCitizensRequest {
	
	@Test
	public void superUserViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "citizensPage", "");
		assertTrue(result);
	}

	@Test
	public void superUserCreatesCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "create", "citizensPage", "");
		assertTrue(result);
	}

	@Test
	public void superUserCannotDeleteCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "edit", "citizensPage", "");
		assertFalse(result);
	}

	@Test
	public void superUserNoOtherActionCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "destroy", "citizensPage", "");
		assertFalse(result);
	}

	@Test
	public void patientCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "citizensPage", "");
		assertFalse(result);
	}

	@Test
	public void doctorCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "citizensPage", "");
		assertFalse(result);
	}

	@Test
	public void adminCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "citizensPage", "");
		assertFalse(result);
	}
}
