package bot.service;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class UserService implements IUserService {

    private long chatId;
    private String message;
    private String username;

    @Override
    public String start(Long chatId, String message, String userName) {
    this.chatId = chatId;
    this.message = message;


        return "Привет, я классный бот, UserName: " + userName;
    }

    public String help(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;



        return "Доступные команды:";
    }

    public String echo(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;


        return message;
    }

  //  @Override
//    public SendMessage help(SendMessage outMessage, Message inMessage) {
//        outMessage.setText("доступные команды:");
//        outMessage.setChatId(inMessage.getChatId());
//        return outMessage;
//    }
//
//    @Override
//    public SendMessage echo(SendMessage outMessage, Message inMessage) {
//        outMessage.setText(inMessage.getText());
//        outMessage.setChatId(inMessage.getChatId());
//        return outMessage;
//    }

}
