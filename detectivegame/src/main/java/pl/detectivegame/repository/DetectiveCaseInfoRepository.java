package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.DetectiveCaseInfo;


import java.util.Optional;

@Repository
public interface DetectiveCaseInfoRepository extends JpaRepository<DetectiveCaseInfo, Long> {
    Optional<DetectiveCaseInfo> findById(Long caseId);

    long countByCreator(Long userId);
}
