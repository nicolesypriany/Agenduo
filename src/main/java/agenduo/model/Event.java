package agenduo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "base_events")
@Entity
public abstract class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private Recurrence recurrence;
}
