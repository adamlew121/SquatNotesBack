package pl.edu.ug.squat_notes.domain;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date messageDate;
    @Length(min = 2, message = "The field must be at least 2 characters")
    private String text;
    private Boolean sentByUser;
    @ManyToOne
    @JoinColumn(name = "chatbox_id")
    private Chatbox chatbox;

    public Message() {    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageDate=" + messageDate +
                ", text='" + text + '\'' +
                ", sentByUser=" + sentByUser +
                ", chatBox=" + chatbox +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(Boolean sentByUser) {
        this.sentByUser = sentByUser;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chatbox getChatBox() {
        return chatbox;
    }

    public void setChatBox(Chatbox chatBox) {
        this.chatbox = chatBox;
    }
}
