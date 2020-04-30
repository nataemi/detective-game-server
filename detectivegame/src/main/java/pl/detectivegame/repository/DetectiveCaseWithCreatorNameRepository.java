package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.DetectiveCaseInfoWithCreator;

import java.util.List;

@Repository
public interface DetectiveCaseWithCreatorNameRepository extends JpaRepository<DetectiveCaseInfoWithCreator, Long> {

    List<DetectiveCaseInfoWithCreator> findByCreator(Long creatorId);

    List<DetectiveCaseInfoWithCreator> findByUserId(Long userId);
}
