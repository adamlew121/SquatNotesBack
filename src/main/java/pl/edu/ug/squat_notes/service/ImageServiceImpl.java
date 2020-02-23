package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.ug.squat_notes.domain.User;
import pl.edu.ug.squat_notes.repository.UserRepository;

import java.io.*;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private UserRepository userRepository;

    @Autowired
    public ImageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> uploadProfilePhoto(Long idUser, MultipartFile image) throws IOException {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            user.get().setProfilePicture(image.getBytes());
            return ResponseEntity.ok(userRepository.save(user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @Override
    public ResponseEntity<byte[]> getProfilePicture(Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            byte[] profilePhotoBytes = user.get().getProfilePicture();
            if (profilePhotoBytes != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(profilePhotoBytes);
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
