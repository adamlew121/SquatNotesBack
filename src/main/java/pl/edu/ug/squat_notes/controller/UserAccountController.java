package pl.edu.ug.squat_notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.ug.squat_notes.domain.User;
import pl.edu.ug.squat_notes.service.ImageService;
import pl.edu.ug.squat_notes.service.UserService;

import java.io.IOException;
import java.util.List;

/**
 * Rest controller for user in account aspects
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserAccountController {

    private UserService userService;
    private ImageService imageService;

    @Autowired
    public UserAccountController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/api/users")
    ResponseEntity<List<User>> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/api/user")
    ResponseEntity<User> getUser(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        return userService.findByLoginAndPassword(login, password);
    }

    @PostMapping("/api/user")
    ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/api/user/{id}/profile-photo")
    ResponseEntity uploadProfilePhoto(@PathVariable Long id, @RequestBody MultipartFile image) throws IOException {
        return imageService.uploadProfilePhoto(id, image);
    }

    @GetMapping("/api/user/{id}/profile-photo")
    ResponseEntity<byte[]> getProfilePicture(@PathVariable Long id) {
        return imageService.getProfilePicture(id);
    }
}
