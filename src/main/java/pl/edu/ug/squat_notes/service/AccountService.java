package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import pl.edu.ug.squat_notes.domain.Account;

import java.util.List;


public interface AccountService {
    ResponseEntity<Account> findByLoginAndPassword(String login, String password);

    ResponseEntity<Account> addUser(Account user);

    ResponseEntity<List<Account>> findAll();

    ResponseEntity<Account> findById(Long id);
}
