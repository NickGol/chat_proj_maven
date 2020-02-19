package chat.ItemsMetaDataPackage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Component
@Scope(value = "prototype")
@Entity
@Table
public class MessageItemData {

    private UUID uuidMessage;
    @Id
    private UUID uuidMessageItem = UUID.randomUUID();
    private String timeOfMessage;
    private String value;
    private String chatName;
    private String sender;

    public void setUuidMessage(UUID uuidMessage) {
        this.uuidMessage = uuidMessage;
    }

    public void setTimeOfMessage(String timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
