package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPatientsRequest {
	
	@Test
	public void doctorViewPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "view" );
		assertTrue(result);
	}

	@Test
	public void doctorCannotCreatesPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "create" );
		assertFalse(result);
	}

	@Test
	public void doctorCannotEditPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "edit" );
		assertFalse(result);
	}

	@Test
	public void doctorNoOtherActionPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "destroy" );
		assertFalse(result);
	}

	@Test
	public void doctorAndPatientViewPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "view" );
		assertTrue(result);
	}

	@Test
	public void doctorAndPatientCannotCreatePatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "create" );
		assertFalse(result);
	}

	@Test
	public void patientCannotViewPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "view" );
		assertFalse(result);
	}

	@Test
	public void adminCannotViewPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "view" );
		assertFalse(result);
	}

	@Test
	public void superUserCannotViewPatients() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation(
				"patientsPage", roles, "view" );
		assertFalse(result);
	}
}
