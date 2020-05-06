package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.Action;
import pl.detectivegame.model.DAO.*;
import pl.detectivegame.model.ItemType;
import pl.detectivegame.model.Successor;
import pl.detectivegame.model.SuccessorType;
import pl.detectivegame.payload.creation.ActionPayload;
import pl.detectivegame.repository.*;
import pl.detectivegame.util.mapper.ActionMapper;
import pl.detectivegame.util.mapper.ItemMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActionService {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ActionActionRepository actionActionRepository;

    @Autowired
    ActionItemRepository actionItemRepository;

    @Autowired
    ActionLocationRepository actionLocationRepository;

    @Autowired
    ItemRepository itemRepository;


    public void deleteAction(ActionPayload actionPayload) {
        pl.detectivegame.model.DAO.Action actionDAO = getActionDAOWithId(actionPayload);
        Long actionId = actionDAO.getActionId();
        deleteUnusedActionActions(actionId);
        deleteActionItems(actionId);
        deleteUnusedActionLocations(actionId);
        actionRepository.delete(actionDAO);
    }

    private void deleteUnusedActionLocations(Long actionId) {
        List<ActionLocation> actionLocationList = actionLocationRepository.findAllByActionLocationIdentity_ActionId(actionId);
        actionLocationRepository.deleteAll(actionLocationList);
    }

    private void deleteActionItems(Long actionId) {
        List<ActionItem> actionItemList = actionItemRepository.findAllByActionItemIdentity_ActionId(actionId);
        actionItemRepository.deleteAll(actionItemList);
    }

    private void deleteUnusedActionActions(Long actionId) {
        List<ActionAction> actionActionList = actionActionRepository.findAllByActionActionIdentity_ActionIdOrActionActionIdentity_RevealedId(actionId,actionId);
        actionActionRepository.deleteAll(actionActionList);
    }

    private pl.detectivegame.model.DAO.Action getActionDAO(ActionPayload actionPayload) {
        pl.detectivegame.model.Action action = actionPayload.getAction();
        Location location = locationRepository.findByNameEqualsAndCaseIdEquals(action.getLocation(), action.getCaseId());
        return ActionMapper.mapWithoutSuccesorsAndId(action, location);
    }

    private pl.detectivegame.model.DAO.Action getActionDAOWithId(ActionPayload actionPayload) {
        Action action = actionPayload.getAction();
        Location location = locationRepository.findByNameEqualsAndCaseIdEquals(action.getLocation(), action.getCaseId());
        return ActionMapper.mapWithoutSuccesors(action, location);
    }

    public ActionPayload createAction(ActionPayload actionPayload) {
        pl.detectivegame.model.DAO.Action actionDAO = getActionDAO(actionPayload);
        actionDAO = actionRepository.save(actionDAO);
        actionPayload.setAction(ActionMapper.map(actionDAO));
        return actionPayload;
    }

    public ActionPayload updateAction(ActionPayload actionPayload) {
        pl.detectivegame.model.DAO.Action actionDAO = updateActionInfo(actionPayload);
        actionDAO = actionRepository.save(actionDAO);
        List<Successor> successors = actionPayload.getAction().getSuccessors();
        Long actionId = actionDAO.getActionId();
        updateSuccesors(successors, actionId);
        createResponse(actionPayload, actionDAO, actionId);
        return actionPayload;
    }

    private void createResponse(ActionPayload actionPayload, pl.detectivegame.model.DAO.Action actionDAO, Long actionId) {
        itemRepository.flush();
        List<Item> allItems = itemRepository.findAllInCase(actionDAO.getCaseId());
        List<pl.detectivegame.model.Item> items = getItems(allItems);
        actionRepository.flush();
        actionPayload.setAction(ActionMapper.map(actionRepository.findById(actionId).get(),items));
    }

    private pl.detectivegame.model.DAO.Action updateActionInfo(ActionPayload actionPayload) {
        pl.detectivegame.model.DAO.Action requestAction = getActionDAOWithId(actionPayload);
        pl.detectivegame.model.DAO.Action actionDAO = actionRepository.getOne(actionPayload.getAction().getActionId());
        actionDAO.setLocation(requestAction.getLocation());
        actionDAO.setDescription(requestAction.getDescription());
        actionDAO.setTime(requestAction.getTime());
        actionDAO.setImage(requestAction.getImage());
        return actionDAO;
    }

    private void updateSuccesors(List<Successor> successors, Long actionId) {
        List<ActionAction> actionActions = new ArrayList<>();
        List<ActionItem> actionItemList = new ArrayList<>();
        List<ActionLocation> actionLocationList = new ArrayList<>();
        convertSuccesorsToDAOs(successors, actionId, actionActions, actionItemList, actionLocationList);
        deleteConnections(actionId, actionActions, actionItemList, actionLocationList);
        actionActionRepository.saveAll(actionActions);
        actionItemRepository.saveAll(actionItemList);
        actionLocationRepository.saveAll(actionLocationList);
        actionActionRepository.flush();
        actionItemRepository.flush();
        actionLocationRepository.flush();
    }

    private void deleteConnections(Long actionId, List<ActionAction> actionActions, List<ActionItem> actionItemList, List<ActionLocation> actionLocationList) {
        deleteUnusedActionActions(actionId, actionActions);
        deleteUnusedActionLocations(actionId, actionLocationList);
        deleteUnusedActionItems(actionId, actionItemList);
    }

    private void deleteUnusedActionItems(Long actionId, List<ActionItem> actionItemList) {
        Set<Long> itemIds = actionItemList.stream().map(actionItem -> actionItem.getActionItemIdentity().getItemId()).collect(Collectors.toSet());
        List<ActionItem> actionItemsDb = actionItemRepository.findAllByActionItemIdentity_ActionId(actionId);
        actionItemsDb = actionItemsDb.stream()
                .filter(actionItem -> !itemIds.contains(actionItem.getActionItemIdentity().getItemId())).collect(Collectors.toList());
        actionItemRepository.deleteAll(actionItemsDb);
    }

    private void deleteUnusedActionLocations(Long actionId, List<ActionLocation> actionLocationList) {
        List<ActionLocation> actionLocationsDb = actionLocationRepository.findAllByActionLocationIdentity_ActionId(actionId);
        Set<Long> locationIds = actionLocationList.stream().map(actionLocation -> actionLocation.getActionLocationIdentity().getLocationId()).collect(Collectors.toSet());
        actionLocationsDb = actionLocationsDb.stream()
                .filter(actionLocation -> !locationIds.contains(actionLocation.getActionLocationIdentity().getLocationId())).collect(Collectors.toList());
        actionLocationRepository.deleteAll(actionLocationsDb);
    }

    private void deleteUnusedActionActions(Long actionId, List<ActionAction> actionActions) {
        List<ActionAction> actionActionsDb = actionActionRepository.findAllByActionActionIdentity_ActionId(actionId);
        Set<Long> revealedActionsIds = actionActions.stream().map(actionAction -> actionAction.getActionActionIdentity().getRevealedId()).collect(Collectors.toSet());
        actionActionsDb = actionActionsDb.stream()
                .filter(actionAction -> !revealedActionsIds.contains(actionAction.getActionActionIdentity().getRevealedId())).collect(Collectors.toList());
        actionActionRepository.deleteAll(actionActionsDb);
    }

    private void convertSuccesorsToDAOs(List<Successor> successors, Long actionId, List<ActionAction> actionActions, List<ActionItem> actionItemList, List<ActionLocation> actionLocationList) {
        for (Successor successor : successors) {
            if (SuccessorType.LOCATIONS.getValue().equals(successor.getType())) {
                actionLocationList.add(
                        ActionLocation.builder()
                                .actionLocationIdentity(
                                        ActionLocationIdentity
                                                .builder()
                                                .actionId(actionId)
                                                .locationId(successor.getId())
                                                .build())
                                .build());
            }
            if (SuccessorType.PEOPLE.getValue().equals(successor.getType()) || SuccessorType.ITEMS.getValue().equals(successor.getType())) {
                actionItemList.add(
                        ActionItem.builder()
                                .actionItemIdentity(
                                        ActionItemIdentity
                                                .builder()
                                                .actionId(actionId)
                                                .itemId(successor.getId())
                                                .build())
                                .build());
            }
            if (SuccessorType.ACTIONS.getValue().equals(successor.getType())) {
                actionActions.add(
                        ActionAction.builder()
                                .actionActionIdentity(
                                        ActionActionIdentity
                                                .builder()
                                                .actionId(actionId)
                                                .revealedId(successor.getId())
                                                .build())
                                .build());
            }
        }
    }

    private List<pl.detectivegame.model.Item> getItems(List<Item> allItems) {
        return allItems.stream().filter(item -> item.getTypeOfItem().equals(ItemType.ITEM.getType())).map(item -> ItemMapper.mapToItem(item)).collect(Collectors.toList());
    }
}
