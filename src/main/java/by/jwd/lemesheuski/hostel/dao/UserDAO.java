package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.bean.User;

public interface UserDAO {
    String findRoleByLoginAndPassword(String login, String password) throws DAOException;
    int addUser(String login, String password, String role, String surname, String name, String patronymic) throws DAOException;
    User findUserByLogin(String login) throws DAOException;
}
