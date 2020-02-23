package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.ug.squat_notes.domain.User;

import java.io.IOException;
import java.sql.Blob;

public interface ImageService {
    ResponseEntity<User> uploadProfilePhoto(Long idUser, MultipartFile image) throws IOException;

    ResponseEntity<byte[]> getProfilePicture(Long idUser);
}
