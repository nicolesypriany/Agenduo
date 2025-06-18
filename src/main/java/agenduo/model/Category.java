package agenduo.model;

import agenduo.dto.request.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Category(CategoryRequest request) {
        this.name = request.name();
    }

    public void update(CategoryRequest request) {
        if(request.name() != null) {
            this.name = request.name();
        }
    }
}
