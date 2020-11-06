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


    private IUserService iUserService;
    private IUserMockService iUserMockService;

    @Autowired
    public void setiUserMockService(IUserMockService iUserMockService) {
        this.iUserMockService = iUserMockService;
    }



    @Autowired
    public void setIUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @Override
    public User read(String phoneNumber) {
        Long chatId = 0L;
        for (Map.Entry<Long, String> pair : iUserService.getMapPhoneNumber().entrySet()) {
            if (phoneNumber.equals(pair.getValue())) {
                chatId = pair.getKey();
            }
        }
        return iUserService.getUserMap().get(chatId);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(iUserService.getUserMap().values());
    }

    @Override
    public boolean delete(String phoneNumber) {
        Long chatId = 0L;
        for (Map.Entry<Long, String> pair : iUserService.getMapPhoneNumber().entrySet()) {
            if (phoneNumber.equals(pair.getValue())) {
                chatId = pair.getKey();
            }
        }
        if (iUserService.getUserMap().containsKey(chatId)) {
            iUserService.getUserMap().remove(chatId);
            iUserService.getMapPhoneNumber().remove(chatId);
            iUserService.getMapBirthDay().remove(chatId);
            iUserService.getMapUserName().remove(chatId);
            iUserService.getChatIdList().remove(chatId);
            iUserMockService.getAuthorizedTracker().remove(chatId);
            //

            return true;
        } else return false;
    }

}
