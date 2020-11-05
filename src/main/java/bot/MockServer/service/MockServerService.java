package bot.MockServer.service;

import bot.MockServer.entities.UserDTO;
import bot.service.IMockServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MockServerService implements IMockServerService {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //get
    public UserDTO[] getUserDTO() {
        String url = "https://serene-coast-56441.herokuapp.com/api/users";
        ResponseEntity<UserDTO[]> response = this.restTemplate.getForEntity(url, UserDTO[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //GetById
    public UserDTO getUserDTObyId(String id) {
        String url = "https://serene-coast-56441.herokuapp.com/api/users/{id}";

        ResponseEntity<UserDTO> response = this.restTemplate.getForEntity(url, UserDTO.class, id);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //Create
    public String createUserDTO(UserDTO userDTO) {

        String url = "https://serene-coast-56441.herokuapp.com/api/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("birthDay", userDTO.getBirthDay());
        map.put("chatId", userDTO.getChatId());
        map.put("firstName", userDTO.getFirstName());
        map.put("male", userDTO.getIsMale());
        map.put("middleName", userDTO.getMiddleName());
        map.put("phone", userDTO.getPhone());
        map.put("secondName", userDTO.getSecondName());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.toString().substring(6, 42);  // работает!!!!!!
        } else {
            return "я не смог";
        }
    }

    //generate
    public UserDTO generateUserDTO() {
        String url = "https://serene-coast-56441.herokuapp.com/api/users/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDTO> response = this.restTemplate.postForEntity(url, entity, UserDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //option
    public String allowedOperations(String id) {
        String url = "https://serene-coast-56441.herokuapp.com/api/users/{id}";


        return this.restTemplate.optionsForAllow(url, id).toString();
    }

}


