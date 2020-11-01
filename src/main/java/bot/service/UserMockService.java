package bot.service;


import bot.MockServer.entities.UserDTO;
import bot.MockServer.service.IMockServerService;
import bot.service.IUserMockService;
import bot.service.IKeyboard;
import bot.service.IUserService;
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
    private boolean isPhoneNumber = false;
    private boolean authorized = false;
    private IMockServerService iMockServerService;
    List<Long> authorizedTracker = new ArrayList<>();

    @Autowired
    public void setiMockServerService(IMockServerService iMockServerService) {
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
        outMessage.enableMarkdown(true);

        iUserService.setChatId(inMessage.getChatId());
        Long chatId = inMessage.getChatId();  // input ChatId
        iUserService.setMessage(inMessage.getText());
        String message = inMessage.getText();   //input message
        String userName = inMessage.getChat().getUserName();

        if (message.equals("/start") && !isBirthDate && !isPhoneNumber || !authorizedTracker.contains(chatId)) {
            if (message.equals("/start")) {
                outMessage.setText("Привет, я классный бот, который умеет запоминать день рождения. Чтобы продолжить, введите номер телефона в формате +79XXXXXXXXX");

            }
            else if (message.length() == 12 && message.startsWith("+7")) {
                UserDTO[] whiteList = iMockServerService.getUserDTO();
                List<String> phone = new ArrayList<>();
                for (int i = 0; i < whiteList.length; i++) {
                    phone.add(whiteList[i].getPhone());
                }
                if (phone.contains(message)){
                    outMessage.setText("вы успешно авторизованы");
                    iKeyboard.setButtons(outMessage);
                    authorizedTracker.add(chatId);
                }  else {
                    outMessage.setText("введен неизвестный номер");

                }
            } else outMessage.setText("введен неизвестный номер");

        }

        else if (message.equals("/help") && !isBirthDate && !isPhoneNumber && authorizedTracker.contains(chatId)) {
            outMessage.setText( iUserService.help(chatId, message, userName));
            iKeyboard.setButtons(outMessage);
        }

        else if (message.equals("/info") && !isBirthDate && !isPhoneNumber && authorizedTracker.contains(chatId)) {
            outMessage.setText(iUserService.info(chatId, message, userName));
            iKeyboard.setButtons(outMessage);
        }

        else if (message.equals("/addPhoneNumber") && !isBirthDate && authorizedTracker.contains(chatId)|| isPhoneNumber ) {
            String outMsg = iUserService.addPhoneNumber(chatId, message);

            if (outMsg.equals("введите номер телефона в формате 89XXXXXXXXX")) {
                isPhoneNumber = true;
            }
            else if (outMsg.equals("Номер сохранен, спасибо")) {
                isPhoneNumber = false;
            }
            outMessage.setText(outMsg);
            iKeyboard.setButtons(outMessage);




        }
        else if (message.equals("/addBirthDay") && !isPhoneNumber && authorizedTracker.contains(chatId) || isBirthDate ) {
            String outMsg = iUserService.addBirthDay(message, chatId);

            if (outMsg.equals("введите дату рождения в формате ДД.ММ.ГГГГ")) {
                isBirthDate = true;
            }
            else if (outMsg.equals("Спасибо!")) {
                isBirthDate = false;
            }
            outMessage.setText(outMsg);
            iKeyboard.setButtons(outMessage);
        }
        else {
            outMessage.setText(iUserService.echo(chatId, message, userName));
            iKeyboard.setButtons(outMessage);
        }
        outMessage.setChatId(chatId);


        return outMessage;
    }
}




