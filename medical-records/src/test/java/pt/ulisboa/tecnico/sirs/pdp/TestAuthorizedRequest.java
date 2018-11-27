package pt.ulisboa.tecnico.sirs.pdp;

import org.junit.Test;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAuthorizedRequest {
	
	@Test
	public void success() {

		Pep pep = new Pep();
		DecisionRequest request = pep.createRequest("indexPage");
		boolean result = pep.evaluateRequest(request);
		assertTrue(result);
	}
	
	@Test
	public void unsuccess() {

		Pep pep = new Pep();
		DecisionRequest request = pep.createRequest("doctorsPage");
		boolean result = pep.evaluateRequest(request);
		assertFalse(result);
	}

}
