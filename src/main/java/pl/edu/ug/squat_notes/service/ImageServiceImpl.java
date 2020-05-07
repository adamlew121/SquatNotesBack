package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.ug.squat_notes.domain.Account;
import pl.edu.ug.squat_notes.repository.AccountRepository;

import java.io.*;
import java.net.URLConnection;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private AccountRepository accountRepository;

    @Autowired
    public ImageServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity uploadProfilePhoto(Long idUser, MultipartFile image) throws IOException {
        Optional<Account> user = accountRepository.findById(idUser);
        if (user.isPresent()) {
            if (image.getBytes().length >= 1048576) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
            }
            user.get().setProfilePicture(image.getBytes());
            accountRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @Override
    public ResponseEntity<byte[]> getProfilePicture(Long idUser) {
        Optional<Account> user = accountRepository.findById(idUser);
        if (user.isPresent()) {
            byte[] profilePhotoBytes = user.get().getProfilePicture();
            MediaType profilePhotoType = getMediaType(profilePhotoBytes);
            if (profilePhotoType != null) {
                return ResponseEntity.ok()
                        .contentType(profilePhotoType)
                        .body(profilePhotoBytes);
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    private MediaType getMediaType(byte[] imageBytes) {
        if (imageBytes != null) {
            try {
                String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageBytes));
                MediaType imageType = MediaType.parseMediaType(contentType);
                if (MediaType.IMAGE_JPEG.equals(imageType) ||
                        MediaType.IMAGE_PNG.equals(imageType)) {
                    return imageType;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
}
