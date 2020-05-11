package pl.detectivegame.model.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long itemId;

    @NotBlank
    private String name;

    private String description;

    private String image;

    @Column(name = "exam_result")
    private String examResult;

    @Column(name = "type_of_item")
    private Character typeOfItem;

    @Column(name = "exam_cost")
    private int examCost;

    @Column(name= "case_id")
    private long caseId;

}
