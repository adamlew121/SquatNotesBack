package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import pl.edu.ug.squat_notes.domain.Chatbox;
import pl.edu.ug.squat_notes.domain.Message;

import java.util.List;

public interface MessageService {
    ResponseEntity<Message> addMessage(Message message);

    ResponseEntity<Message> deleteMessage(Message message);
}
