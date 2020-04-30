package pl.detectivegame.util.mapper;

import pl.detectivegame.model.Item;
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
                .build();
    }

    public static Person mapToPerson(pl.detectivegame.model.DAO.Item item){
        return Person.builder()
                .description(item.getDescription())
                .personId(item.getItemId())
                .image(item.getImage())
                .fullName(item.getName())
                .revealed(false)
                .build();
    }
}
