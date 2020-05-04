package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.Item;
import pl.detectivegame.model.Person;
import pl.detectivegame.payload.creation.ItemPayload;
import pl.detectivegame.payload.creation.PersonPayload;
import pl.detectivegame.repository.ItemRepository;
import pl.detectivegame.util.mapper.ItemMapper;

@Service
public class PersonService {

    @Autowired
    ItemRepository itemRepository;

    public PersonPayload savePerson(PersonPayload personPayload) {
        Person person = personPayload.getPerson();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(person);
        itemDAO = itemRepository.save(itemDAO);
        personPayload.setPerson(ItemMapper.mapToPerson(itemDAO));
        return personPayload;
    }

    public void deletePerson(PersonPayload personPayload) {
        Person person = personPayload.getPerson();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(person);
        itemRepository.delete(itemDAO);
    }
}
