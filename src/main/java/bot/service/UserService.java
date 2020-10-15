package bot.service;


import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {


    public UserService() {}

    @Override
    public String sendString() {
        return "приветики";
    }



}




