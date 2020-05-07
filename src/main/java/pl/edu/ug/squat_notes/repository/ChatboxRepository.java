package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.squat_notes.domain.Chatbox;

import java.util.List;

public interface ChatboxRepository extends JpaRepository<Chatbox, Long> {
    List<Chatbox> findAllByUserId(Long id);
}
