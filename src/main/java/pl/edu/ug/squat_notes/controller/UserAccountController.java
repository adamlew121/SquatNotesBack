package pl.edu.ug.squat_notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.ug.squat_notes.domain.Account;
import pl.edu.ug.squat_notes.service.ImageService;
import pl.edu.ug.squat_notes.service.AccountService;

import java.io.IOException;
import java.util.List;

/**
 * Rest controller for user in account aspects
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserAccountController {

    private AccountService accountService;
    private ImageService imageService;

    @Autowired
    public UserAccountController(AccountService accountService, ImageService imageService) {
        this.accountService = accountService;
        this.imageService = imageService;
    }

    @GetMapping("/api/users")
    ResponseEntity<List<Account>> getUsers() {
        return accountService.findAll();
    }

    @GetMapping("/api/user")
    ResponseEntity<Account> getUser(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        return accountService.findByLoginAndPassword(login, password);
    }

    @GetMapping("/api/user/{id}")
    ResponseEntity<Account> getUserById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping("/api/user")
    ResponseEntity<Account> addUser(@RequestBody Account user) {
        return accountService.addUser(user);
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
