package bot.service.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface IKeyboard {
    void setButtons (SendMessage sendMessage);
}
