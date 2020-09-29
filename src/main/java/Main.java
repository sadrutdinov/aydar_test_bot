
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;



public class Main {
    public static void main(String[] args) {

        ApiContextInitializer.init();


        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        AydarTestBot aydarTestBot = new AydarTestBot(botOptions);

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(aydarTestBot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
