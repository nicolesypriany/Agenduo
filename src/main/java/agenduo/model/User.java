package agenduo.model;

import agenduo.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "couple_id")
    private Couple couple;

    private Boolean isActive;

    public User(UserDTO request) {
        this.name = request.name();
        this.isActive = true;
    }

    public void delete() {
        this.isActive = false;
    }
}
