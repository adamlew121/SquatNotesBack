package pl.edu.ug.squat_notes.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageListSerializer extends StdSerializer<List<Message>> {

    public MessageListSerializer() {
        this(null);
    }

    public MessageListSerializer(Class<List<Message>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Message> messages,
                          JsonGenerator generator,
                          SerializerProvider provider) throws IOException, JsonProcessingException {
        List<MessageView> ids = new ArrayList<>();
        for(Message message: messages) {
            ids.add(new MessageView(message.getId(), message.getMessageDate(), message.getText(), message.getSentByUser()));
        }
        generator.writeObject(ids);
    }
}

class MessageView {
    Long id;
    Date messageDate;
    String text;
    boolean sentByUser;

    public MessageView(Long id, Date messageDate, String text, boolean sentByUser) {
        this.id = id;
        this.messageDate = messageDate;
        this.text = text;
        this.sentByUser = sentByUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(boolean sentByUser) {
        this.sentByUser = sentByUser;
    }
}
