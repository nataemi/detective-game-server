package pl.detectivegame.service;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.*;
import pl.detectivegame.model.DAO.*;
import pl.detectivegame.model.DAO.Action;
import pl.detectivegame.model.DAO.Item;
import pl.detectivegame.payload.dashboard.AllDetectiveCasesResponse;
import pl.detectivegame.payload.gameplay.*;
import pl.detectivegame.repository.*;
import pl.detectivegame.util.mapper.ActionMapper;
import pl.detectivegame.util.mapper.DetectiveCaseMapper;
import pl.detectivegame.util.mapper.ItemMapper;
import pl.detectivegame.util.mapper.LocationConnectionResponseMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DetectiveCaseService {

    @Autowired
    private DetectiveCaseInfoRepository detectiveCaseInfoRepository;

    @Autowired
    SaveRepository saveRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetectiveCaseWithCreatorNameRepository detectiveCaseWithCreatorNameRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationConnectionRepository locationConnectionRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    QuestionRepository questionRepository;

    public DetectiveCaseInfoResponse createDetectiveCase(DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCaseInfo detectiveCaseInfo =
                DetectiveCaseInfo.builder()
                        .id(detectiveCaseRequest.getId())
                        .image(detectiveCaseRequest.getImage())
                        .name(detectiveCaseRequest.getName())
                        .time(detectiveCaseRequest.getTime())
                        .description(detectiveCaseRequest.getDescription())
                        .ready(detectiveCaseRequest.isReady())
                        .build();
        detectiveCaseInfo = detectiveCaseInfoRepository.save(detectiveCaseInfo);
        User creator = getCreator(detectiveCaseInfo);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCaseInfo, creator);
    }

    public DetectiveCaseInfoResponse getDetectiveCaseInfoById(Long detectiveCaseId) {
        DetectiveCaseInfo detectiveCaseInfo = detectiveCaseInfoRepository.findById(detectiveCaseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", detectiveCaseId));
        User creator = getCreator(detectiveCaseInfo);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCaseInfo, creator);
    }

    private User getCreator(DetectiveCaseInfo detectiveCaseInfo) {
        return userRepository.findById(detectiveCaseInfo.getCreator())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", detectiveCaseInfo.getCreator()));
    }

    public DetectiveCaseResponse getNewDetectiveCaseById(Long detectiveCaseId) {

        DetectiveCaseInfo detectiveCaseInfo = detectiveCaseInfoRepository.getOne(detectiveCaseId);
        List<Action> actions = actionRepository.findByCaseId(detectiveCaseId);
        List<Location> locations = locationRepository.findByCaseId(detectiveCaseId);
        List<LocationConnection> locationConnections = locationConnectionRepository.
                findAllByLocationConnectionIdentity_FromIdIn(locations.stream().map(Location::getLocationId).collect(Collectors.toList()));
        List<LocationConnectionWithName> paths = LocationConnectionResponseMapper.map(locationConnections, locations);
        List<Question> test = questionRepository.findByCaseId(detectiveCaseId);
        List<Item> allItems = itemRepository.findAllInCase(detectiveCaseId);
        List<pl.detectivegame.model.Item> items = getItems(allItems);
        List<Person> people = getPeople(allItems);

        DetectiveCaseResponse detectiveCaseResponse =
                DetectiveCaseResponse.builder().newDetectiveCase(
                        NewDetectiveCase.builder()
                                .actions(ActionMapper.map(actions,items))
                                .movementPoints(detectiveCaseInfo.getTime())
                                .frstActionId(detectiveCaseInfo.getFrstActionId())
                                .date(detectiveCaseInfo.getBgnDate())
                                .location(getFirstLocationName(locations))
                                .locations(locations)
                                .paths(paths)
                                .items(items)
                                .people(people)
                                .test(test)
                                .build()).build();
        return detectiveCaseResponse;
    }

    private List<pl.detectivegame.model.Item> getItems(List<Item> allItems) {
        return allItems.stream().filter(item -> item.getTypeOfItem().equals(ItemType.ITEM.getType())).map(item -> ItemMapper.mapToItem(item)).collect(Collectors.toList());
    }

    private List<Person> getPeople(List<Item> allItems) {
        return allItems.stream().filter(item -> item.getTypeOfItem().equals(ItemType.PERSON.getType())).map(item -> ItemMapper.mapToPerson(item)).collect(Collectors.toList());
    }

    private String getFirstLocationName(List<Location> locations) {
        if(!locations.isEmpty()) return locations.stream().filter(location -> location.isStart()).collect(Collectors.toList()).get(0).getName();
        else return null;
    }


    public ResponseEntity<SaveDetectiveCaseResponse> saveDetectiveCase(JsonObject saveDetectiveCaseRequest) {

        String caseId;
        String player;
        String saveJson;
        try {
             caseId = saveDetectiveCaseRequest.get("caseId").toString();
             player = saveDetectiveCaseRequest.get("playerId").toString();
             saveJson = saveDetectiveCaseRequest.get("saveJson").toString();
        }
        catch (Exception e){
            throw new IllegalArgumentException("Incorrect Request format or value");
        }


        Save save = Save.builder()
                .caseId(Long.parseLong(caseId))
                .player(Long.parseLong(player))
                .lastModified(new Date())
                .save_json(saveJson)
                .build();
        save = saveRepository.save(save);

        String saveId = save.getSave_id().toString();

        return ResponseEntity.ok(new SaveDetectiveCaseResponse(saveId));

    }

    public DetectiveCaseSaveResponse getDetectiveCaseSave(DetectiveCaseSaveRequest saveDetectiveCaseRequest) {

        Optional<Save> save = saveRepository.findFirstByPlayerAndCaseIdOrderByLastModifiedDesc(saveDetectiveCaseRequest.getUserId(),saveDetectiveCaseRequest.getCaseId());
        return DetectiveCaseSaveResponse.builder().jsonSave(save.map(o -> save.get().getSave_json()).orElse(null)).build();
    }

    public AllDetectiveCasesResponse getAllDetectiveCases() {
        List<DetectiveCaseInfoWithCreator> saves = detectiveCaseWithCreatorNameRepository.findAll();
        return AllDetectiveCasesResponse.builder().detectiveCaseList(saves).build();
    }
}
