package bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import javax.annotation.PostConstruct;

@Component

public class App {

    @Autowired
    public void setTelegramLongPollingBot(TelegramLongPollingBot telegramLongPollingBot) {
        this.telegramLongPollingBot = telegramLongPollingBot;
    }

    private TelegramLongPollingBot telegramLongPollingBot;

    @PostConstruct
    public void start() {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(telegramLongPollingBot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
