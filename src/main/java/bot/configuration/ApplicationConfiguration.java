package bot.configuration;

import bot.service.IMockServerService;
import bot.MockServer.service.ImitationMockServerService;
import bot.MockServer.service.MockServerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration


public class ApplicationConfiguration {


    @Bean
    @ConditionalOnProperty(value = "knownUser", havingValue = "true")
    public IMockServerService mockServerService(){
        return new MockServerService();
    }

    @Bean
    @ConditionalOnProperty(value = "knownUser", havingValue = "false")
    public IMockServerService imitationMockServerService() {
        return new ImitationMockServerService();
    }


}
