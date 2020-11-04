package bot.service;

import bot.entities.User;

import java.util.List;

public interface IRestService {

    User read(String phoneNumber);

    List<User> readAll();

    boolean delete(String phoneNumber);
}
