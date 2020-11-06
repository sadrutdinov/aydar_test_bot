package bot.service;


import bot.MockServer.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMockService implements IUserMockService {

    private IKeyboard iKeyboard;
    private IUserService iUserService;
    private boolean isBirthDate = false;
    private IMockServerService iMockServerService;
    List<Long> authorizedTracker = new ArrayList<>();



    public List<Long> getAuthorizedTracker() {
        return authorizedTracker;
    }



    @Autowired
    public void setIMockServerService(IMockServerService iMockServerService) {
        this.iMockServerService = iMockServerService;
    }


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
        outMessage.enableMarkdown(false);

        iUserService.setChatId(inMessage.getChatId());
        Long chatId = inMessage.getChatId();  // input ChatId
        iUserService.setMessage(inMessage.getText());
        String message = inMessage.getText();   //input message
        String userName = inMessage.getChat().getUserName();

        if (message.equals("/start") && !isBirthDate && !authorizedTracker.contains(chatId)|| !authorizedTracker.contains(chatId)) {
            if (message.equals("/start")) {
                outMessage.setText("Привет, я классный бот, который умеет запоминать день рождения. Чтобы продолжить, введите номер телефона в формате +79XXXXXXXXX");

            } else if (message.length() == 12 && message.startsWith("+7")) {
                UserDTO[] whiteList = iMockServerService.getUserDTO();
                List<String> phone = new ArrayList<>();
                for (int i = 0; i < whiteList.length; i++) {
                    phone.add(whiteList[i].getPhone());
                }
                if (phone.contains(message)) {
                    outMessage.setText("вы успешно авторизованы");
                    iKeyboard.setButtons(outMessage);
                    authorizedTracker.add(chatId);
                    iUserService.start(chatId, message, userName);
                } else {
                    outMessage.setText("для авторизации введите номер телефона в формате +79XXXXXXXXX");

                }
            } else outMessage.setText("для авторизации введите номер телефона в формате +79XXXXXXXXX");

        } else if (message.equals("/help") && !isBirthDate && authorizedTracker.contains(chatId)) {
            outMessage.setText(iUserService.help());
            iKeyboard.setButtons(outMessage);
        } else if (message.equals("/info") && !isBirthDate && authorizedTracker.contains(chatId)) {
            outMessage.setText(iUserService.info());
            iKeyboard.setButtons(outMessage);
        } else if (message.equals("/addBirthDay") && authorizedTracker.contains(chatId) || isBirthDate) {
            String outMsg = iUserService.addBirthDay(message, chatId);

            if (outMsg.equals("введите дату рождения в формате ДД.ММ.ГГГГ")) {
                isBirthDate = true;
            } else if (outMsg.equals("Спасибо!")) {
                isBirthDate = false;
            }
            outMessage.setText(outMsg);
            iKeyboard.setButtons(outMessage);
        } else {
            outMessage.setText(iUserService.echo(message));
            iKeyboard.setButtons(outMessage);
        }
        outMessage.setChatId(chatId);


        return outMessage;
    }
}




