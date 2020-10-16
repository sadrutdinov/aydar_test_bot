package bot.controller;


import bot.keyboard.IKeyboard;
import bot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Controller implements IController {



    private IKeyboard iKeyboard;
    private IUserService iUserService;
    private boolean birthDay = false;

    @Autowired
    public void setIKeyboard(IKeyboard iKeyboard) {
        this.iKeyboard = iKeyboard;
    }

    @Autowired
    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }




    @Override
    public SendMessage onUpdateReceivedController(Update update) {
        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
        outMessage.enableMarkdown(true);

        iUserService.setChatId(inMessage.getChatId());
        Long chatId = inMessage.getChatId();  // input ChatId
        iUserService.setMessage(inMessage.getText());
        String message = inMessage.getText();   //input message
        String userName = inMessage.getChat().getUserName();



        if (message.equals("/start") && !birthDay) {
            String outMsg = iUserService.start(chatId, message, userName);
            outMessage.setText(outMsg);
        }
        else if (message.equals("/help") && !birthDay) {
            String outMsg = iUserService.help(message);
            outMessage.setText(outMsg);
        }
        else if (!message.equals("/help") && !message.equals("/start") || birthDay ) {
            String outMsg = iUserService.echo(message);
            if (outMsg.equals("введите дату рождения в формате ДД.ММ.ГГГГ")) {
                birthDay = true;
            }
            else if (outMsg.equals("Спасибо!")) {
                birthDay = false;


            }
            else if (outMsg.equals("введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ")) {
                birthDay = true;
            }
                outMessage.setText(outMsg);
        }
        outMessage.setChatId(chatId);
        iKeyboard.setButtons(outMessage);
        return outMessage;
    }
}




