package pl.detectivegame.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DetectiveCase;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetectiveCaseInfoRepository extends JpaRepository<DetectiveCase, Long> {
    Optional<DetectiveCase> findById(Long caseId);

    Page<DetectiveCase> findByCreator(Long userId, Pageable pageable);

    long countByCreator(Long userId);

    List<DetectiveCase> findByIdIn(List<Long> caseIds);

    List<DetectiveCase> findByIdIn(List<Long> caseIds, Sort sort);
}
