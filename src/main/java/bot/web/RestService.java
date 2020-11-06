package bot.web;


import bot.entities.User;
import bot.service.IRepository;
import bot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RestService implements IRestService {


    private IUserService iUserService;
    private IUserMockService iUserMockService;
    private IRepository iRepository;

    @Autowired
    public void setiUserMockService(IUserMockService iUserMockService) {
        this.iUserMockService = iUserMockService;
    }

    @Autowired
    public void setIRepository(IRepository iRepository) {
        this.iRepository = iRepository;
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
        return iRepository.getUser(chatId);
    }

    @Override
    public List<User> readAll() {
        return iRepository.getAllUser();
    }

    @Override
    public boolean delete(String phoneNumber) {
        Long chatId = 0L;
        for (Map.Entry<Long, String> pair : iUserService.getMapPhoneNumber().entrySet()) {
            if (phoneNumber.equals(pair.getValue())) {
                chatId = pair.getKey();
            }
        }
        if (iRepository.containsUser(chatId)) {
            iUserService.getMapPhoneNumber().remove(chatId);
            iUserService.getMapBirthDay().remove(chatId);
            iUserService.getMapUserName().remove(chatId);
            iUserService.getChatIdList().remove(chatId);
            iUserMockService.getAuthorizedTracker().remove(chatId);
            iRepository.deleteUser(chatId);
            //

            return true;
        } else return false;
    }

}
