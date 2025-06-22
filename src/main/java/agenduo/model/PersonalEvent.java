package agenduo.model;

import agenduo.dto.request.PersonalEventRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class PersonalEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public PersonalEvent() {}

    public PersonalEvent(PersonalEventRequest request, Category category, Recurrence recurrence, User owner) {
        this.setTitle(request.title());
        this.setDescription(request.description());
        this.setDate(request.date());
        this.setPlace(request.place());
        this.setStartTime(request.startTime());
        this.setDuration(request.duration());
        this.setCategory(category);
        this.setRecurrence(recurrence);
        this.setIsActive(true); // ou request.isActive() se vier do DTO
    }

    public PersonalEvent(PersonalEventRequest request) {
        this.setTitle(request.title());
        this.setDescription(request.description());
        this.setDate(request.date());
        this.setPlace(request.place());
        this.setStartTime(request.startTime());
        this.setDuration(request.duration());
        this.setIsActive(true); // ou request.isActive() se vier do DTO
    }

    public void update(PersonalEventRequest request) {
        if(request.title() != null) {
            this.setTitle(request.title());
        }
        if(request.description() != null) {
            this.setDescription(request.description());
        }
        if(request.date() != null) {
            this.setDate(request.date());
        }
        if(request.place() != null) {
            this.setPlace(request.place());
        }
        if(request.startTime() != null) {
            this.setStartTime(request.startTime());
        }
        if(request.duration() != null) {
            this.setDuration(request.duration());
        }
        if(request.category() != null) {
            this.setCategory(request.category());
        }
        if(request.recurrence() != null) {
            this.setRecurrence(request.recurrence());
        }
    }
}
