package bot.entities;

import bot.service.IUser;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Data
@Component
public class User implements IUser {
    private String userName; // возможно лишнее
    private Date birthDate;
    private Long chatId;
    private String phoneNumber;



 // todo убрать имплементацию


}
