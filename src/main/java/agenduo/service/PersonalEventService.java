package agenduo.service;

import agenduo.dto.request.PersonalEventRequest;
import agenduo.dto.response.PersonalEventResponse;
import agenduo.model.Category;
import agenduo.model.PersonalEvent;
import agenduo.model.User;
import agenduo.repository.CategoryRepository;
import agenduo.repository.PersonalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonalEventService {

    @Autowired
    private PersonalEventRepository personalEventRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    public PersonalEventResponse create(PersonalEventRequest request) {
        Category category =  categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        User owner = userService.getById(request.ownerId());

        var event = new PersonalEvent(request);
        event.setCategory(category);
        event.setOwner(owner);

        personalEventRepository.save(event);

        return new PersonalEventResponse(event);
    }

    public PersonalEvent getById(Long id) {
        return personalEventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }

    public List<PersonalEventResponse> getAll() {
        var events = personalEventRepository.findAll();

        return events.stream()
                .map(PersonalEventResponse::new)
                .toList();
    }

    public PersonalEventResponse update(Long id, PersonalEventRequest request) {
        var event = getById(id);

        Category category = null;
        if (request.categoryId() != null) {
            category =  categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        }

        event.update(request, category);
        return new PersonalEventResponse(event);
    }

    public void delete(Long id) {
        var event = getById(id);
        personalEventRepository.delete(event);
    }
}
