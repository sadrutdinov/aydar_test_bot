package bot;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;




public class AydarTestBot extends TelegramLongPollingBot {


    private static final String TOKEN = "1300405012:AAFR2a8RVzx7rAQdD61SmpXviOpiTDPBLdU";
    private static final String USERNAME = "aydar_test_bot";

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml"
    );

    public AydarTestBot() {}

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

    private IUserService iUserService;

    @Autowired
    public AydarTestBot(IUserService iUserService) {
        this.iUserService = iUserService;
    }



    @Override
    public void onUpdateReceived(Update update) {

        UserService userService = context.getBean("userService", UserService.class);

        Message inMessage = update.getMessage();
        SendMessage outMessage = new SendMessage();
        outMessage.enableMarkdown(true);
        outMessage.setChatId(inMessage.getChatId());
        outMessage.setText(userService.sendString());



        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }








}
