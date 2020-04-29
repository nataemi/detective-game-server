package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DetectiveCaseInfoWithCreatorName;

@Repository
public interface DetectiveCaseWithCreatorNameRepository extends JpaRepository<DetectiveCaseInfoWithCreatorName, Long> {
}
