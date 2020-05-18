package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.Chatbox;
import pl.edu.ug.squat_notes.repository.AccountRepository;
import pl.edu.ug.squat_notes.repository.ChatboxRepository;
import pl.edu.ug.squat_notes.util.Utils;

import java.util.List;
import java.util.Optional;

@Service
public class ChatboxServiceImpl implements ChatboxService {

    private ChatboxRepository chatBoxRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ChatboxServiceImpl(ChatboxRepository chatboxRepository, AccountRepository accountRepository) {
        this.chatBoxRepository = chatboxRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<List<Chatbox>> findAllByUserId(Long id) {
        if (!accountRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Chatbox> chatBoxList = chatBoxRepository.findAllByUserId(id);
        if (chatBoxList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.ok(chatBoxList);
        }
    }

    @Override
    public ResponseEntity<Chatbox> addChatbox(Chatbox chatbox) {
        if (Utils.isValid(chatbox)) {
            return ResponseEntity.ok(chatBoxRepository.save(chatbox));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Chatbox> updateChatbox(Chatbox chatbox) {
        Optional<Chatbox> chatboxFromDB = chatBoxRepository.findById(chatbox.getId());
        if (chatboxFromDB.isPresent() && chatboxFromDB.get().getUser().getId().equals(chatbox.getUser().getId()) && Utils.isValid(chatbox)) {
            return ResponseEntity.ok(chatBoxRepository.save(chatbox));
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }


}
