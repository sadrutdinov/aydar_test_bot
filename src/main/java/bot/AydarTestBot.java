package bot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.IUserService;
import service.UserService;


public class AydarTestBot extends TelegramLongPollingBot {

    public AydarTestBot(){}
    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";
    private static final String USERNAME = "aydar_test_bot";
    private IUserService iUserService;

    public String getBotUsername() { return USERNAME; }

    public String getBotToken() { return TOKEN; }

    @Autowired
    public AydarTestBot(IUserService iUserService) {
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
