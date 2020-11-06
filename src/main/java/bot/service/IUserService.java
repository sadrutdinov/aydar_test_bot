package bot.service;

import bot.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUserService {
    void start(Long chatId, String phoneNumber, String userName);

    String help(Long chatId, String message, String userName);

    String echo(Long chatId, String message, String userName);

    String info(Long chatId, String message, String userName);

    String addBirthDay(String message, Long chatId);

    void chatIdTracker(Long chatId);

    void setChatId(long chatId);

    void setMessage(String outMessage);

    List<Long> getChatIdList();

    public Map<Long, String> getMapPhoneNumber();

    public Map<Long, String> getMapUserName();

    public Map<Long, Date> getMapBirthDay();
    public Map<Long, User> getUserMap();

}

