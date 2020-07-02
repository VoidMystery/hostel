package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.Order;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.OrderDAO;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Apartment> findApartmentByDate(LocalDate beginningDate, LocalDate endDate) throws DAOException {
        final String ID_COLUMN = "id";
        final String FLOOR_COLUMN = "floor";
        final String ROOM_NUMBER_COLUMN = "number";
        final String APARTMENT_TYPE_COLUMN = "type";
        final String BALCONY_COLUMN = "balcony";
        final String NUMBER_OF_ROOMS_COLUMN = "rooms";
        final String NUMBER_OF_BEDS_COLUMN = "beds";
        final String PRICE_COLUMN = "price";

        final String FIND_APARTMENTS_BY_DATE = "select `a`.`id` as id, `a`.`floor` as floor, `a`.`room_number` as number, " +
                "`nob`.`number_of_beds` as beds, `t`.`apartment_type` as type, `nor`.`number_of_rooms` as rooms, " +
                "`a`.`balcony` as balcony, `a`.`price` as price from aparment a " +
                "inner join `number_of_beds` `nob` on `a`.`number_of_beds_id` = `nob`.`id`" +
                "inner join `apartment_type` `t` on `a`.`apartment_type_id` = `t`.`id`" +
                "inner join `number_of_rooms` `nor` on `a`.`number_of_rooms_id` = `nor`.`number_of_rooms` " +
                "left join " +
                "(select `o`.`aparment_id` as id from `order` `o` where `o`.`beginning_date`<=? " +
                "and `o`.`end_date`>=? group by `id`) as `or` on `a`.`id`=`or`.`id` " +
                "where `or`.`id` is null";

        List<Apartment> apartmentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(FIND_APARTMENTS_BY_DATE);
            preparedStatement.setString(1, beginningDate.toString());
            preparedStatement.setString(2, endDate.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Apartment apartment =
                        new Apartment(resultSet.getInt(ID_COLUMN), resultSet.getInt(FLOOR_COLUMN),
                                resultSet.getInt(ROOM_NUMBER_COLUMN), resultSet.getString(APARTMENT_TYPE_COLUMN),
                                resultSet.getBoolean(BALCONY_COLUMN), resultSet.getInt(NUMBER_OF_ROOMS_COLUMN),
                                resultSet.getInt(NUMBER_OF_BEDS_COLUMN), resultSet.getDouble(PRICE_COLUMN));
                apartmentList.add(apartment);
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return apartmentList;
    }

    @Override
    public List<Order> findOrdersByLogin(String login) throws DAOException {
        final String ID_COLUMN = "id";
        final String BEGINNING_DATE_COLUMN = "beginning_date";
        final String END_DATE_COLUMN = "end_date";
        final String PRICE_COLUMN = "price";
        final String USER_ID_COLUMN = "user_id";
        final String STATUS_COLUMN = "status";
        final String APARTMENT_ID_COLUMN = "apartment_id";
        final String APARTMENT_NUMBER_COLUMN = "apartment_number";

        final String FIND_ORDERS_BY_USER_ID = "select `o`.`id` as id, `o`.`beginning_date` as beginning_date," +
                " `o`.`end_date` as end_date, `o`.`price` as price, `o`.`user_idperson` as user_id, " +
                "`o`.`status` as status, `o`.`aparment_id` as apartment_id, `a`.`room_number` as apartment_number " +
                "from `order` `o` inner join `aparment` `a` on `o`.`aparment_id`=`a`.`id` where `o`.`user_idperson`=?";

        User user = DAOProvider.getInstance().getUserDAO().findUserByLogin(login);
        List<Order> orderList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order =
                        new Order(resultSet.getInt(ID_COLUMN), LocalDate.parse(resultSet.getString(BEGINNING_DATE_COLUMN)),
                                LocalDate.parse(resultSet.getString(END_DATE_COLUMN)), resultSet.getDouble(PRICE_COLUMN),
                                resultSet.getInt(USER_ID_COLUMN), resultSet.getString(STATUS_COLUMN),
                                resultSet.getInt(APARTMENT_ID_COLUMN), resultSet.getInt(APARTMENT_NUMBER_COLUMN));
                orderList.add(order);
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return orderList;
    }

    @Override
    public boolean addNewOrder(int id, String login, LocalDate beginningDate, LocalDate endDate) throws DAOException {
        boolean status = false;
        final String ADD_NEW_ORDER = "insert into `order`(beginning_date, end_date, price, user_idperson, status, aparment_id) " +
                "values(?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_ORDER);

            User user = DAOProvider.getInstance().getUserDAO().findUserByLogin(login);
            if (user.getId() != 0) {

                List<Apartment> apartmentList = findApartmentByDate(beginningDate, endDate);
                if (!apartmentList.isEmpty()) {
                    for (Apartment a : apartmentList) {
                        if (a.getId() == id) {

                            double cost = (Period.between(beginningDate, endDate).getDays()+1)*a.getPrice();

                            preparedStatement.setString(1, beginningDate.toString());
                            preparedStatement.setString(2, endDate.toString());
                            preparedStatement.setDouble(3, cost);
                            preparedStatement.setInt(4, user.getId());
                            preparedStatement.setString(5, "reserved");
                            preparedStatement.setInt(6, id);
                            int count = preparedStatement.executeUpdate();
                            if (count!=0) {
                                status = true;
                            }
                        }
                    }
                }

            }
            ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement);
        }
        return status;
    }
}
