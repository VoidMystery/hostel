package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Apartment> findApartmentByDate(LocalDate beginningDate, LocalDate endDate) throws ServiceException;
    List<Order> findOrdersByLogin(String login) throws ServiceException;
    boolean makeReservation(int id, String login, LocalDate beginningDate, LocalDate endDate) throws ServiceException;
}
