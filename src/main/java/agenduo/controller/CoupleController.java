package agenduo.controller;

import agenduo.dto.CoupleDTO;
import agenduo.model.Couple;
import agenduo.repository.CoupleRepository;
import agenduo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("couple")
public class CoupleController {

    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody CoupleDTO request, UriComponentsBuilder uriBuilder) {
        var userA = userRepository.findById(request.userAId()).get();
        var userB = userRepository.findById(request.userBId()).get();
        var couple = new Couple(userA, userB);

        var uri = uriBuilder.path("/couple/{id}").buildAndExpand(couple.getId()).toUri();

        return ResponseEntity.created(uri).body(couple);
    }
}
