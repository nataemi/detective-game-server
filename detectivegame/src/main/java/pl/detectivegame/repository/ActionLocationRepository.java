package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.ActionLocation;

import java.util.List;

@Repository
public interface ActionLocationRepository extends JpaRepository<ActionLocation,Long> {

    List<ActionLocation> findAllByActionLocationIdentity_ActionId(Long actionId);
}
