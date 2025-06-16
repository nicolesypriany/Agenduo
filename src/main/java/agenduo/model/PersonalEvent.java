package agenduo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PersonalEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
