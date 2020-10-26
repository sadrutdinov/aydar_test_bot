package bot.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Getter
@Setter
@Component
public class PingTask implements IPingTask {
    @Value("https://www.google.com")
    private String url;


    @Scheduled(fixedRateString = "60000")
    public void pingMe() {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            log.info(url.getHost(), connection.getResponseCode());

            connection.disconnect();
        } catch (IOException e) {
            log.info("ping failed");
            e.printStackTrace();
        }

    }
}
