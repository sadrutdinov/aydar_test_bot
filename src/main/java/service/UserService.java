package service;

import database.Database;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class UserService {

    private IDatabase database;

    public UserService(IDatabase database) {
        this.database = database;
    }

    static final UserService userService1 = new UserService(new Database());

    public UserService() {}
    User user = new User();
    Keyboard keyboard = new Keyboard();
    private boolean isBirthDate = false;

    private int day;
    private int month;
    private int year;


    public SendMessage service(Update update) {


        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
        outMessage.enableMarkdown(true);
        user.setChat_id(inMessage.getChatId());
        user.setUserName(inMessage.getChat().getUserName());
        userService1.database.mapDatabase(user.getChat_id(), user.getUserName());
        outMessage.setChatId(user.getChat_id());
        outMessage.setText(inMessage.getText());
        keyboard.setButtons(outMessage);

        if (!isBirthDate && update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().equals("/добавить дату рождения") ) {


            return outMessage;

        } else if (update.getMessage().getText().equals("/добавить дату рождения")) {

            isBirthDate = true;
            outMessage.setText("Введите дату рождения в формате ДД.ММ.ГГГГ");
            return outMessage;

        } else if (!update.getMessage().getText().equals("/добавить дату рождения") && isBirthDate) {

            try {
                String x = update.getMessage().getText();
                String[] xList = x.split("\\D");
                day = Integer.parseInt(xList[0]);
                month = Integer.parseInt(xList[1]);
                year = Integer.parseInt(xList[2]);


            } catch (Exception e) {
                outMessage.setText("введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ ");
                day = 0;
                month = 0;
                year = 0;
                return outMessage;

            }

            if ((update.getMessage().getText().length() == 10) && ((day > 0) && (day <32)) && ((month > 0) && (month <13)) && ((year > 1900) && (year < 2021))) {
                user.setBirthDate(update.getMessage().getText());
                outMessage.setText("введенная дата: " + user.getBirthDate());
                userService1.database.mapBirthDay(user.getBirthDate(), user.getUserName());
                day = 0;
                month = 0;
                year = 0;
                isBirthDate = false;
                return outMessage;


            }
            else {
                outMessage.setText("введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ ");

                day = 0;
                month = 0;
                year = 0;
                return outMessage;

            }
        }
       else return outMessage;
    }
}