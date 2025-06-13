package agenduo.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CoupleEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "couple_id")
    private Couple couple;

    private Boolean confirmedByUserA;
    private Boolean confirmedByUserB;
}
