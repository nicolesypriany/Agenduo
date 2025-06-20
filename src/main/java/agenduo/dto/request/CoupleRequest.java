package agenduo.dto.request;

import jakarta.validation.constraints.NotNull;

public record CoupleRequest(
        @NotNull(message = "User A deve ser preenchido!")
        Long userAId,

        @NotNull(message = "User B deve ser preenchido!")
        Long userBId) {
}
