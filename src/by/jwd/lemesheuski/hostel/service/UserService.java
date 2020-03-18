package by.jwd.lemesheuski.hostel.service;

public interface UserService {
    String auth(String login, String password) throws ServiceException;
    String signUp(String login, String password, String password2, String surname, String name, String patronymic) throws ServiceException;
}
