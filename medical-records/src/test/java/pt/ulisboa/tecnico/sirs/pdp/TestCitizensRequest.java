package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCitizensRequest {
	
	@Test
	public void SuperUserViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void SuperUserCreatesCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "create" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void SuperUserDeletesCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "edit" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void SuperUserNoOtherActionCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "destroy" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void PatientCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void DoctorCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void AdminCannotViewCitizens() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"citizensPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}
}
