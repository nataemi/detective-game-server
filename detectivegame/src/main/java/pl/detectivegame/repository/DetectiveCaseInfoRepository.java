package pl.detectivegame.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DetectiveCaseInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetectiveCaseInfoRepository extends JpaRepository<DetectiveCaseInfo, Long> {
    Optional<DetectiveCaseInfo> findById(Long caseId);

    Page<DetectiveCaseInfo> findByCreator(Long userId, Pageable pageable);

    long countByCreator(Long userId);

    List<DetectiveCaseInfo> findByIdIn(List<Long> caseIds);

    List<DetectiveCaseInfo> findByIdIn(List<Long> caseIds, Sort sort);
}
