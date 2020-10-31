package bot;


import bot.service.IController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class AydarTestBot extends TelegramLongPollingBot {

    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";

    private static final String USERNAME = "aydar_test_bot";

    public String getBotUsername() { return USERNAME; }

    public String getBotToken() { return TOKEN; }


    private IController iController;

    @Autowired
    public void setIUserService(IController iController) {
        this.iController = iController;
    }


    @Override
    public void onUpdateReceived(Update update) {

       SendMessage outMessage = iController.onUpdateReceivedController(update);

        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
