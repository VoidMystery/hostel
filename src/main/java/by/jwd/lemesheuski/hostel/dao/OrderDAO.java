package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    List<Apartment> findApartmentByDate(LocalDate beginningDate, LocalDate endDate) throws DAOException;
    List<Order> findOrdersByLogin(String login) throws DAOException;
    boolean addNewOrder(int id, String login, LocalDate beginningDate, LocalDate endDate) throws DAOException;
}
