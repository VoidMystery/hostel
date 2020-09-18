package by.jwd.lemesheuski.hostel.service;

import by.jwd.lemesheuski.hostel.bean.RoomService;

import java.util.List;

public interface RoomServiceService {

    boolean saveRoomService(String name, double price) throws ServiceException;
    boolean addRoomServiceToOrder(int orderId, int roomServiceId) throws ServiceException;
    List<RoomService> findAllRoomService() throws ServiceException;
    List<RoomService> findRoomServicesByOrderId(int id) throws ServiceException;
    RoomService findRoomServiceById(int id) throws ServiceException;
    RoomService findRoomServiceByServiceName(String name) throws ServiceException;
    boolean deleteRoomService(int id) throws ServiceException;
    boolean setPaidRoomService(int id) throws ServiceException;
    boolean updateRoomServicePrice(String name, double price) throws ServiceException;

}
