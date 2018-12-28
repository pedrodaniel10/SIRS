package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDoctorsRequest {


	@Test
	public void adminViewDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "doctorsPage", "");
		assertTrue(result);
	}

	@Test
	public void adminCreatesDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "create", "doctorsPage", "");
		assertTrue(result);
	}

	@Test
	public void adminEditsDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "edit", "doctorsPage", "");
		assertTrue(result);
	}

	@Test
	public void adminNoOtherActionDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "destroy", "doctorsPage", "");
		assertFalse(result);
	}

	@Test
	public void patientCannotViewDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "doctorsPage", "");
		assertFalse(result);
	}

	@Test
	public void doctorCannotViewDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "doctorsPage", "");
		assertFalse(result);
	}

	@Test
	public void superUserCannotViewDoctors() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "doctorsPage", "");
		assertFalse(result);
	}
}
