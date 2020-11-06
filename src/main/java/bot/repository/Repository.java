package bot.repository;


import bot.entities.User;
import bot.service.IRepository;
import bot.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Repository implements IRepository {
    @Autowired
    private UserRepository userRepository;

    //read
    public void createUser(User user) {
        userRepository.save(user);
    }

    //delete
    public void deleteUser(Long chatId) {
        userRepository.deleteById(chatId);
    }

    //getById
    public User getUser(Long chatId) {
        if (userRepository.existsById(chatId)) {
            return userRepository.findById(chatId).get();
        } else return null;
    }

    //getAll
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        Iterator iterator = userRepository.findAll().iterator();
        while (iterator.hasNext()) {
            userList.add((User) iterator.next());
        }
        return userList;
    }

    public boolean containsUser(Long chatId) {
        if (userRepository.existsById(chatId)) {
            return true;
        } else
            return false;
    }

}
