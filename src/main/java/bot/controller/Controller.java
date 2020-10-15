package bot.controller;


import bot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Controller implements IController {

    @Autowired
    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    private IUserService iUserService;

    @Override
    public SendMessage onUpdateReceivedController(Update update) {
        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
        outMessage.enableMarkdown(true);

        iUserService.setChatId(inMessage.getChatId());
        Long chatId = inMessage.getChatId();  // входящий чат айди
        iUserService.setMessage(inMessage.getText());
        String message = inMessage.getText();// входящее сообщение
        String userName = inMessage.getChat().getUserName();

        if (message.equals("/start")) {
            String outMsg = iUserService.start(chatId, message, userName);
            outMessage.setText(outMsg);
        }
        else if (message.equals("/help")) {
            String outMsg = iUserService.help(chatId, message);
            outMessage.setText(outMsg);
        }
        else if (!message.equals("/help") && !message.equals("/start") ) {
            String outMsg = iUserService.echo(chatId, message);
            outMessage.setText(outMsg);
        }


        outMessage.setChatId(chatId);

        return outMessage;
    }
}




