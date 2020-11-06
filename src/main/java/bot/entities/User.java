package bot.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Component
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    private Long chatId;
    private String userName;
    private Date birthDate;

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
