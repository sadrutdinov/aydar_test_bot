package database;

import service.IDatabase;
import java.util.HashMap;
import java.util.Map;

public class Database implements IDatabase {

    Map<Long, String> database = new HashMap<>();
    Map<String, String> birthDayList = new HashMap<>();

    @Override
    public void mapDatabase(long chat_id, String userName) {
        if (!database.containsKey(chat_id)) {
            database.put(chat_id, userName); }


    }

    @Override
    public void mapBirthDay(String birthDate, String userName) {
        if (!birthDayList.containsKey(birthDate)) {
            birthDayList.put(birthDate, userName); }

    }
}
