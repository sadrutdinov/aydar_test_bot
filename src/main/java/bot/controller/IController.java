package bot.controller;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface IController {
    SendMessage onUpdateReceivedController(Update update);
}
