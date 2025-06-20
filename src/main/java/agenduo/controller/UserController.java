package agenduo.controller;

import agenduo.dto.request.UserRequest;
import agenduo.dto.response.UserResponse;
import agenduo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserRequest request, UriComponentsBuilder uriBuilder) {
        var user = service.create(request);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var user = service.getById(id);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var users = service.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UserRequest request) {
        var user = service.update(id, request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
