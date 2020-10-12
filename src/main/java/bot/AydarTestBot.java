package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.UserService;

public class AydarTestBot extends TelegramLongPollingBot {

    UserService userService = new UserService();

    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";
    private static final String USERNAME = "aydar_test_bot";

    public String getBotUsername() { return USERNAME; }

    public String getBotToken() { return TOKEN; }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new AydarTestBot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {
              execute(userService.service(update));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }








}
