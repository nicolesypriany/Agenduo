package agenduo.controller;

import agenduo.dto.request.PersonalEventRequest;
import agenduo.dto.response.PersonalEventResponse;
import agenduo.service.PersonalEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("personalevent")
public class PersonalEventController {

    @Autowired
    private PersonalEventService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PersonalEventRequest request, UriComponentsBuilder uriBuilder) {
        var event = service.create(request);

        var uri = uriBuilder.path("/personalevent/{id}").buildAndExpand(event.id()).toUri();

        return ResponseEntity.created(uri).body(event);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var event = service.getById(id);
        return ResponseEntity.ok(new PersonalEventResponse(event));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var events = service.getAll();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody PersonalEventRequest request) {
        var event = service.update(id, request);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
