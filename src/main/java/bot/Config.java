package bot;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.UserService;

@ComponentScan("service")
@Configuration
public class Config {
    @Bean
    public UserService createUserService() {
        return new UserService();
    }
}
