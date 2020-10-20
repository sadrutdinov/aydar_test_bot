package bot.repository;

public interface IDatabase {
    void mapperUserName(Long chatId, String userName);
    void mapperBirthDay(Long chatId, String birthDay);
    String getUserInfo(Long chatId);
}
