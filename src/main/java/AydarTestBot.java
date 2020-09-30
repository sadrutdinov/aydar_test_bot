import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
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

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new AydarTestBot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
       // sendMessage.setReplyToMessageId(message.getMessageId()); - реплаит сообщение
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButtons (SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton( "/print userName"));
        keyboardRowFirst.add(new KeyboardButton( "/print chat_id"));
        keyboardRowFirst.add(new KeyboardButton( "/add birthDate"));

        keyboardRowsList.add(keyboardRowFirst);
        replyKeyboardMarkup.setKeyboard(keyboardRowsList);

    }

    public String getBotUsername() { return USERNAME; }
    public String getBotToken() { return TOKEN; }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = new User();
        user.chat_id = message.getChatId();
        user.userName = message.getChat().getUserName();

        if (message != null && message.hasText()){

            switch (message.getText()){
                case "/start":
                    sendMsg(message, "Привет!, Нажимай на кнопки!");
                    break;
                case "/print userName":
                    sendMsg(message, "имя пользователя: "+ user.userName);
                            break;
                case "/print chat_id":
                    sendMsg(message, "id чата: "+ user.chat_id);
                    break;
                case "/add birthDate":
                    sendMsg(message, "Введи дату рождения в формате ДД.ММ.ГГГГ");
                    break;
            }
       }
    }


}
