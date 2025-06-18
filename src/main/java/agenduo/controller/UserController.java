package agenduo.controller;

import agenduo.dto.request.UserRequest;
import agenduo.dto.response.UserResponse;
import agenduo.model.User;
import agenduo.repository.CoupleRepository;
import agenduo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoupleRepository coupleRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody UserRequest request, UriComponentsBuilder uriBuilder) {
        var user = new User(request);
        userRepository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var users = userRepository.findAll();

        List<UserResponse> userResponseList = users.stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok(userResponseList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserRequest request) {
        var user = userRepository.getReferenceById(id);
        user.update(request);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);
        user.delete();
        return ResponseEntity.noContent().build();
    }
}
