package bot.MockServer.service;

import bot.MockServer.entities.UserDTO;

public class ImitationMockServerService implements IMockServerService {
    @Override
    public UserDTO[] getUserDTO() {
        return new UserDTO[0];
    }
}
