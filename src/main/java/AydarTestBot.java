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

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            try {
                execute(new SendMessage(chat_id, "подпишись на меня в инсте https://www.instagram.com/aydarfx/"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }


}
