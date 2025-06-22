package agenduo.service;

import agenduo.dto.request.PersonalEventRequest;
import agenduo.dto.response.PersonalEventResponse;
import agenduo.model.PersonalEvent;
import agenduo.repository.PersonalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonalEventService {

    @Autowired
    private PersonalEventRepository repository;

    public PersonalEventResponse create(PersonalEventRequest request) {
        var event = new PersonalEvent(request);
        repository.save(event);

        return new PersonalEventResponse(event);
    }

    public PersonalEvent getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }

    public List<PersonalEventResponse> getAll() {
        var events = repository.findAll();

        return events.stream()
                .map(PersonalEventResponse::new)
                .toList();
    }

    public PersonalEventResponse update(Long id, PersonalEventRequest request) {
        var event = getById(id);
        event.update(request);
        return new PersonalEventResponse(event);
    }

    public void delete(Long id) {
        var event = getById(id);
        repository.delete(event);
    }
}
