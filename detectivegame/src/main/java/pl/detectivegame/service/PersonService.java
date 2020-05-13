package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.ActionItem;
import pl.detectivegame.model.DAO.ActionItemIdentity;
import pl.detectivegame.model.Item;
import pl.detectivegame.model.Person;
import pl.detectivegame.payload.creation.ItemPayload;
import pl.detectivegame.payload.creation.PersonPayload;
import pl.detectivegame.repository.ActionItemRepository;
import pl.detectivegame.repository.ItemRepository;
import pl.detectivegame.util.mapper.ItemMapper;

@Service
public class PersonService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @Autowired
    ActionItemRepository actionItemRepository;

    public PersonPayload savePerson(PersonPayload personPayload) {
        Person person = personPayload.getPerson();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(person);
        itemDAO = itemRepository.save(itemDAO);
        modifyActionItem(personPayload, itemDAO);
        personPayload.setPerson(ItemMapper.mapToPerson(itemDAO));
        return personPayload;
    }

    public void deletePerson(PersonPayload personPayload) {
        Person person = personPayload.getPerson();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(person);
        deleteActionItem(itemDAO);
        itemRepository.delete(itemDAO);
    }

    public void addActionItemIfIsRevealed(PersonPayload personPayload, pl.detectivegame.model.DAO.Item itemDAO) {
        if(personPayload.getPerson().isRevealed()){
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

    public void modifyActionItem(PersonPayload itemPayload, pl.detectivegame.model.DAO.Item itemDAO) {
        addActionItemIfIsRevealed(itemPayload, itemDAO);
        deleteActionItemIfIsNotRevealed(itemPayload,itemDAO);
    }

    private void deleteActionItemIfIsNotRevealed(PersonPayload personPayload, pl.detectivegame.model.DAO.Item itemDAO) {
        if (!personPayload.getPerson().isRevealed()) {
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
