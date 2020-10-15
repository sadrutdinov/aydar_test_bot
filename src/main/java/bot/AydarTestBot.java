package bot;


import bot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class AydarTestBot extends TelegramLongPollingBot {

    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";

    private static final String USERNAME = "aydar_test_bot";

    public String getBotUsername() { return USERNAME; }

    public String getBotToken() { return TOKEN; }


    private IUserService iUserService;

    @Autowired
    public void setIUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @Override
    public void onUpdateReceived(Update update) {

        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
        outMessage.enableMarkdown(true);

        outMessage.setChatId(inMessage.getChatId());

        outMessage.setText(iUserService.sendString());

        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
