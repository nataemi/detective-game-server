package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.LocationConnection;
import pl.detectivegame.model.DAO.LocationConnectionIdentity;

import java.util.List;

@Repository
public interface LocationConnectionRepository extends JpaRepository<LocationConnection, LocationConnectionIdentity> {

    List<LocationConnection> findAllByLocationConnectionIdentity_FromIdIn(List<Long> collect);

}
