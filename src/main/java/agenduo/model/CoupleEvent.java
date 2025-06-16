package agenduo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CoupleEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "couple_id")
    private Couple couple;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private Boolean confirmedByUserA;
    private Boolean confirmedByUserB;
}
