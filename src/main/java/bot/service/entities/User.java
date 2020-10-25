package bot.service.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User implements IUser {
    private String userName;
    private String birthDate;
    private Long chatId;
    private String phoneNumber;




}
