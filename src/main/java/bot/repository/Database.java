//package bot.repository;
//
//import bot.service.IDatabase;
//import bot.entities.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//
//public class Database implements IDatabase {
//
//    public static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_RESET = "\u001B[0m";
//    Map<Long, String> mapUserName = new HashMap<>(); // мапа юзернеймов
//    Map<Long, Date> mapBirthDay = new HashMap<>(); // мапа дней рождения
//    Map<Long, String> mapPhoneNumber = new HashMap<>();  // мапа номеров телефонов
//    Map<Long, User> userMap = new HashMap<>();  //мапа юзеров
//
//
//    public Map<Long, String> getMapPhoneNumber() {
//        return mapPhoneNumber;
//    }
//
//    public Map<Long, String> getMapUserName() {
//        return mapUserName;
//    }
//
//    public Map<Long, Date> getMapBirthDay() {
//        return mapBirthDay;
//    }
//
//    public Map<Long, User> getUserMap() {
//        return userMap;
//    }
//
//
//    @Override
//    public void mapperUserName(Long chatId, String userName) {
//        if (!mapUserName.containsKey(chatId)) {
//            mapUserName.put(chatId, userName);
//            log.info((ANSI_GREEN + "chatId: " + chatId + ", userName: " + userName + ANSI_RESET));
//        }
//
//    }
//
//    @Override
//    public void mapperBirthDay(Long chatId, Date birthDay) {
//        if (!mapBirthDay.containsKey(chatId)) {
//            mapBirthDay.put(chatId, birthDay);
//            log.info((ANSI_GREEN + "chatId: " + chatId + ", birthDay: " + birthDay + ANSI_RESET));
//        }
//    }
//
//    @Override
//    public void mapperPhoneNumber(Long chatId, String phoneNumber) {
//        if (!mapPhoneNumber.containsKey(chatId)) {
//            mapPhoneNumber.put(chatId, phoneNumber);
//            log.info((ANSI_GREEN + "chatId: " + chatId + ", phoneNumber: " + phoneNumber + ANSI_RESET));
//        }
//    }
//
//
//
//    @Override
//    public void mapperUser(Long chatId) {
//            userMap.put(chatId, new User(chatId, mapUserName.get(chatId), mapBirthDay.get(chatId), mapPhoneNumber.get(chatId)));
//            log.info(userMap.values().toString());
//            //log.info(iUser.toString());
//
//    }
//
//    public String getUserInfo(Long chatId) {
//        if (userMap.containsKey(chatId)) {
//            return "chatId: " + userMap.get(chatId).getChatId() + "\n" + "userName: " + userMap.get(chatId).getUserName() + "\n" + "birthDay: " + userMap.get(chatId).getBirthDate() +
//                    "\n" + "phoneNumber: " + userMap.get(chatId).getPhoneNumber();
//        } else
//            return "для доступа к данным требуется ввести Дату Рождения";
//    }
//
//}
