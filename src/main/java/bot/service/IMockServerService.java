package bot.service;

import bot.MockServer.entities.UserDTO;


public interface IMockServerService {

    UserDTO[] getUserDTO();
    UserDTO getUserDTObyId(String id);
    String createUserDTO(UserDTO userDTO);
    UserDTO generateUserDTO();
    String allowedOperations(String id);
}
