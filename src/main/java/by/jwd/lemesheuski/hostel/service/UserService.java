package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.bean.User;

public interface UserService {
    String auth(String login, String password) throws ServiceException;
    int signUp(String login, String password, String password2, String surname, String name, String patronymic) throws ServiceException;
    User getUserInfo(String login) throws  ServiceException;
    boolean updateUserPasswordByLogin(String login, String oldPassword, String newPassword, String newPassword2) throws ServiceException;
    boolean updateUserDiscountByLogin(String login) throws  ServiceException;
}
