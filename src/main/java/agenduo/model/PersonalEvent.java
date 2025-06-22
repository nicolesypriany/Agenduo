package agenduo.model;

import agenduo.dto.request.PersonalEventRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonalEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public PersonalEvent() {}

    public PersonalEvent(PersonalEventRequest request) {
        this.setTitle(request.title());
        this.setDescription(request.description());
        this.setDate(request.date());
        this.setPlace(request.place());
        this.setStartTime(request.startTime());
        this.setDuration(request.duration());
        this.setIsActive(true); // ou request.isActive() se vier do DTO
    }
}
