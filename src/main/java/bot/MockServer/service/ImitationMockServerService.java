package bot.MockServer.service;

import bot.MockServer.entities.UserDTO;
import bot.service.IMockServerService;

import java.util.Date;


public class ImitationMockServerService implements IMockServerService {
    @Override
    public UserDTO[] getUserDTO() {
        UserDTO userDTO = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        UserDTO[] arrayUserDTOS = new UserDTO[] {userDTO, userDTO2};
        return  arrayUserDTOS;
    }

    @Override
    public UserDTO getUserDTObyId(String id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Aydar");
        userDTO.setPhone("81234567889");
        userDTO.setIsMale(true);
        userDTO.setBirthDay(new Date(99, 01, 01));
        userDTO.setMiddleName("A");
        userDTO.setChatId("000");
        return userDTO;
    }

    @Override
    public String createUserDTO(UserDTO userDTO) {
        return "05f10d17-0982-4d3b-b0d3-5b5e6b2164d1";
    }

    @Override
    public UserDTO generateUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Aydar");
        userDTO.setPhone("81234567889");
        userDTO.setIsMale(true);
        userDTO.setBirthDay(new Date(99, 01, 01));
        userDTO.setMiddleName("A");
        userDTO.setChatId("000");
        return userDTO;
    }

    @Override
    public String allowedOperations(String id) {


        return "[GET, POST, PATCH, OPTIONS]";
    }
}
