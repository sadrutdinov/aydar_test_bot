package bot.service;

import bot.entities.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private IDatabase iDatabase;
    private List<Long> chatIdList = new ArrayList<>();

    public List<Long> getChatIdList() {
        return chatIdList;
    }

    @Autowired
    public void setIDatabase(IDatabase iDatabase) {
        this.iDatabase = iDatabase;
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
        iUser.setChatId(chatId);
        iUser.setUserName(userName);
        iUser.setPhoneNumber(phoneNumber);
        iDatabase.mapperUserName(iUser.getChatId(), iUser.getUserName());
        iDatabase.mapperPhoneNumber(iUser.getChatId(), iUser.getPhoneNumber());
    }

    @Override
    public String help(Long chatId, String message, String userName) {
        this.message = message;
        iUser.setChatId(chatId);
        iUser.setUserName(userName);
        iDatabase.mapperUserName(iUser.getChatId(), iUser.getUserName());
        return "Подсказка по командам:" + "\n" +
                "/help - вызов подсказок по командам \n" +
                "/addBirthDay - добавить дату рождения \n" +
                "/info - получить Ваши данные";
    }

    @Override
    public String info(Long chatId, String message, String userName) {
        iUser.setChatId(chatId);
        iUser.setUserName(userName);
        iDatabase.mapperUserName(iUser.getChatId(), iUser.getUserName());
        return iDatabase.getUserInfo(chatId);
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
                    iUser.setBirthDate(new Date(year - 1900, month - 1, day));
                    birthDay = false;
                    iDatabase.mapperBirthDay(iUser.getChatId(), iUser.getBirthDate());
                    iDatabase.mapperUser(chatId, iUser);
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
        iUser.setChatId(chatId);
        iUser.setUserName(userName);
        iDatabase.mapperUserName(iUser.getChatId(), iUser.getUserName());
        return message;
    }

}
