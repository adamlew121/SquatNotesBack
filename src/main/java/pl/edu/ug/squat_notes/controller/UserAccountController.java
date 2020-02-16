package pl.edu.ug.squat_notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.squat_notes.domain.User;
import pl.edu.ug.squat_notes.repository.UserRepository;
import pl.edu.ug.squat_notes.service.UserService;

/**
 * Rest controller for user in account aspects
 */
@RestController
public class UserAccountController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserAccountController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/user")
    ResponseEntity<User> getUser(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        return userService.findByLoginAndPassword(login, password);
    }

    @PostMapping("/api/user")
    ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
