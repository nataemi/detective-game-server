package pl.detectivegame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import pl.detectivegame.model.DAO.Question;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class NewDetectiveCase {

    @JsonProperty(value = "movement_points")
    int movementPoints;
    Date date;
    @JsonProperty(value = "frst_action_id")
    long frstActionId;
    @JsonProperty(value = "max_score")
    int maxScore;
    @JsonProperty(value = "max_days")
    int maxDays;
    @JsonProperty(value = "mp_per_day")
    int mpPerDay;
    String location;
    List<Action> actions;
    List<Location> locations;
    List<Item> items;
    List<LocationConnectionWithName> paths;
    List<Person> people;
    List<Question> test;
    List<OptimalPath> optimalPaths;
}
