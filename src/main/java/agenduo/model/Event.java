package agenduo.model;

import agenduo.dto.request.PersonalEventRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "base_events")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate date;
    private String place;
    private LocalTime startTime;
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private Recurrence recurrence;

    private Boolean isActive;

    public void update(PersonalEventRequest request, Category category) {
        if (request.title() != null) setTitle(request.title());
        if (request.description() != null) setDescription(request.description());
        if (request.date() != null) setDate(request.date());
        if (request.place() != null) setPlace(request.place());
        if (request.startTime() != null) setStartTime(request.startTime());
        if (request.duration() != null) setDuration(request.duration());
        if (category != null) setCategory(category);
    }
}
