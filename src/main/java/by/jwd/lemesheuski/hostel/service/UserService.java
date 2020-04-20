package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.bean.MessagesSack;
import by.jwd.lemesheuski.hostel.bean.User;

public interface UserService {
    String auth(String login, String password) throws ServiceException;
    MessagesSack signUp(String login, String password, String password2, String surname, String name, String patronymic) throws ServiceException;
    User getUserInfo(String login) throws  ServiceException;
}
