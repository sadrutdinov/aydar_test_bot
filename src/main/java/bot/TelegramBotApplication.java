package bot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;




@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {


        ApiContextInitializer.init();
        ConfigurableApplicationContext context = SpringApplication.run(TelegramBotApplication.class, args);
        }



}
