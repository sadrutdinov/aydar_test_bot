package service;

public interface IDatabase {

    void mapDatabase(long chat_id, String userName);

    void mapBirthDay(String birthDate, String userName);
}
