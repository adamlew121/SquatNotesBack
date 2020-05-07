package pl.edu.ug.squat_notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.squat_notes.domain.Message;
import pl.edu.ug.squat_notes.service.MessageService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/api/message/add")
    ResponseEntity<Message> addMessage(Message message) {
        return messageService.addMessage(message);
    }
}
