package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private DAOProvider(){}

    public static DAOProvider getInstance() {
        return instance;
    }

    private final UserDAO userDAO = new UserDAOImpl();

    public UserDAO getUserDAO(){
        return userDAO;
    }
}
