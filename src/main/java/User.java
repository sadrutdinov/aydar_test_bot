import lombok.Data;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@Data
public class User {
    private String userName;
    private String birthDate;
    private long chat_id;

    public void mapDatabase(long chat_id, String userName) {
        Map<Long, String> database = new HashMap<>();
        if (database.containsKey(chat_id) != true) {
        database.put(chat_id, userName); }
    }

    public void mapBirthDay(String birthDate, String userName) {
        Map<String, String> bitrhDayList = new HashMap<>();
        if (bitrhDayList.containsKey(birthDate) != true) {
            bitrhDayList.put(birthDate, userName); }

    }






}


