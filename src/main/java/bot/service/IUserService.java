package bot.service;

import bot.service.entities.IUser;

import java.util.List;

public interface IUserService {
    String start(Long chatId, String message, String userName);
    String help(Long chatId, String message, String userName);
    String echo(Long chatId, String message, String userName);
    String info(Long chatId, String message, String userName);
    String addBirthDay(String message, Long chatId);
    void chatIdTracker(Long chatId);
    String addPhoneNumber(Long chatId, String message);

    long getChatId();
    void setChatId(long chatId);
    String getMessage();
    void setMessage(String outMessage);
    List<Long> getChatIdList();
    List<Long> getPhoneNumberList();
}

