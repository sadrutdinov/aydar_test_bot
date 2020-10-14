package bot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;


@SpringBootApplication
public class TelegramBotApplication {
    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new AydarTestBot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
