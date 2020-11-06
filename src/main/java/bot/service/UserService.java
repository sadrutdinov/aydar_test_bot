package bot.service;

import bot.entities.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Data
@Component
public class UserService implements IUserService {

    private long chatId;
    private String message;
    private String userName;
    private boolean birthDay = false;
    private boolean isPhoneNumber = false;
    private int day;
    private int month;
    private int year;
    private User iUser;
    private List<Long> chatIdList = new ArrayList<>();

    //maps and various list's are needed for working with multiple users
    Map<Long, String> mapUserName = new HashMap<>();
    Map<Long, Date> mapBirthDay = new HashMap<>();
    Map<Long, String> mapPhoneNumber = new HashMap<>();
    Map<Long, User> userMap = new HashMap<>();


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

    public List<Long> getChatIdList() {
        return chatIdList;
    }


    @Autowired
    public void setIUser(User user) {
        this.iUser = user;
    }

    public void chatIdTracker(Long chatId) {
        if (!chatIdList.contains(chatId)) {
            chatIdList.add(chatId);
        }
    }

    @Override
    public void start(Long chatId, String phoneNumber, String userName) {
        mapUserName.put(chatId, userName);
        mapPhoneNumber.put(chatId, phoneNumber);
    }

    @Override
    public String help(Long chatId, String message, String userName) {
        this.message = message;

        return "Подсказка по командам:" + "\n" +
                "/help - вызов подсказок по командам \n" +
                "/addBirthDay - добавить дату рождения \n" +
                "/info - получить Ваши данные";
    }

    @Override
    public String info(Long chatId, String message, String userName) { //todo
        if (userMap.containsKey(chatId)) {
            return "chatId: " + userMap.get(chatId).getChatId() + "\n" + "userName: " + userMap.get(chatId).getUserName() + "\n" + "birthDay: " + userMap.get(chatId).getBirthDate() +
                    "\n" + "phoneNumber: " + userMap.get(chatId).getPhoneNumber();
        } else
            return "для доступа к данным требуется ввести Дату Рождения"; //todo
    }

    @Override
    public String addBirthDay(String message, Long chatId) {
        if (!birthDay && !chatIdList.contains(chatId)) {
            chatIdTracker(chatId);
            birthDay = true;
            return "введите дату рождения в формате ДД.ММ.ГГГГ";
        } else if (birthDay) {
            try {
                String[] xList = message.split("\\.");
                day = Integer.parseInt(xList[0]);
                month = Integer.parseInt(xList[1]);
                year = Integer.parseInt(xList[2]);
                if (((day > 0) && (day < 32)) && ((month > 0) && (month < 13)) && ((year > 1905) && (year < 2021) && xList[0].length() == 2 && xList[1].length() == 2)) {
                    birthDay = false;

                    mapBirthDay.put(chatId, new Date(year - 1900, month - 1, day, 12, 00, 00));

                    userMap.put(chatId, new User(chatId, mapUserName.get(chatId), mapBirthDay.get(chatId), mapPhoneNumber.get(chatId)));
                    day = 0;
                    month = 0;
                    year = 0;

                    return "Спасибо!";
                } else {
                    day = 0;
                    month = 0;
                    year = 0;

                    return "введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ";
                }
            } catch (Exception e) {
                day = 0;
                month = 0;
                year = 0;

                return "введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ";

            }
        }
        return "/addBirthDay";
    }

    public String echo(Long chatId, String message, String userName) {

        return message;
    }

}
