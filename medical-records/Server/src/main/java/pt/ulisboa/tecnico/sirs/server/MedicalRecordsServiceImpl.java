package pt.ulisboa.tecnico.sirs.server;

import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;

public class MedicalRecordsServiceImpl implements MedicalRecordsService {
    @Override
    public String getWelcomePage() {
        return "WELCOME";
    }
}
