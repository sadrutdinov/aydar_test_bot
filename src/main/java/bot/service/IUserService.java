package bot.service;

public interface IUserService {
    String start(Long chatId, String message, String userName);
    String help(Long chatId, String message);
    String echo(Long chatId, String message);


    long getChatId();
    void setChatId(long chatId);

    String getMessage();
    void setMessage(String outMessage);


}

