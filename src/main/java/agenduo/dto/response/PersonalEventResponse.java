package agenduo.dto.response;

import agenduo.model.Category;
import agenduo.model.PersonalEvent;
import agenduo.model.Recurrence;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record PersonalEventResponse(
        Long id,
        String title,
        String description,
        LocalDate date,
        String place,
        LocalTime startTime,
        Integer duration,
        Category category,
        Recurrence recurrence
) {
    public PersonalEventResponse(PersonalEvent personalEvent) {
        this (
                personalEvent.getId(),
                personalEvent.getTitle(),
                personalEvent.getDescription(),
                personalEvent.getDate(),
                personalEvent.getPlace(),
                personalEvent.getStartTime(),
                personalEvent.getDuration(),
                personalEvent.getCategory(),
                personalEvent.getRecurrence()
        );
    }
}
