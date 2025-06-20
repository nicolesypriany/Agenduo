package agenduo.controller;

import agenduo.dto.request.CoupleRequest;
import agenduo.dto.response.CoupleResponse;
import agenduo.service.CoupleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("couple")
public class CoupleController {

    @Autowired
    private CoupleService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CoupleRequest request, UriComponentsBuilder uriBuilder) {
        var couple = service.create(request);

        var uri = uriBuilder.path("/couple/{id}").buildAndExpand(couple.id()).toUri();

        return ResponseEntity.created(uri).body(couple);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var couple = service.getById(id);
        return ResponseEntity.ok(new CoupleResponse(couple));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var couples = service.getAll();
        return ResponseEntity.ok(couples);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
