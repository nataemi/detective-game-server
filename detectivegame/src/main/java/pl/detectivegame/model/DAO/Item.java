package pl.detectivegame.model.DAO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@NamedNativeQuery(name = "Item.findAllInCase",
        query = "SELECT * FROM ITEM i WHERE i.item_id IN (SELECT item_id FROM ACTION_ITEM WHERE ACTION_ID IN (SELECT ACTION_ID FROM ACTION WHERE CASE_ID = :detectiveCaseId));",
        resultClass = Item.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long itemId;

    @NotBlank
    private String name;

    private String description;

    private String image;

    private String examResult;

    @Column(name = "type_of_item")
    private Character typeOfItem;

}