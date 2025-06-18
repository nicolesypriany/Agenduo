package agenduo.model;

import agenduo.dto.request.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @ManyToOne
    @JoinColumn(name = "couple_id")
    private Couple couple;

    private Boolean isActive;

    public User(UserRequest request) {
        this.name = request.name();
        this.isActive = true;
    }

    public void update(UserRequest request) {
        if(request.name() != null) {
            this.name = request.name();
        }
    }

    public void delete() {
        this.isActive = false;
    }
}
