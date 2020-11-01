package bot.MockServer.service;

import bot.MockServer.entities.UserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MockServerService implements IMockServerService {

    private final RestTemplate restTemplate;

    public MockServerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //GET
    public UserDTO[] getUserDTO() {
        String url = "https://serene-coast-56441.herokuapp.com/api/users";
        return this.restTemplate.getForObject(url, UserDTO[].class);
    }


}


