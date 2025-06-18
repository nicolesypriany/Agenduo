package agenduo.dto.response;

import agenduo.model.User;

public record UserResponse(Long id,
                           String name,
                           Long coupleId) {

    public UserResponse(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getCouple().getId()
        );
    }
}
