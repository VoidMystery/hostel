package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.MessagesSack;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.controller.command.ErrorMessageName;
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
    public boolean signUp(MessagesSack sack, String login, String password, String password2, String surname, String name,
                          String patronymic) throws ServiceException {
        if (singUpValidate(sack, login, password, password2, surname, name, patronymic)) {
            try {
                if (DAOProvider.getInstance().getUserDAO().addUser(login, password, "ROLE_USER", surname, name, patronymic) == 1) {
                    return true;
                } else {
                    sack.addError(ErrorMessageName.LOGIN_IS_PRESENT);
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    private boolean singUpValidate(MessagesSack sack, String login, String password, String password2, String surname, String name,
                                   String patronymic) {
        String loginPattern = "[a-zA-Z1-9_]{3,20}";
        String passwordPattern = "[a-zA-Z1-9_]{5,20}";
        String SNPPattern = "(([A-Z][a-z])|([А-Я][а-я])){2,15}";

        boolean status = true;

        if (login == null || !login.matches(loginPattern)){
            sack.addError(ErrorMessageName.LOGIN_IS_WRONG);
            status = false;
        }
        if(password == null || password2 == null || !password.matches(passwordPattern)){
            sack.addError(ErrorMessageName.PASSWORD_IS_WRONG);
            status = false;
        }else if(!password2.equals(password)){
            sack.addError(ErrorMessageName.PASSWORDS_DO_NOT_MATCH);
            status = false;
        }
        if (surname == null || !surname.matches(SNPPattern)){
            sack.addError(ErrorMessageName.SURNAME_IS_WRONG);
            status = false;
        }
        if(name == null || !name.matches(SNPPattern)){
            sack.addError(ErrorMessageName.NAME_IS_WRONG);
            status = false;
        }
        if(patronymic == null || !patronymic.matches(SNPPattern)){
            sack.addError(ErrorMessageName.PATRONYMIC_IS_WRONG);
            status = false;
        }
        return status;
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
