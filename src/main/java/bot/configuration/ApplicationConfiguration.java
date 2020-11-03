package bot.configuration;

import bot.MockServer.service.IMockServerService;
import bot.MockServer.service.ImitationMockServerService;
import bot.MockServer.service.MockServerService;
import bot.service.IUserMockService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration


public class ApplicationConfiguration {


    @Bean
    @ConditionalOnProperty(value = "knownUser", havingValue = "true")
    public IMockServerService mockServerService(){
        return new MockServerService(new RestTemplateBuilder());
    }

    @Bean
    @ConditionalOnProperty(value = "knownUser", havingValue = "false")
    public IMockServerService imitationMockServerService() {
        return new ImitationMockServerService();
    }


}
