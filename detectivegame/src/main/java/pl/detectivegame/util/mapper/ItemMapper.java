package pl.detectivegame.util.mapper;

import pl.detectivegame.model.Item;
import pl.detectivegame.model.ItemType;
import pl.detectivegame.model.Person;

public class ItemMapper {

    public static Item mapToItem(pl.detectivegame.model.DAO.Item item){
        return Item.builder()
                .description(item.getDescription())
                .examined(false)
                .examineInfo(item.getExamResult())
                .itemId(item.getItemId())
                .image(item.getImage())
                .name(item.getName())
                .revealed(false)
                .examCost(item.getExamCost())
                .caseId(item.getCaseId())
                .build();
    }

    public static Person mapToPerson(pl.detectivegame.model.DAO.Item item){
        return Person.builder()
                .description(item.getDescription())
                .personId(item.getItemId())
                .image(item.getImage())
                .fullname(item.getName())
                .revealed(false)
                .caseId(item.getCaseId())
                .build();
    }

    public static pl.detectivegame.model.DAO.Item mapToItem(Item item){
        return pl.detectivegame.model.DAO.Item.builder()
                .typeOfItem(ItemType.ITEM.getType())
                .name(item.getName())
                .description(item.getDescription())
                .itemId(item.getItemId())
                .image(item.getImage())
                .examResult(item.getExamineInfo())
                .examCost(item.getExamCost())
                .caseId(item.getCaseId())
                .build();
    }

    public static pl.detectivegame.model.DAO.Item mapToItem(Person person){
        return pl.detectivegame.model.DAO.Item.builder()
                .description(person.getDescription())
                .image(person.getImage())
                .itemId(person.getPersonId())
                .name(person.getFullname())
                .typeOfItem(ItemType.PERSON.getType())
                .caseId(person.getCaseId())
                .build();
    }
}
