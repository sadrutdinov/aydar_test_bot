package bot.MockServer.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class UserDTO {

    private Date birthDay;
    private String chatId;
    private String firstName;
    private UUID id;
    private Boolean isMale;
    private String middleName;
    private String phone;
    private String secondName;

//    public String toString(UserDTO[] list, int i){
//        return list[i].getBirthDay() + " " + list[i].getPhone();
//    }
//
}


