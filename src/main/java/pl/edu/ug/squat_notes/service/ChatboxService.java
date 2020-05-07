package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import pl.edu.ug.squat_notes.domain.Chatbox;

import java.util.List;

public interface ChatboxService {
    ResponseEntity<List<Chatbox>> findAllByUserId(Long id);

    ResponseEntity<Chatbox> addChatbox(Chatbox chatbox);

    ResponseEntity<Chatbox> updateChatbox(Chatbox chatbox);
}
