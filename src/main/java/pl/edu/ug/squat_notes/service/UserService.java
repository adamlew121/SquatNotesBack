package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import pl.edu.ug.squat_notes.domain.User;


public interface UserService {
    ResponseEntity<User> findByLoginAndPassword(String login, String password);

    ResponseEntity<User> addUser(User user);

}
