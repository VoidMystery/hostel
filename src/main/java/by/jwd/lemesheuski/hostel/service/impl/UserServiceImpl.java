package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String auth(String login, String password) throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        String role;
        try {
            if (login == null || password == null) {
                role = null;
            } else {
                role = userDAO.findRoleByLoginAndPassword(login, password);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return role;
    }

    @Override
    public int signUp(String login, String password, String password2, String surname, String name,
                          String patronymic) throws ServiceException {
        if (singUpValidate(login, password, password2, surname, name, patronymic)) {
            try {
                return DAOProvider.getInstance().getUserDAO().addUser(login, password, "ROLE_USER", surname, name, patronymic);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return 0;
    }

    private boolean singUpValidate(String login, String password, String password2, String surname, String name,
                                   String patronymic) {
        final String loginPattern = "[a-zA-Z1-9_]{3,20}";
        final String passwordPattern = "[a-zA-Z1-9_]{5,20}";
        final String SNPPattern = "([A-Z][a-z]{2,15})|([А-Я][а-я]{2,15})";

        if (login == null || !login.matches(loginPattern)){
            return false;
        }
        if(password == null || password2 == null || !password.matches(passwordPattern)){
            return false;
        }else if(!password2.equals(password)){
            return false;
        }
        if (surname == null || !surname.matches(SNPPattern)){
            return false;
        }
        if(name == null || !name.matches(SNPPattern)){
            return false;
        }
        if(patronymic == null || !patronymic.matches(SNPPattern)){
            return false;
        }
        return true;
    }

    @Override
    public User getUserInfo(String login) throws ServiceException {
        if (login == null) {
            return null;
        }
        try {
            return DAOProvider.getInstance().getUserDAO().findUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
