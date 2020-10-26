package bot.service;

import bot.service.entities.IUser;

import java.util.List;

public interface IRestService {

    IUser read(String phoneNumber);

    List<IUser> readAll();

    boolean delete(String phoneNumber);
}
