package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.dao.impl.ApartmentDaoImpl;
import by.jwd.lemesheuski.hostel.dao.impl.OrderDAOImpl;
import by.jwd.lemesheuski.hostel.dao.impl.RoomServiceDaoImpl;
import by.jwd.lemesheuski.hostel.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private DAOProvider(){}

    public static DAOProvider getInstance() {
        return instance;
    }

    private final UserDAO userDAO = new UserDAOImpl();
    private final ApartmentDao apartmentDAO = new ApartmentDaoImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final RoomServiceDao roomServiceDao = new RoomServiceDaoImpl();

    public UserDAO getUserDAO(){
        return userDAO;
    }

    public ApartmentDao getApartmentDAO() {
        return apartmentDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public RoomServiceDao getRoomServiceDao() {
        return roomServiceDao;
    }
}
