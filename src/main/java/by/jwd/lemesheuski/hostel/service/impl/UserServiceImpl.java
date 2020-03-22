package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import by.jwd.lemesheuski.hostel.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String auth(String login, String password) throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        String role = null;
        try{
            role = userDAO.findRoleByLoginAndPassword(login, password);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return role;
    }

    @Override
    public String signUp(String login, String password, String password2, String surname, String name,
                         String patronymic) throws ServiceException {
        String dataIncorrect="Некоторые данные не верны";
        String success="Успешно зарегестрированы";
        String loginIsPresent= "Логин уже занят";

        String loginPattern = "[a-zA-Z1-9_]{3,20}";
        String passwordPattern = "[a-zA-Z1-9_]{5,20}";
        String SNPPattern = "[A-ZА-Я][a-zа-я]{2,15}";

        if (login.matches(loginPattern) && password.matches(passwordPattern) && password.equals(password2)
                && surname.matches(SNPPattern) && name.matches(SNPPattern)
                && patronymic.matches(SNPPattern)){
            try {
                if(DAOProvider.getInstance().getUserDAO().addUser(login, password, "ROLE_USER",surname, name, patronymic)==1){
                    return success;
                }else
                    return loginIsPresent;
            }catch (DAOException e){
                throw new ServiceException(e);
            }
        }else {
            return dataIncorrect;
        }
    }
}
