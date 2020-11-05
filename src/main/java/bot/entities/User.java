package bot.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component

public class User {
    private String userName;
    private Date birthDate;
    private Long chatId;
    private String phoneNumber;

    public User(Long chatId, String userName, Date birthDate, String phoneNumber) {
        this.chatId = chatId;
        this.userName = userName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }
}
