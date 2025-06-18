package agenduo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "couples")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Couple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_a_id")
    private User userA;

    @OneToOne
    @JoinColumn(name = "user_b_id")
    private User userB;

    private Boolean isActive;

    public Couple(User userA, User userB) {
        this.userA = userA;
        this.userB = userB;
        this.isActive = true;
    }

    public void delete() {
        this.isActive = false;
    }

    public void updateUserA(User user) {
        this.userA = user;
    }

    public void updateUserB(User user) {
        this.userB = user;
    }
}
