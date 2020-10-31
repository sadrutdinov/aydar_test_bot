package bot.service;

public interface IUser {

    public String getUserName();
    public void setUserName(String userName) ;

    public String getBirthDate();
    public void setBirthDate(String birthDate);

    public Long getChatId() ;
    public void setChatId(Long chatId);

    public String getPhoneNumber() ;
    public void setPhoneNumber(String phoneNumber);
}
