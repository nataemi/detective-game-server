package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.*;
import pl.detectivegame.model.Item;
import pl.detectivegame.payload.creation.ItemPayload;
import pl.detectivegame.repository.*;
import pl.detectivegame.util.mapper.ItemMapper;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @Autowired
    ActionItemRepository actionItemRepository;

    public ItemPayload saveItem(ItemPayload itemPayload) {
        Item item = itemPayload.getItem();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(item);
        itemDAO = itemRepository.save(itemDAO);
        modifyActionItem(itemPayload,itemDAO);
        itemPayload.setItem(ItemMapper.mapToItem(itemDAO));
        return itemPayload;
    }

    public void deleteItem(ItemPayload itemPayload) {
        Item item = itemPayload.getItem();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(item);
        deleteActionItem(itemDAO);
        itemRepository.delete(itemDAO);
    }

    public void addActionItemIfIsRevealed(ItemPayload itemPayload, pl.detectivegame.model.DAO.Item itemDAO) {
        if(itemPayload.getItem().isRevealed()){
            long frstActionId = detectiveCaseService.getFrstActionId(itemDAO.getCaseId());
            ActionItem actionItem = ActionItem.builder()
                    .actionItemIdentity(
                            ActionItemIdentity.builder()
                                    .actionId(frstActionId)
                                    .itemId(itemDAO.getItemId())
                                    .build()
                    )
                    .build();
            actionItemRepository.save(actionItem);
        }
    }

    public void modifyActionItem(ItemPayload itemPayload, pl.detectivegame.model.DAO.Item itemDAO) {
        addActionItemIfIsRevealed(itemPayload, itemDAO);
        deleteActionItemIfIsNotRevealed(itemPayload,itemDAO);
    }

    private void deleteActionItemIfIsNotRevealed(ItemPayload itemPayload, pl.detectivegame.model.DAO.Item itemDAO) {
        if (!itemPayload.getItem().isRevealed()) {
            deleteActionItem(itemDAO);
        }
    }

    private void deleteActionItem(pl.detectivegame.model.DAO.Item itemDAO) {
        long frstActionId = detectiveCaseService.getFrstActionId(itemDAO.getCaseId());
        ActionItem actionItem = ActionItem.builder()
                .actionItemIdentity(
                        ActionItemIdentity.builder()
                                .actionId(frstActionId)
                                .itemId(itemDAO.getItemId())
                                .build()
                )
                .build();
        actionItemRepository.delete(actionItem);
    }
}
