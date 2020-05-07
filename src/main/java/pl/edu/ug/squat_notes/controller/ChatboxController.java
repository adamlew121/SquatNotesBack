package pl.edu.ug.squat_notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.squat_notes.domain.Chatbox;
import pl.edu.ug.squat_notes.repository.ChatboxRepository;
import pl.edu.ug.squat_notes.service.ChatboxService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ChatboxController {

    private ChatboxService chatboxService;
    private ChatboxRepository chatboxRepository;

    @Autowired
    public ChatboxController(ChatboxRepository chatboxRepository, ChatboxService chatboxService) {
        this.chatboxRepository = chatboxRepository;
        this.chatboxService = chatboxService;
    }

    @GetMapping("/api/chatbox/all")
    List<Chatbox> getChatboxList() {
        return chatboxRepository.findAll();
    }

    @GetMapping("/api/chatbox/{id}")
    List<Chatbox> getChatboxList(@PathVariable Long id) {
        return chatboxRepository.findAllByUserId(id);
    }

    @PostMapping("/api/chatbox")
    ResponseEntity<Chatbox> addChatbox(@RequestBody Chatbox chatbox) {
        return chatboxService.addChatbox(chatbox);
    }

    @PatchMapping("/api/chatbox")
    ResponseEntity<Chatbox> updateChatbox(@RequestBody Chatbox chatbox) {
        return chatboxService.updateChatbox(chatbox);
    }

}
