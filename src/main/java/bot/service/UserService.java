package bot.service;

import bot.repository.IDatabase;
import bot.service.entities.IUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class UserService implements IUserService {

    private long chatId;
    private String message;
    private String userName;
    private boolean birthDay = false;
    private int day;
    private int month;
    private int year;
    private IUser iUser;
    private IDatabase iDatabase;
    List<Long> chatIdList = new ArrayList<>();

    @Autowired
    public void setIDatabase(IDatabase iDatabase) {
        this.iDatabase = iDatabase;
    }

    @Autowired
    public void setIUser(IUser iUser) {
        this.iUser = iUser;
    }

    public void chatIdTracker (Long chatId) {
        if (!chatIdList.contains(chatId)) {
        chatIdList.add(chatId);
        }
    }

    @Override
    public String start(Long chatId, String message, String userName) {
    iUser.setChatId(chatId);
    iUser.setUserName(userName);
    this.message = message;
        iDatabase.mapperUserName(iUser.getChatId(), iUser.getUserName());
        return "Привет, я классный бот, который умеет запоминать день рождения";
    }

    public String help(String message) {
        this.message = message;
        return "Подсказка по командам:" +"\n" +
                "/help - вызов подсказок по командам \n" +
                "/addBirthDay - добавить дату рождения \n" +
                "/info - получить Ваши данные";
    }

    @Override
    public String info(Long chatId) {
        return iDatabase.getUserInfo(chatId);
    }

    @Override
    public String addBirthDay(String message, Long chatId) {
        if (!birthDay && !chatIdList.contains(chatId) ) {
                chatIdTracker(chatId);
                birthDay = true;
                return "введите дату рождения в формате ДД.ММ.ГГГГ";

        }
        else if (birthDay) {
            try {
                String[] xList = message.split("\\D");
                day = Integer.parseInt(xList[0]);
                month = Integer.parseInt(xList[1]);
                year = Integer.parseInt(xList[2]);
                if (((day > 0) && (day <32)) && ((month > 0) && (month <13)) && ((year > 1900) && (year < 2021))) {
                    iUser.setBirthDate(day +"."+ month +"."+ year);
                    birthDay = false;
                    iDatabase.mapperBirthDay(iUser.getChatId(), iUser.getBirthDate());
                    day = 0;
                    month = 0;
                    year = 0;

                    return "Спасибо!";
                }
                else {
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
        else return "/addBirthDay";
    }

    public String echo(String message) {
        return message;
    }
}
