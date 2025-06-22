package agenduo.dto.request;

import agenduo.model.Category;
import agenduo.model.Recurrence;
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
        Integer duration,

        Category category,
        Recurrence recurrence
) {
}
