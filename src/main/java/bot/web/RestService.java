package bot.web;


import bot.entities.User;
import bot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RestService implements IRestService {

    private IDatabase iDatabase;
    private IUserService iUserService;
    private IUserMockService iUserMockService;

    @Autowired
    public void setiUserMockService(IUserMockService iUserMockService) {
        this.iUserMockService = iUserMockService;
    }

    @Autowired
    public void setIDatabase(IDatabase iDatabase) {
        this.iDatabase = iDatabase;
    }
    @Autowired
    public void setIUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @Override
    public User read(String phoneNumber) {
        Long chatId = 0L;
        for (Map.Entry<Long, String> pair : iDatabase.getMapPhoneNumber().entrySet()) {
            if (phoneNumber.equals(pair.getValue())) {
                chatId = pair.getKey();
            }
        }
        return iDatabase.getUserMap().get(chatId);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(iDatabase.getUserMap().values());
    }

    @Override
    public boolean delete(String phoneNumber) {
        Long chatId = 0L;
        for (Map.Entry<Long, String> pair : iDatabase.getMapPhoneNumber().entrySet()) {
            if (phoneNumber.equals(pair.getValue())) {
                chatId = pair.getKey();
            }
        }
          if (iDatabase.getUserMap().containsKey(chatId)) {
             iDatabase.getUserMap().remove(chatId);
             iDatabase.getMapPhoneNumber().remove(chatId);
             iDatabase.getMapBirthDay().remove(chatId);
             iDatabase.getMapUserName().remove(chatId);
             iUserService.getChatIdList().remove(chatId);
             iUserMockService.getAuthorizedTracker().remove(chatId);
           //

              return true;
          }
          else return false;
    }

}
