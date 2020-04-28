package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.Save;

import java.util.Optional;


@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {

    Optional<Save> findFirstByPlayerAndCaseIdOrderByLastModifiedDesc(Long player, Long case_Id);


}
