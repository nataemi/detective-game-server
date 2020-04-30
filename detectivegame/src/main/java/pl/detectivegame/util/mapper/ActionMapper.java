package pl.detectivegame.util.mapper;

import pl.detectivegame.model.Action;
import pl.detectivegame.model.DAO.ActionAction;
import pl.detectivegame.model.DAO.ActionItem;
import pl.detectivegame.model.DAO.ActionLocation;
import pl.detectivegame.model.Item;
import pl.detectivegame.model.Succesor;
import pl.detectivegame.model.SuccesorType;

import java.util.List;
import java.util.stream.Collectors;

public class ActionMapper {

    public static List<Action> map(List<pl.detectivegame.model.DAO.Action> actions, List<Item> items){

        List<Long> itemsIds = items.stream().map(Item::getItemId).collect(Collectors.toList());
        return actions.stream().map(action -> Action.builder()
                .actionId(action.getActionId())
                .bgnDate(action.getBgnDate())
                .description(action.getDescription())
                .image(action.getImage())
                .name(action.getName())
                .time(action.getTime())
                .succesors(mapSuccesors(action.getRevealedActions(), action.getRevealedItems(), action.getRevealedLocations(),itemsIds))
                .build()).collect(Collectors.toList());
    }

     static private List<Succesor> mapSuccesors(List<ActionAction> revealedActions, List<ActionItem> revealedItems, List<ActionLocation> revealedLocations, List<Long> items) {
        List<Succesor> succesors;
        succesors = mapActionActionListToSuccesorsList(revealedActions);
        mapActionItemListToSuccesorsList(revealedItems, succesors, items);
        mapActionLocationListToSuccesorsList(revealedLocations, succesors);
        return succesors;
    }

     static private boolean mapActionLocationListToSuccesorsList(List<ActionLocation> revealedLocations, List<Succesor> succesors) {
        return succesors.addAll(revealedLocations.stream().map(actionLocation ->
                Succesor.builder().id(actionLocation.getActionLocationIdentity().getLocationId()).type(SuccesorType.LOCATIONS).build()).collect(Collectors.toList()));
    }

    static private boolean mapActionItemListToSuccesorsList(List<ActionItem> revealedItems, List<Succesor> succesors, List<Long> items) {
        return succesors.addAll(revealedItems.stream().map(actionItem ->
                Succesor.builder().id(actionItem.getActionActionIdentity().getItemId()).type(getItemSuccesorType(items,actionItem)).build()).collect(Collectors.toList()));
    }

    private static SuccesorType getItemSuccesorType(List<Long> items, ActionItem actionItem) {
        return items.contains(actionItem.getActionActionIdentity().getItemId()) ? SuccesorType.ITEMS : SuccesorType.PEOPLE;
    }

    static private List<Succesor> mapActionActionListToSuccesorsList(List<ActionAction> revealedActions) {
        return revealedActions.stream().map(actionAction ->
                Succesor.builder().id(actionAction.getActionActionIdentity().getRevealedId()).type(SuccesorType.ACTIONS).build()).collect(Collectors.toList());
    }
}
