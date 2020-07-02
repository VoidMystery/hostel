package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.OrderDAO;
import by.jwd.lemesheuski.hostel.service.OrderService;
import by.jwd.lemesheuski.hostel.service.ServiceException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
    private static final Logger log = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public List<Apartment> findApartmentByDate(LocalDate beginningDate, LocalDate endDate) throws ServiceException {
        List<Apartment> apartmentList = new ArrayList<>();
        if(beginningDate.compareTo(endDate)<0){
            try {
                apartmentList = orderDAO.findApartmentByDate(beginningDate, endDate);
            }catch (DAOException e){
                throw new ServiceException(e);
            }
        }
        return apartmentList;
    }

    @Override
    public List<Order> findOrdersByLogin(String login) throws ServiceException {
        List<Order> orderList = null;
        try{
            orderList = orderDAO.findOrdersByLogin(login);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public boolean makeReservation(int id, String login, LocalDate beginningDate, LocalDate endDate) throws ServiceException {
        boolean status=false;
        if(beginningDate.compareTo(endDate)<0){
            try {
                status = orderDAO.addNewOrder(id, login, beginningDate, endDate);
            }catch (DAOException e){
                throw new ServiceException(e);
            }
        }
        return status;
    }
}
