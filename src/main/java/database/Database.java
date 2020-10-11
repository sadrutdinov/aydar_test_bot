package database;

import lombok.extern.slf4j.Slf4j;
import service.IDatabase;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Database implements IDatabase {

    Map<Long, String> database = new HashMap<>();
    Map<String, String> birthDayList = new HashMap<>();

    @Override
    public void mapDatabase(long chat_id, String userName) {
        if (!database.containsKey(chat_id)) {
            database.put(chat_id, userName); }

        for (Map.Entry<Long, String> pair : database.entrySet())
        {
            Long key = pair.getKey();
            String value = pair.getValue();
            log.info("chat_id: " + key + " | userName: " + value);
        }
    }

    @Override
    public void mapBirthDay(String birthDate, String userName) {
        if (!birthDayList.containsKey(birthDate)) {
            birthDayList.put(birthDate, userName); }

        for (Map.Entry<String, String> pair : birthDayList.entrySet())
        {
            String key = pair.getKey();
            String value = pair.getValue();
            log.info("birthDate: " + key + " | userName: " + value);

        }
    }



}
