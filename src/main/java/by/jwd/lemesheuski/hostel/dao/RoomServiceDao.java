package by.jwd.lemesheuski.hostel.dao;

import by.jwd.lemesheuski.hostel.bean.RoomService;

import java.util.List;

public interface RoomServiceDao {
    boolean saveRoomService(String name, double price) throws DAOException;
    boolean addRoomServiceToOrder(int orderId, int roomServiceId) throws DAOException;
    boolean updateServiceHasOrder(int id) throws DAOException;
    List<RoomService> findAllRoomServices() throws DAOException;
    List<RoomService> findRoomServicesByOrderId(int id) throws DAOException;
    RoomService findRoomServiceBySHOId(int id) throws DAOException;
    RoomService findRoomServiceById(int id) throws DAOException;
    RoomService findRoomServiceByServiceName(String name) throws DAOException;
    boolean deleteRoomService(int id) throws DAOException;
    boolean updateRoomServicePrice(String roomServiceName, double price) throws DAOException;
}
