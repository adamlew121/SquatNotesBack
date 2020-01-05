package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.squat_notes.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
