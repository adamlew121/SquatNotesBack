package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.Message;
import pl.edu.ug.squat_notes.repository.MessageRepository;
import pl.edu.ug.squat_notes.util.Utils;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @Override
    public ResponseEntity<Message> addMessage(Message message) {
        if (Utils.isValid(message)) {
            return ResponseEntity.ok(messageRepository.save(message));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Message> deleteMessage(Message message) {
        if (Utils.isValid(message)) {
            messageRepository.delete(message);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
