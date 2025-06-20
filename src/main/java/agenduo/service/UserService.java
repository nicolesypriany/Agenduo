package agenduo.service;

import agenduo.dto.request.UserRequest;
import agenduo.dto.response.UserResponse;
import agenduo.model.User;
import agenduo.repository.CoupleRepository;
import agenduo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoupleRepository coupleRepository;

    public UserResponse create(UserRequest request) {
        var user = new User(request);
        userRepository.save(user);
        return new UserResponse(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public List<UserResponse> getAll() {
        var users = userRepository.findAll();

        return users.stream()
                .map(UserResponse::new)
                .toList();
    }

    public UserResponse update(Long id, UserRequest request) {
        var user = getById(id);
        user.update(request);
        return new UserResponse(user);
    }

    public void delete(Long id) {
        var user = getById(id);
        user.delete();
    }
}
