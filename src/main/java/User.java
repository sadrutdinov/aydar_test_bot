import lombok.Data;

import java.util.*;

@Data
public class User {
    private String userName;
    private String birthDate;
    private long chat_id;



    Map<Long, String> database = new HashMap<>();
    Map<String, String> birthDayList = new HashMap<>();

    public void mapDatabase(long chat_id, String userName) {
        if (database.containsKey(chat_id) != true) {
        database.put(chat_id, userName); }
    }

    public void mapBirthDay(String birthDate, String userName) {
        if (birthDayList.containsKey(birthDate) != true) {
            birthDayList.put(birthDate, userName); }

    }

}

