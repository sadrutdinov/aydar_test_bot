package bot.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class Database implements IDatabase{

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    Map<Long, String> mapUserName = new HashMap<>();
    Map<Long, String> mapBirthDay = new HashMap<>();
    Map<Long, String> mapPhoneNumber = new HashMap<>();

    @Override
    public void mapperUserName(Long chatId, String userName) {
        if (!mapUserName.containsKey(chatId)) {
            mapUserName.put(chatId, userName);
            log.info((ANSI_GREEN + "chatId: "+ chatId + ", userName: " + userName + ANSI_RESET));
        }

    }

    @Override
    public void mapperBirthDay(Long chatId, String birthDay) {
        if (!mapBirthDay.containsKey(chatId)) {
            mapBirthDay.put(chatId, birthDay);
            log.info((ANSI_GREEN + "chatId: "+ chatId + ", birthDay: " + birthDay  + ANSI_RESET));

        }
    }
    public String getUserInfo(Long chatId) {
        if (mapUserName.containsKey(chatId)) {
            return "chatId: " + chatId + "\n" + "userName: " + mapUserName.get(chatId)  + "\n" + "birthDay: " + mapBirthDay.get(chatId) +
            "\n" + "phoneNumber: " + mapPhoneNumber.get(chatId);
        } else
            return "данные отсутствуют";
    }

    @Override
    public void mapperPhoneNumber(Long chatId, String phoneNumber) {
        if (!mapPhoneNumber.containsKey(chatId)) {
            mapPhoneNumber.put(chatId, phoneNumber);
            log.info((ANSI_GREEN + "chatId: "+ chatId + ", phoneNumber: " + phoneNumber  + ANSI_RESET));

        }
    }
}
