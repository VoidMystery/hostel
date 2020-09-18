package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.bean.User;

public interface UserDAO {
    String findRoleByLoginAndPassword(String login, String password) throws DAOException;
    int addUser(String login, String password, String role, String surname, String name, String patronymic) throws DAOException;
    User findUserByLogin(String login) throws DAOException;
    User findUserById(int id) throws DAOException;
    boolean updateUserPasswordByLogin(String login, String password) throws  DAOException;
    boolean updateUserDiscountByLogin(String login) throws  DAOException;
    boolean updateUserDiscountById(int id) throws  DAOException;
    boolean updateUserMoneySpentById(int id, double money) throws  DAOException;
}
