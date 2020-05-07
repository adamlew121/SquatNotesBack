package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.squat_notes.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLoginAndPassword(String login, String password);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
