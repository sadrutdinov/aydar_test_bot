package bot.service;

import java.util.List;

public interface IRestService {

    IUser read(String phoneNumber);

    List<IUser> readAll();

    boolean delete(String phoneNumber);
}
