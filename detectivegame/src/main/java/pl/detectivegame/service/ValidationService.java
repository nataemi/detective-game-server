package pl.detectivegame.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.NewDetectiveCase;
import pl.detectivegame.model.VerficationStatus;
import pl.detectivegame.payload.creation.ValidatePayload;

import java.util.ArrayList;
import java.util.List;


@Service
public class ValidationService {

    @Autowired
    DetectiveCaseService detectiveCaseService;

    public ValidatePayload validateDetectiveCase(Long caseId){
        NewDetectiveCase detectiveCase = detectiveCaseService.getNewDetectiveCaseById(caseId).getNewDetectiveCase();
        return validateDetectiveCase(detectiveCase);
    }

    public ValidatePayload validateDetectiveCase(NewDetectiveCase detectiveCase) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("ksession-rules");
        kieSession.insert(detectiveCase);
        List<String> wrongList = new ArrayList<>();
        List<String> warningList = new ArrayList<>();
        kieSession.setGlobal("wrongList",wrongList);
        kieSession.setGlobal("warningList",warningList);
        kieSession.fireAllRules();
        ValidatePayload validatePayload =
                ValidatePayload.builder()
                        .warningList(warningList)
                        .wrongList(wrongList)
                        .status(warningList.isEmpty() && wrongList.isEmpty() ? VerficationStatus.VALID : VerficationStatus.NOTVALID)
                        .build();
        return validatePayload;
    }
}
