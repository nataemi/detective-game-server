package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.Answer;
import pl.detectivegame.model.DAO.Question;
import pl.detectivegame.model.DAO.QuestionWithoutAnswers;
import pl.detectivegame.model.Item;
import pl.detectivegame.payload.creation.ItemPayload;
import pl.detectivegame.payload.creation.QuestionPayload;
import pl.detectivegame.repository.AnswerRepository;
import pl.detectivegame.repository.ItemRepository;
import pl.detectivegame.repository.QuestionRepository;
import pl.detectivegame.repository.QuestionWithoutAnswersRepository;
import pl.detectivegame.util.mapper.ItemMapper;
import pl.detectivegame.util.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ItemPayload saveItem(ItemPayload itemPayload) {
        Item item = itemPayload.getItem();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(item);
        itemDAO = itemRepository.save(itemDAO);
        itemPayload.setItem(ItemMapper.mapToItem(itemDAO));
        return itemPayload;
    }

    public void deleteItem(ItemPayload itemPayload) {
        Item item = itemPayload.getItem();
        pl.detectivegame.model.DAO.Item itemDAO = ItemMapper.mapToItem(item);
        itemRepository.delete(itemDAO);
    }
}
