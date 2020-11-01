package bot.service;


import bot.service.IUserMockService;
import bot.service.IKeyboard;
import bot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UserMockService implements IUserMockService {

    private IKeyboard iKeyboard;
    private IUserService iUserService;
    private boolean isBirthDate = false;
    private boolean isPhoneNumber = false;


    @Autowired
    public void setIKeyboard(IKeyboard iKeyboard) {
        this.iKeyboard = iKeyboard;
    }

    @Autowired
    public void setIUserService(IUserService iUserService) {
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

        if (message.equals("/start") && !isBirthDate && !isPhoneNumber) {
            outMessage.setText(iUserService.start(chatId, message, userName));
        }

        else if (message.equals("/help") && !isBirthDate && !isPhoneNumber) {
            outMessage.setText( iUserService.help(chatId, message, userName));
        }

        else if (message.equals("/info") && !isBirthDate && !isPhoneNumber) {
            outMessage.setText(iUserService.info(chatId, message, userName));
        }

        else if (message.equals("/addPhoneNumber") && !isBirthDate || isPhoneNumber ) {
            String outMsg = iUserService.addPhoneNumber(chatId, message);

            if (outMsg.equals("введите номер телефона в формате 89XXXXXXXXX")) {
                isPhoneNumber = true;
            }
            else if (outMsg.equals("Номер сохранен, спасибо")) {
                isPhoneNumber = false;
            }
            outMessage.setText(outMsg);




        }
        else if (message.equals("/addBirthDay") && !isPhoneNumber || isBirthDate ) {
            String outMsg = iUserService.addBirthDay(message, chatId);

            if (outMsg.equals("введите дату рождения в формате ДД.ММ.ГГГГ")) {
                isBirthDate = true;
            }
            else if (outMsg.equals("Спасибо!")) {
                isBirthDate = false;
            }
            outMessage.setText(outMsg);
        }
        else {
            outMessage.setText(iUserService.echo(chatId, message, userName));
        }
        outMessage.setChatId(chatId);
        iKeyboard.setButtons(outMessage);

        return outMessage;
    }
}




