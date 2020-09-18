package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.bean.RoomService;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.RoomServiceDao;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceDaoImpl implements RoomServiceDao {

    private static final String SELECT_ALL_ROOM_SERVICE = "select * from `service` `s` where `s`.`active` = '1'";
    private static final String SELECT_ROOM_SERVICE_BY_ID = "select * from `service` `s` where `s`.`id`=?";
    private static final String SELECT_ROOM_SERVICES_BY_ORDER_ID = "select `s`.`id`, `s`.`service_name`, `s`.`price`, " +
            "`sho`.`price`, `sho`.`id` from `service` `s` " +
            "inner join services_has_order sho on s.id = sho.service_id where `sho`.`order_id`=?";
    private static final String SELECT_ROOM_SERVICE_BY_SERVICE_NAME = "select * from `service` `s` where `s`.`service_name`=? " +
            "and `s`.`active`=1";
    private static final String SELECT_ROOM_SERVICES_BY_SHO_ID = "select `s`.`id`, `s`.`service_name`, `s`.`price`, " +
            "`sho`.`price`, `sho`.`id` from `service` `s` " +
            "inner join services_has_order sho on s.id = sho.service_id where `sho`.`id`=?";
    private static final String ADD_ROOM_SERVICE = "insert into `service` (`service_name`, `price`) values (?,?)";
    private static final String ADD_ROOM_SERVICE_TO_ORDER = "insert into `services_has_order` (`order_id`, `service_id`, status) values (?,?,?)";
    private static final String DEACTIVATE_ROOM_SERVICE_BY_ID = "update `service` `s` set `s`.`active`=false  where `s`.`id`=?";
    private static final String DEACTIVATE_ROOM_SERVICE_BY_SERVICE_NAME = "update `service` `s` set `s`.`active`=false  " +
            "where `s`.`service_name`=?";
    private static final String UPDATE_SERVICE_HAS_ORDER = "update `services_has_order` `sho` set `sho`.`status`='paid', " +
            "`sho`.`payment_day`=?, `sho`.`price`=? where `sho`.`id`=?";

    @Override
    public boolean addRoomServiceToOrder(int orderId, int roomServiceId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ADD_ROOM_SERVICE_TO_ORDER);

            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, roomServiceId);
            preparedStatement.setString(3, "added");

            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                status = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement);
        }
        return status;
    }

    @Override
    public List<RoomService> findRoomServicesByOrderId(int id) throws DAOException {
        List<RoomService> roomServices = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROOM_SERVICES_BY_ORDER_ID);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoomService roomService = new RoomService(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDouble(3), resultSet.getDouble(4),
                        resultSet.getInt(5));
                roomServices.add(roomService);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return roomServices;
    }

    @Override
    public boolean saveRoomService(String name, double price) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if (findRoomServiceByServiceName(name) == null) {
                preparedStatement = connection.prepareStatement(ADD_ROOM_SERVICE);

                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, price);

                int count = preparedStatement.executeUpdate();
                if (count > 0) {
                    status = true;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement);
        }
        return status;
    }

    @Override
    public List<RoomService> findAllRoomServices() throws DAOException {
        List<RoomService> roomServices = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SELECT_ALL_ROOM_SERVICE);
            while (resultSet.next()) {
                RoomService roomService = new RoomService(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getDouble(3));
                roomServices.add(roomService);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, statement, resultSet);
        }
        return roomServices;
    }

    @Override
    public RoomService findRoomServiceById(int id) throws DAOException {
        RoomService roomService = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROOM_SERVICE_BY_ID);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                roomService = new RoomService(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getDouble(3));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return roomService;
    }

    @Override
    public RoomService findRoomServiceByServiceName(String name) throws DAOException {
        RoomService roomService = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROOM_SERVICE_BY_SERVICE_NAME);

            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                roomService = new RoomService(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getDouble(3));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return roomService;
    }

    @Override
    public RoomService findRoomServiceBySHOId(int id) throws DAOException {
        RoomService roomService = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_ROOM_SERVICES_BY_SHO_ID);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                roomService = new RoomService(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDouble(3), resultSet.getDouble(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return roomService;
    }

    @Override
    public boolean deleteRoomService(int id) throws DAOException {
        boolean status = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(DEACTIVATE_ROOM_SERVICE_BY_ID);

            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                status = true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return status;
    }

    @Override
    public boolean updateRoomServicePrice(String roomServiceName, double price) throws DAOException {
        boolean status = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(DEACTIVATE_ROOM_SERVICE_BY_SERVICE_NAME);

            preparedStatement.setString(1, roomServiceName);
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                status = saveRoomService(roomServiceName, price);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return status;
    }

    @Override
    public boolean updateServiceHasOrder(int id) throws DAOException {
        boolean status = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Order order = DAOProvider.getInstance().getOrderDAO().findOrderBySHOId(id);
        User user =  DAOProvider.getInstance().getUserDAO().findUserById(order.getUserId());
        LocalDate now = LocalDate.now();
        RoomService roomService = findRoomServiceBySHOId(id);
        if (order != null) {
            try {
                double price = roomService.getPrice()* Period.between(now, order.getEndDate()).getDays()*
                        (1-(double)user.getDiscount()/100);

                connection = ConnectionPool.getInstance().takeConnection();
                preparedStatement = connection.prepareStatement(UPDATE_SERVICE_HAS_ORDER);

                preparedStatement.setString(1, now.toString());
                preparedStatement.setDouble(2, price);
                preparedStatement.setInt(3, id);
                int count = preparedStatement.executeUpdate();
                if (count > 0) {
                    status = true;
                }
            } catch (SQLException | ConnectionPoolException e) {
                throw new DAOException(e);
            } finally {
                DAOUtil.finallyClose(connection, preparedStatement, resultSet);
            }
        }
        return status;
    }
}
