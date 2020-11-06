package bot.service;

import bot.entities.User;

import java.util.List;

public interface IRepository {
    void createUser(User user);

    void deleteUser(Long chatId);

    User getUser(Long chatId);

    List<User> getAllUser();

    public boolean containsUser(Long chatId);
}
