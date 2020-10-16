package bot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface IKeyboard {
    public void setButtons (SendMessage sendMessage);
}
