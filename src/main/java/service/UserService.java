package service;


import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService{


    @Override
    public String sendString() {
        return "приветики";
    }}

//    public void onUpdateReceived(Update update) {
//
//        Message inMessage = update.getMessage();
//        SendMessage outMessage = new SendMessage();
//        outMessage.enableMarkdown(true);
//        outMessage.setChatId(inMessage.getChatId());
//        outMessage.setText(inMessage.getText());
//
//        try {
//            execute(outMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}
