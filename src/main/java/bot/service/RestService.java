package bot.service;


import bot.repository.IDatabase;
import bot.service.entities.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RestService implements IRestService {

    private IDatabase iDatabase;
    private IUserService iUserService;

    @Autowired
    public void setIDatabase(IDatabase iDatabase) {
        this.iDatabase = iDatabase;
    }
    @Autowired
    public void setIUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @Override
    public IUser read(String phoneNumber) {
        Long chatId = 0L;
        for (Map.Entry<Long, String> pair : iDatabase.getMapPhoneNumber().entrySet()) {
            if (phoneNumber.equals(pair.getValue())) {
                chatId = pair.getKey();
            }
        }
        return iDatabase.getUserMap().get(chatId);
    }

    @Override
    public List<IUser> readAll() {
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
             iUserService.getPhoneNumberList().remove(chatId);

              return true;
          }
          else return false;
    }

}
