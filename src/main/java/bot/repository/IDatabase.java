package bot.repository;

import bot.service.entities.IUser;
import bot.service.entities.User;

import java.util.Map;

public interface IDatabase {
    void mapperUserName(Long chatId, String userName);
    void mapperBirthDay(Long chatId, String birthDay);
    String getUserInfo(Long chatId);
    void mapperPhoneNumber(Long chatId, String phoneNumber);
    void mapperUser (Long chatId, IUser user);
    Map<Long, String> getMapPhoneNumber();
    Map<Long, User> getUserMap();
    Map<Long, String> getMapUserName();
    Map<Long, String> getMapBirthDay();
}
