package agenduo.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull(message = "O nome é obrigatório!")
        String name) {
}