package bot.service;

import java.util.Date;

public interface IUser {

    public String getUserName();
    public void setUserName(String userName) ;

    public Date getBirthDate();
    public void setBirthDate(Date birthDate);

    public Long getChatId() ;
    public void setChatId(Long chatId);

    public String getPhoneNumber() ;
    public void setPhoneNumber(String phoneNumber);
}
