import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class AydarTestBot extends TelegramLongPollingBot {

    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";
    private static final String USERNAME = "aydar_test_bot";



    public void setButtons (SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton( "/добавить дату рождения"));
        keyboardRowsList.add(keyboardRowFirst);
        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
    }

    public String getBotUsername() { return USERNAME; }
    public String getBotToken() { return TOKEN; }
    public boolean isBirthDate = false;

    @Override
    public void onUpdateReceived(Update update) {
        try {

            Message inMessage = update.getMessage();
            SendMessage outMessage = new SendMessage();
            outMessage.enableMarkdown(true);
            User user = new User();
            user.setChat_id(inMessage.getChatId());
            user.setUserName(inMessage.getChat().getUserName());
            user.mapDatabase(user.getChat_id(), user.getUserName());
            outMessage.setChatId(user.getChat_id());
            outMessage.setText(inMessage.getText());

            if (isBirthDate == false && update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/добавить дату рождения") != true ) {

                setButtons(outMessage);
                execute(outMessage);

            } else if (update.getMessage().getText().equals("/добавить дату рождения")) {

                isBirthDate = true;
                outMessage.setText("ВВедите дату рождения в формате ДД.ММ.ГГГГ");
                execute(outMessage);

            } else if (update.getMessage().getText().equals("/добавить дату рождения") != true && isBirthDate == true ) {

                    user.setBirthDate(update.getMessage().getText());
                    outMessage.setText("введенная дата: " + user.getBirthDate());
                    user.mapBirthDay(user.getBirthDate(), user.getUserName());
                    execute(outMessage);
                    isBirthDate = false;
                }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }




}
