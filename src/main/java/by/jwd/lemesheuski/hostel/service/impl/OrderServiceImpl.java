package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.OrderDAO;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.service.OrderService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();


    @Override
    public Order findOrderById(int id) throws ServiceException {
        Order order;
        try {
            order = orderDAO.findOrderById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public List<Apartment> findApartmentByDate(LocalDate beginningDate, LocalDate endDate) throws ServiceException {
        List<Apartment> apartmentList = new ArrayList<>();
        if (beginningDate.compareTo(endDate) < 0) {
            try {
                apartmentList = orderDAO.findApartmentByDate(beginningDate, endDate);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return apartmentList;
    }

    @Override
    public List<Order> findOrdersByLogin(String login) throws ServiceException {
        List<Order> orderList = null;
        try {
            orderList = orderDAO.findOrdersByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public boolean makeReservation(int id, String login, LocalDate beginningDate, LocalDate endDate) throws ServiceException {
        boolean status = false;
        if (beginningDate.compareTo(endDate) < 0) {
            try {
                status = orderDAO.addNewOrder(id, login, beginningDate, endDate);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return status;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDAO.findAllOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public boolean updateOrderStatusById(int id) throws ServiceException {
        Order order;
        User user;
        final String status = "paid";
        boolean operationStatus = false;
        try {
            order = orderDAO.findOrderById(id);
            user = userDAO.findUserById(order.getUserId());
            userDAO.updateUserMoneySpentById(order.getUserId(), user.getMoneySpent() + order.getPrice());
            userDAO.updateUserDiscountById(id);
            if (order.getId() != 0) {
                order.setStatus(status);
                operationStatus = orderDAO.updateOrderById(order);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return operationStatus;
    }
}
