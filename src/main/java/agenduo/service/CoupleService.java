package agenduo.service;

import agenduo.dto.request.CoupleRequest;
import agenduo.dto.response.CoupleResponse;
import agenduo.model.Couple;
import agenduo.repository.CoupleRepository;
import agenduo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoupleService {

    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public CoupleResponse create(CoupleRequest request) {
        var userA = userService.getById(request.userAId());
        var userB = userService.getById(request.userBId());

        if (userA.getCouple() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário A já está em um casal!");
        }

        if (userB.getCouple() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário B já está em um casal!");
        }

        var couple = new Couple(userA, userB);
        coupleRepository.save(couple);

        userA.setCouple(couple);
        userB.setCouple(couple);
        userRepository.save(userA);
        userRepository.save(userB);

        return new CoupleResponse(couple);
    }

    public Couple getById(Long id) {
        return coupleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Casal não encontrado"));
    }

    public List<CoupleResponse> getAll() {
        var couples = coupleRepository.findAll();

        return couples.stream()
                .map(CoupleResponse::new)
                .toList();
    }

    public void delete(Long id) {
        var couple = getById(id);
        couple.delete();

        var userA = couple.getUserA();
        var userB = couple.getUserB();

        userA.setCouple(null);
        userB.setCouple(null);
    }
}
