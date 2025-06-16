package agenduo.controller;

import agenduo.dto.UserDTO;
import agenduo.model.User;
import agenduo.repository.CoupleRepository;
import agenduo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoupleRepository coupleRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody UserDTO request, UriComponentsBuilder uriBuilder) {
        var user = new User(request);
        userRepository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }
}
