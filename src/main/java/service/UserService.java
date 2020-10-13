package service;


import org.springframework.stereotype.Component;

@Component("userService")
public class UserService implements IUserService{


    @Override
    public String sendString() {
        return "приветики";
    }


}
