package bot.service;

import bot.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUserService {
    void start(Long chatId, String phoneNumber, String userName);

    String help();

    String echo(String message);

    String info();

    String addBirthDay(String message, Long chatId);

    void chatIdTracker(Long chatId);

    void setChatId(long chatId);

    void setMessage(String outMessage);

    List<Long> getChatIdList();

    Map<Long, String> getMapPhoneNumber();

    Map<Long, String> getMapUserName();

    Map<Long, Date> getMapBirthDay();


}

