package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.ActionItem;

import java.util.List;

@Repository
public interface ActionItemRepository extends JpaRepository<ActionItem,Long> {

    List<ActionItem> findAllByActionItemIdentity_ActionId(Long actionId);
}
