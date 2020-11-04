package bot.service;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface IUserMockService {
    SendMessage onUpdateReceivedController(Update update);
    List<Long> getAuthorizedTracker();
}
