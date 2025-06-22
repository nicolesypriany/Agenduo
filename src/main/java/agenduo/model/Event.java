package agenduo.model;

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
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private Recurrence recurrence;

    private Boolean isActive;
}
