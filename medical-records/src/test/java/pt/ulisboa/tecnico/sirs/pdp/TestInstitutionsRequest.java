package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestInstitutionsRequest {
	
	@Test
	public void SuperUserViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void SuperUserCreatesInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "create" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void SuperUserDeletesInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "edit" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertTrue(result);
	}

	@Test
	public void SuperUserNoOtherActionInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "destroy" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void PatientCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void DoctorCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}

	@Test
	public void AdminCannotViewInstitutions() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		DecisionRequest request = policyEnforcementPoint.createRequest(
				"institutionsPage", roles, "view" );
		boolean result = policyEnforcementPoint.evaluateRequest(request);
		assertFalse(result);
	}
}
