package agenduo.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record PersonalEventRequest(
        @NotNull(message = "O título é obrigatório")
        String title,
        String description,

        @NotNull(message = "A data é obrigatória")
        LocalDate date,

        String place,

        @NotNull(message = "A hora de início é obrigatória")
        LocalTime startTime,

        @NotNull(message = "A duração é obrigatória")
        Duration duration,

        Long categoryId,
        Long recurrenceId,
        Long ownerId
) {
}
