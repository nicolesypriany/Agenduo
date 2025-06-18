package agenduo.controller;

import agenduo.dto.request.CoupleRequest;
import agenduo.dto.response.CoupleResponse;
import agenduo.model.Couple;
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

@RestController
@RequestMapping("couple")
public class CoupleController {

    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody CoupleRequest request, UriComponentsBuilder uriBuilder) {
        var userA = userRepository.findById(request.userAId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        var userB = userRepository.findById(request.userBId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        var couple = new Couple(userA, userB);
        coupleRepository.save(couple);

        var uri = uriBuilder.path("/couple/{id}").buildAndExpand(couple.getId()).toUri();

        return ResponseEntity.created(uri).body(new CoupleResponse(couple));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var couple = coupleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Casal não encontrado"));

        return ResponseEntity.ok(new CoupleResponse(couple));
    }

    @GetMapping
    public ResponseEntity getAll() {
        var couples = coupleRepository.findAll();

        List<CoupleResponse> coupleResponseList = couples.stream()
                .map(CoupleResponse::new)
                .toList();

        return ResponseEntity.ok(coupleResponseList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id, @RequestBody CoupleRequest request) {
        var couple = coupleRepository.getReferenceById(id);
        if (request.userAId() != null) {
            var user = userRepository.getReferenceById(request.userAId());
            couple.updateUserA(user);
        }

        if (request.userBId() != null) {
            var user = userRepository.getReferenceById(request.userBId());
            couple.updateUserB(user);
        }

        return ResponseEntity.ok(new CoupleResponse(couple));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var couple = coupleRepository.getReferenceById(id);
        couple.delete();
        return ResponseEntity.noContent().build();
    }
}
