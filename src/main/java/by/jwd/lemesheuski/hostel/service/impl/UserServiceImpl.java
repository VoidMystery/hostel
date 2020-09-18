package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.OrderDAO;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.UserService;
import by.jwd.lemesheuski.hostel.service.validator.UserServiceValidator;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

    @Override
    public String auth(String login, String password) throws ServiceException {
        String role=null;
        try {
            if (login != null || password != null) {
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
        if (UserServiceValidator.isSingUpParamsValid(login, password, password2, surname, name, patronymic)) {
            try {
                return userDAO.addUser(login, password, "ROLE_USER", surname, name, patronymic);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return 0;
    }

    @Override
    public User getUserInfo(String login) throws ServiceException {
        User user = new User();
        if (login != null) {
            try {
                user = userDAO.findUserByLogin(login);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return user;
    }

    @Override
    public boolean updateUserPasswordByLogin(String login, String oldPassword, String newPassword, String newPassword2) throws ServiceException {
        boolean status = false;
        try {
            if (auth(login, oldPassword) != null && newPassword.equals(newPassword2)
                    && UserServiceValidator.isPasswordValid(newPassword)) {

                 status = userDAO.updateUserPasswordByLogin(login, newPassword);
            }
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return status;
    }

    @Override
    public boolean updateUserDiscountByLogin(String login) throws ServiceException {
        try{
            return userDAO.updateUserDiscountByLogin(login);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
