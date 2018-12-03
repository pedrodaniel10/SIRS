package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMedicalRecordsRequest {

	@Test
	public void patientViewMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "medicalRecordsPage", "");
		assertTrue(result);
	}

	@Test
	public void patientCreatesMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "create", "medicalRecordsPage", "");
		assertFalse(result);
	}

	@Test
	public void patientEditMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "edit", "medicalRecordsPage", "");
		assertFalse(result);
	}

	@Test
	public void patientNoOtherActionMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("PATIENT");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "destroy", "medicalRecordsPage", "");
		assertFalse(result);
	}

	@Test
	public void doctorViewMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "medicalRecordsPage", "");
		assertTrue(result);
	}

	@Test
	public void doctorCreatesMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "create", "medicalRecordsPage", "");
		assertTrue(result);
	}

	@Test
	public void doctorEditMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "edit", "medicalRecordsPage", "");
		assertFalse(result);
	}

	@Test
	public void doctorNoOtherActionMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("DOCTOR");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "destroy", "medicalRecordsPage", "");
		assertFalse(result);
	}

	@Test
	public void adminCannotViewMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "medicalRecordsPage", "");
		assertFalse(result);
	}

	@Test
	public void superUserCannotViewMedicalRecords() {

		PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();
		ArrayList<String> roles = new ArrayList<>();
		roles.add("SUPERUSER");
		boolean result = policyEnforcementPoint.requestEvaluation("",
				roles, "view", "medicalRecordsPage", "");
		assertFalse(result);
	}
}
