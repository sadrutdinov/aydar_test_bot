package bot.service;

import bot.service.entities.IUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class UserService implements IUserService {

    private long chatId;
    private String message;
    private String userName;
    private boolean birthDay;
    private boolean SavedBirthDay;
    private int day;
    private int month;
    private int year;
    private IUser iUser;


    @Autowired
    public void setIUser(IUser iUser) {
        this.iUser = iUser;
    }


    @Override
    public String start(Long chatId, String message, String userName) {
    iUser.setChatId(chatId);
    iUser.setUserName(userName);
    this.message = message;

        //TODO сделать маппер для нового пользователя
        return "Привет, я классный бот, UserName: " + iUser.getUserName() + iUser.getChatId();
    }

    public String help(String message) {
        this.message = message;
        return "вызывайте команды через доступные кнопки. Если вы уже ввели дату рождения, то команда добавления будет недоступна. У вас же один день рождения :)";
    }

    public String echo(String message) {
        this.message = message;

        if (message.equals("/addBirthDay") && !birthDay){
            if (!SavedBirthDay) {
                birthDay = true;
                SavedBirthDay = true;
                return "введите дату рождения в формате ДД.ММ.ГГГГ";
            }
            else {
                return "/addBirthDay";
            }

        }
        else if (birthDay)  {
            try {
                String x = message;
                String[] xList = x.split("\\D");
                day = Integer.parseInt(xList[0]);
                month = Integer.parseInt(xList[1]);
                year = Integer.parseInt(xList[2]);
                if (((day > 0) && (day <32)) && ((month > 0) && (month <13)) && ((year > 1900) && (year < 2021))) {
                    birthDay = false;
                //TODO сделать маппер для др
                    day = 0;
                    month = 0;
                    year = 0;


                    return "Спасибо!";
                }
                else {
                    day = 0;
                    month = 0;
                    year = 0;

                    return "введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ";
                }

            } catch (Exception e) {
                day = 0;
                month = 0;
                year = 0;

                return "введен неверный формат! Введите дату рождения в формате ДД.ММ.ГГГГ";

            }
        }

        else return message;
    }



}
