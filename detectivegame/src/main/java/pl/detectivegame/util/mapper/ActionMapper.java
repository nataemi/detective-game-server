package pl.detectivegame.util.mapper;

import pl.detectivegame.model.Action;
import pl.detectivegame.model.DAO.ActionAction;
import pl.detectivegame.model.DAO.ActionItem;
import pl.detectivegame.model.DAO.ActionLocation;
import pl.detectivegame.model.DAO.Location;
import pl.detectivegame.model.Item;
import pl.detectivegame.model.Successor;
import pl.detectivegame.model.SuccessorType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActionMapper {

    public static List<Action> map(List<pl.detectivegame.model.DAO.Action> actions, List<Item> items){
        return actions.stream().map(action -> map(action, items)).collect(Collectors.toList());
    }

    public static Action map(pl.detectivegame.model.DAO.Action action, List<Item> items){
        List<Long> itemsIds = new ArrayList<>();
        if(items != null) {
            itemsIds = items.stream().map(Item::getItemId).collect(Collectors.toList());
        }
        return  Action.builder()
                .actionId(action.getActionId())
                .description(action.getDescription())
                .image(action.getImage())
                .name(action.getName())
                .time(action.getTime())
                .revealed(false)
                .done(false)
                .caseId(action.getCaseId())
                .successors(mapSuccesors(action.getRevealedActions(), action.getRevealedItems(), action.getRevealedLocations(),itemsIds))
                .location(action.getLocation() != null ? action.getLocation().getName() : null)
                .build();
    }

    public static Action map(pl.detectivegame.model.DAO.Action action){
        return  Action.builder()
                .actionId(action.getActionId())
                .description(action.getDescription())
                .image(action.getImage())
                .name(action.getName())
                .time(action.getTime())
                .revealed(false)
                .done(false)
                .caseId(action.getCaseId())
                .location(action.getLocation() != null ? action.getLocation().getName() : null)
                .build();
    }

     static private List<Successor> mapSuccesors(List<ActionAction> revealedActions, List<ActionItem> revealedItems, List<ActionLocation> revealedLocations, List<Long> items) {
        List<Successor> successors;
        successors = mapActionActionListToSuccesorsList(revealedActions);
        mapActionItemListToSuccesorsList(revealedItems, successors, items);
        mapActionLocationListToSuccesorsList(revealedLocations, successors);
        return successors;
    }

     static private boolean mapActionLocationListToSuccesorsList(List<ActionLocation> revealedLocations, List<Successor> successors) {
        return successors.addAll(revealedLocations.stream().map(actionLocation ->
                Successor.builder().id(actionLocation.getActionLocationIdentity().getLocationId()).type(SuccessorType.LOCATIONS.getValue()).build()).collect(Collectors.toList()));
    }

    static private boolean mapActionItemListToSuccesorsList(List<ActionItem> revealedItems, List<Successor> successors, List<Long> items) {
        return successors.addAll(revealedItems.stream().map(actionItem ->
                Successor.builder().id(actionItem.getActionItemIdentity().getItemId()).type(getItemSuccesorType(items,actionItem).getValue()).build()).collect(Collectors.toList()));
    }

    private static SuccessorType getItemSuccesorType(List<Long> items, ActionItem actionItem) {
        return items.contains(actionItem.getActionItemIdentity().getItemId()) ? SuccessorType.ITEMS : SuccessorType.PEOPLE;
    }

    static private List<Successor> mapActionActionListToSuccesorsList(List<ActionAction> revealedActions) {
        return revealedActions.stream().map(actionAction ->
                Successor.builder().id(actionAction.getActionActionIdentity().getRevealedId()).type(SuccessorType.ACTIONS.getValue()).build()).collect(Collectors.toList());
    }


    static public pl.detectivegame.model.DAO.Action mapWithoutSuccesorsAndId(Action action, Location location){
        return pl.detectivegame.model.DAO.Action.builder()
                .caseId(action.getCaseId())
                .description(action.getDescription())
                .image(action.getImage())
                .location(location)
                .time(action.getTime())
                .name(action.getName())
                .build();
    }

    static public pl.detectivegame.model.DAO.Action mapWithoutSuccesors(Action action, Location location){
        return pl.detectivegame.model.DAO.Action.builder()
                .actionId(action.getActionId())
                .caseId(action.getCaseId())
                .description(action.getDescription())
                .image(action.getImage())
                .location(location)
                .time(action.getTime())
                .name(action.getName())
                .build();
    }
}
