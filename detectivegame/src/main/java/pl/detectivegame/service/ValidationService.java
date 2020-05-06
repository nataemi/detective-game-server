package pl.detectivegame.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import pl.detectivegame.payload.creation.ValidatePayload;

@Service
public class ValidationService {



    public ValidatePayload validateDetectiveCase(Long caseId){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("ksession-rules");
//        kieSession.setGlobal("action", action);
//        kieSession.insert(action);
        kieSession.fireAllRules();
        return ValidatePayload.builder().build();
    }
}
