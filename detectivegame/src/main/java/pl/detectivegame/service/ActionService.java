package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.Action;
import pl.detectivegame.model.DAO.Location;
import pl.detectivegame.payload.creation.ActionPayload;
import pl.detectivegame.repository.ActionRepository;
import pl.detectivegame.repository.LocationRepository;
import pl.detectivegame.util.mapper.ActionMapper;

import java.util.Optional;

@Service
public class ActionService {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    LocationRepository locationRepository;


    public void deleteAction(ActionPayload actionPayload) {
        Optional<Action> action = actionRepository.findById(actionPayload.getAction().getActionId());
        actionRepository.delete(action.get());
        //TODO sprawdz czy wystarczy zeby usunac powiazania
    }

    public ActionPayload createAction(ActionPayload actionPayload) {
        pl.detectivegame.model.Action action = actionPayload.getAction();
        Location location = locationRepository.findByNameEqualsAndCaseIdEquals(action.getLocation(), action.getCaseId());
        Action actionDAO = ActionMapper.mapWithoutSuccesorsAndId(action, location);
        actionDAO = actionRepository.save(actionDAO);
        actionPayload.setAction(ActionMapper.map(actionDAO));
        return actionPayload;
    }
}
