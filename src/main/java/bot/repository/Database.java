package bot.repository;

import bot.service.IDatabase;
import bot.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component

public class Database implements IDatabase {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    Map<Long, String> mapUserName = new HashMap<>(); // мапа юзернеймов
    Map<Long, Date> mapBirthDay = new HashMap<>(); // мапа дней рождения
    Map<Long, String> mapPhoneNumber = new HashMap<>();  // мапа номеров телефонов
    Map<Long, User> userMap = new HashMap<>();  //мапа юзеров


    public Map<Long, String> getMapPhoneNumber() {
        return mapPhoneNumber;
    }

    public Map<Long, String> getMapUserName() {
        return mapUserName;
    }

    public Map<Long, Date> getMapBirthDay() {
        return mapBirthDay;
    }

    public Map<Long, User> getUserMap() {
        return userMap;
    }


    @Override
    public void mapperUserName(Long chatId, String userName) {
        if (!mapUserName.containsKey(chatId)) {
            mapUserName.put(chatId, userName);
            log.info((ANSI_GREEN + "chatId: " + chatId + ", userName: " + userName + ANSI_RESET));
        }

    }

    @Override
    public void mapperBirthDay(Long chatId, Date birthDay) {
        if (!mapBirthDay.containsKey(chatId)) {
            mapBirthDay.put(chatId, birthDay);
            log.info((ANSI_GREEN + "chatId: " + chatId + ", birthDay: " + birthDay + ANSI_RESET));

        }
    }

    @Override
    public void mapperPhoneNumber(Long chatId, String phoneNumber) {
        if (!mapPhoneNumber.containsKey(chatId)) {
            mapPhoneNumber.put(chatId, phoneNumber);
            log.info((ANSI_GREEN + "chatId: " + chatId + ", phoneNumber: " + phoneNumber + ANSI_RESET));
        }
    }



    @Override
    public void mapperUser(Long chatId, User iUser) {
        if (mapUserName.get(chatId) != null && mapBirthDay.get(chatId) != null && mapPhoneNumber.get(chatId) != null) {
            userMap.put(chatId, iUser);
            log.info(userMap.get(chatId).toString());
        }
    }

    public String getUserInfo(Long chatId) {
        if (mapUserName.containsKey(chatId)) {
            return "chatId: " + chatId + "\n" + "userName: " + mapUserName.get(chatId) + "\n" + "birthDay: " + mapBirthDay.get(chatId) +
                    "\n" + "phoneNumber: " + mapPhoneNumber.get(chatId);
        } else
            return "данные отсутствуют";
    }

}
