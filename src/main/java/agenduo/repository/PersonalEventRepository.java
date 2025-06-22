package agenduo.repository;

import agenduo.model.PersonalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalEventRepository extends JpaRepository<PersonalEvent, Long> {
}
