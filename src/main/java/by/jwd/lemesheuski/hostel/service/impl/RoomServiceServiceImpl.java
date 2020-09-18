package by.jwd.lemesheuski.hostel.service.impl;

import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.bean.RoomService;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.*;
import by.jwd.lemesheuski.hostel.service.RoomServiceService;
import by.jwd.lemesheuski.hostel.service.ServiceException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class RoomServiceServiceImpl implements RoomServiceService {

    private final RoomServiceDao roomServiceDao = DAOProvider.getInstance().getRoomServiceDao();
    private final OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

    private static final String SERVICE_NAME_PATTERN = "[А-я ]{4,}";

    @Override
    public boolean addRoomServiceToOrder(int orderId, int roomServiceId) throws ServiceException {
        boolean status = false;
        if (orderId > 0 && roomServiceId > 0) {
            try {
                status = roomServiceDao.addRoomServiceToOrder(orderId, roomServiceId);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return status;
    }

    @Override
    public List<RoomService> findRoomServicesByOrderId(int id) throws ServiceException {

        try {
            List<RoomService> roomServices = roomServiceDao.findRoomServicesByOrderId(id);
            Order order = DAOProvider.getInstance().getOrderDAO().findOrderById(id);
            int period = Period.between(LocalDate.now(), order.getEndDate()).getDays();
            for (RoomService r : roomServices) {
                if (r.getCost() == 0) {
                    r.setPrice(r.getPrice() * period *
                            (1 - (double) userDAO.findUserById(order.getUserId()).getDiscount() / 100));
                }
            }
            return roomServices;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean saveRoomService(String name, double price) throws ServiceException {
        boolean status = false;
        if (name.matches(SERVICE_NAME_PATTERN) && price > 0) {
            try {
                status = roomServiceDao.saveRoomService(name, price);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return status;
    }

    @Override
    public List<RoomService> findAllRoomService() throws ServiceException {
        try {
            return roomServiceDao.findAllRoomServices();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RoomService findRoomServiceById(int id) throws ServiceException {
        try {
            return roomServiceDao.findRoomServiceById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RoomService findRoomServiceByServiceName(String name) throws ServiceException {
        try {
            return roomServiceDao.findRoomServiceByServiceName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteRoomService(int id) throws ServiceException {
        try {
            return roomServiceDao.deleteRoomService(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateRoomServicePrice(String name, double price) throws ServiceException {
        boolean status = false;
        if (price > 0) {
            try {
                status = roomServiceDao.updateRoomServicePrice(name, price);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return status;
    }

    @Override
    public boolean setPaidRoomService(int id) throws ServiceException {
        boolean status = false;
        try {
            if (roomServiceDao.updateServiceHasOrder(id)) {
                Order order = orderDAO.findOrderBySHOId(id);
                User user = userDAO.findUserById(order.getUserId());
                userDAO.updateUserMoneySpentById(order.getUserId(), user.getMoneySpent() +
                        roomServiceDao.findRoomServiceBySHOId(id).getCost());
                userDAO.updateUserDiscountById(id);
                status = true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return status;
    }
}
