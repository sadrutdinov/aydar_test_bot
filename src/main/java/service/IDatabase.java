package service;

public interface IDatabase {

    void mapDatabase(long chat_id, String userName);

    void mapBirthDay(Long chat_id, String birthDate);
}
