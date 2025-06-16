package agenduo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "notification")
@Entity
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private Long eventId;
    private LocalDateTime sendAt;
}
