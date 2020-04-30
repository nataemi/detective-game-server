package pl.detectivegame.model.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private Long locationId;

    private Long caseId;

    private String name;

    private String description;

    private String image;

    @JsonIgnore
    @Column(name="is_start")
    private boolean isStart;
}
