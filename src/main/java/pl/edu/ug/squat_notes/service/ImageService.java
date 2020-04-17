package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ResponseEntity uploadProfilePhoto(Long idUser, MultipartFile image) throws IOException;

    ResponseEntity<byte[]> getProfilePicture(Long idUser);
}
