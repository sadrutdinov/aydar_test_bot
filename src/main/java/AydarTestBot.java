import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class AydarTestBot extends TelegramLongPollingBot {

    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";
    private static final String USERNAME = "aydar_test_bot";

    public AydarTestBot(DefaultBotOptions options) {
        super(options);
    }

    public String getBotUsername() { return USERNAME; }
    public String getBotToken() { return TOKEN; }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        User user1 = new User();
     user1.chat_id = update.getMessage().getChatId();
       if (update.getMessage().getText().equals("/start")) {
           execute(new SendMessage(user1.chat_id, "Привет, введи имя именниника"));
       }






    }


}
