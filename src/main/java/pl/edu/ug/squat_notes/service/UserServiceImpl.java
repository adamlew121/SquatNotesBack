package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.User;
import pl.edu.ug.squat_notes.repository.UserRepository;
import pl.edu.ug.squat_notes.util.Utils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> findByLoginAndPassword(String login, String password) {
        Optional<User> userFromDB = userRepository.findByLoginAndPassword(login, password);
        if (userFromDB.isPresent()) {
            return ResponseEntity.ok(userFromDB.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Override
    public ResponseEntity<User> addUser(User user) {
        Boolean isLoginBusy = userRepository.existsByLogin(user.getLogin());
        Boolean isEmailBusy = userRepository.existsByEmail(user.getEmail());
        if (isLoginBusy || isEmailBusy) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            if (Utils.isValid(user)) {
                return ResponseEntity.ok(userRepository.save(user));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
    }
}
