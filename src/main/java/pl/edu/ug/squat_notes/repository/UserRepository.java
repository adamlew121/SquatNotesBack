package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.squat_notes.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginAndPassword(String login, String password);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
