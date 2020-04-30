package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.model.Action;

import pl.detectivegame.model.DAO.Location;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class NewDetectiveCase {

    int movementPoints;
    Date date;
    long frstActionId;
    String location;
    List<Action> actions;
    List<Location> locations;
    List<Item> items;
    List<LocationConnectionWithName> paths;
    List<Person> people;
}
