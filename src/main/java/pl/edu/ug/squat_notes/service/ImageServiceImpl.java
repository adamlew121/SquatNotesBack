//package pl.edu.ug.squat_notes.service;
//
//import org.hibernate.Hibernate;
//import org.hibernate.engine.jdbc.BlobProxy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import pl.edu.ug.squat_notes.domain.User;
//import pl.edu.ug.squat_notes.repository.UserRepository;
//
//import java.io.IOException;
//import java.sql.Blob;
//import java.util.Optional;
//
//@Service
//public class ImageServiceImpl implements ImageService {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public ImageServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public ResponseEntity<User> uploadProfilePhoto(Long idUser, MultipartFile image) throws IOException {
//        Optional<User> user = userRepository.findById(idUser);
//        if(user.isPresent()) {
//            user.get().setProfilePicture(BlobProxy.generateProxy(image));
//            return ResponseEntity.ok(userRepository.save(user.get()));
//        } else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        }
//    }
//}
