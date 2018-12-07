package pt.ulisboa.tecnico.sirs.server;

import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.pdp.PolicyEnforcementPoint;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsServiceImpl implements MedicalRecordsService {

    public Boolean requestEvaluation(String subjectId, List<String> roles, String action, String resourceName, String resourceId) {
        PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();

        return policyEnforcementPoint.requestEvaluation(subjectId,
                roles, action, resourceName, resourceId);
    }

    @Override
    public Boolean getWelcomePage() {

        ArrayList<String> roles = new ArrayList<>();
        return requestEvaluation("",
                roles, "view", "indexPage", "");
    }

    @Override
    public Boolean getLoginRegisterPage() {

        ArrayList<String> roles = new ArrayList<>();
        return requestEvaluation("",
                roles, "view", "loginRegisterPage", "");
    }

    @Override
    public Boolean postLoginRegisterPage() {

        ArrayList<String> roles = new ArrayList<>();
        return requestEvaluation("",
                roles, "edit", "loginRegisterPage", "");
    }


}
