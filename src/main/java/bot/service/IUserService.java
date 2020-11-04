package bot.service;

import java.util.List;

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
}

