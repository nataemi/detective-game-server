package pl.detectivegame.util.mapper;

import pl.detectivegame.model.DetectiveCaseInfoWithCreator;

import java.util.List;
import java.util.stream.Collectors;

public class DetectiveCaseInfoWithCreatorMapper {

    private static DetectiveCaseInfoWithCreator map(pl.detectivegame.model.DAO.DetectiveCaseInfoWithCreator dao) {
        return DetectiveCaseInfoWithCreator.builder()
                .description(dao.getDescription())
                .id(dao.getId())
                .image(dao.getImage())
                .maxDays(dao.getMaxDays())
                .movementPoints(dao.getMaxDays() * dao.getMpPerDay())
                .mpPerDay(dao.getMpPerDay())
                .name(dao.getName())
                .ready(dao.isReady())
                .score(dao.getScore())
                .build();
    }

    public static List<DetectiveCaseInfoWithCreator> map( List<pl.detectivegame.model.DAO.DetectiveCaseInfoWithCreator> daoList){
        return daoList.stream().map(DetectiveCaseInfoWithCreatorMapper::map).collect(Collectors.toList());
    }
}
