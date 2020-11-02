package bot.MockServer.service;

import bot.MockServer.entities.UserDTO;
import org.springframework.http.HttpMethod;

import java.util.Set;

public interface IMockServerService {

    public UserDTO[] getUserDTO();
    public UserDTO getUserDTObyId(String id);
    public String createUserDTO(UserDTO userDTO);
    public UserDTO generateUserDTO();
    public String allowedOperations(String id);
}
