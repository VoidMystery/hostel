package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.ApartmentType;
import by.jwd.lemesheuski.hostel.bean.NumberOfBeds;
import by.jwd.lemesheuski.hostel.bean.NumberOfRooms;
import by.jwd.lemesheuski.hostel.dao.ApartmentDao;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImpl implements ApartmentDao {
    private static final String ID_COLUMN = "id";
    private static final String FLOOR_COLUMN = "floor";
    private static final String ROOM_NUMBER_COLUMN = "number";
    private static final String APARTMENT_TYPE_COLUMN = "type";
    private static final String BALCONY_COLUMN = "balcony";
    private static final String NUMBER_OF_ROOMS_COLUMN = "rooms";
    private static final String NUMBER_OF_BEDS_COLUMN = "beds";
    private static final String PRICE_COLUMN = "price";
    private static final String STATUS_COLUMN = "status";

    private static final String FIND_APARTMENT_BY_ID = "select `a`.`id` as id, `a`.`floor` as floor, `a`.`room_number` as number, " +
            "`nob`.`number_of_beds` as beds, `t`.`apartment_type` as type, `nor`.`number_of_rooms` as rooms, " +
            "`a`.`balcony` as balcony, `a`.`price` as price from aparment a " +
            "inner join `number_of_beds` `nob` on `a`.`number_of_beds_id` = `nob`.`id` " +
            "inner join `apartment_type` `t` on `a`.`apartment_type_id` = `t`.`id` " +
            "inner join `number_of_rooms` `nor` on `a`.`number_of_rooms_id` = `nor`.`number_of_rooms` " +
            "where `a`.`id`=? and `a`.`status` is NULL";

    private static final String FIND_APARTMENT_BY_ROOM_NUMBER = "select `a`.`id` as id, `a`.`floor` as floor, `a`.`room_number` as number, " +
            "`nob`.`number_of_beds` as beds, `t`.`apartment_type` as type, `nor`.`number_of_rooms` as rooms, " +
            "`a`.`balcony` as balcony, `a`.`price` as price from aparment a " +
            "inner join `number_of_beds` `nob` on `a`.`number_of_beds_id` = `nob`.`id` " +
            "inner join `apartment_type` `t` on `a`.`apartment_type_id` = `t`.`id` " +
            "inner join `number_of_rooms` `nor` on `a`.`number_of_rooms_id` = `nor`.`number_of_rooms` " +
            "where `a`.`room_number`=? and `a`.`status` is NULL";

    private static final String UPDATE_APARTMENT = "update aparment a set `a`.status=? " +
            "where `a`.`id`=?";

    private static final String FIND_NUMBER_OF_BEDS_BY_ID = "select * from `number_of_beds` where `number_of_beds`.`id`=?";

    private static final String FIND_NUMBER_OF_ROOMS_BY_ID = "select * from `number_of_rooms` where `number_of_rooms`.`id`=?";

    private static final String FIND_APARTMENT_TYPE_BY_ID = "select * from `apartment_type` where `apartment_type`.`id`=?";

    private static final String FIND_ALL_NUMBERS_OF_BEDS = "select * from number_of_beds";

    private static final String FIND_ALL_APARTMENT_TYPES = "select * from apartment_type";

    private static final String FIND_ALL_NUMBER_OF_ROOMS = "select * from number_of_rooms";

    private static final String FIND_ALL_APARTMENTS = "select `a`.`id` as id, `a`.`floor` as floor, `a`.`room_number` as number, " +
            "`nob`.`number_of_beds` as beds, `t`.`apartment_type` as type, `nor`.`number_of_rooms` as rooms, " +
            "`a`.`balcony` as balcony, `a`.`price` as price, `a`.`status` as status from aparment a " +
            "inner join `number_of_beds` `nob` on `a`.`number_of_beds_id` = `nob`.`id` " +
            "inner join `apartment_type` `t` on `a`.`apartment_type_id` = `t`.`id` " +
            "inner join `number_of_rooms` `nor` on `a`.`number_of_rooms_id` = `nor`.`number_of_rooms`";

    private static final String SAVE_APARTMENT = "insert into aparment(floor, room_number, number_of_beds_id, apartment_type_id, " +
            "balcony, number_of_rooms_id, price) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_APARTMENT_TYPE = "update `apartment_type` set  `apartment_type`.`apartment_type`=? " +
            "where `apartment_type`.`id`=?";

    private static final String UPDATE_NUMBER_OF_ROOMS = "update `number_of_rooms` set  `number_of_rooms`.`number_of_rooms`=? " +
            "where `number_of_rooms`.`id`=?";

    private static final String UPDATE_NUMBER_OF_BEDS = "update `number_of_beds` set  `number_of_beds`.`number_of_beds`=? " +
            "where `number_of_beds`.`id`=?";

    private static final String SAVE_APARTMENT_TYPE = "insert into apartment_type(apartment_type) " +
            "values (?)";

    private static final String SAVE_NUMBER_OF_ROOMS = "insert into aparment(floor, room_number, number_of_beds_id, apartment_type_id, " +
            "balcony, number_of_rooms_id, price) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SAVE_NUMBER_OF_BEDS = "insert into aparment(floor, room_number, number_of_beds_id, apartment_type_id, " +
            "balcony, number_of_rooms_id, price) values (?, ?, ?, ?, ?, ?, ?)";

    private static final Logger log = Logger.getLogger(ApartmentDaoImpl.class);


    @Override
    public boolean saveApartmentType(String type) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean status = false;

        try {

            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(SAVE_APARTMENT_TYPE);
            preparedStatement.setString(1, type);
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
    public boolean saveNumberOfBeds(int nob) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean status = false;

        try {

            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(SAVE_NUMBER_OF_BEDS);
            preparedStatement.setInt(1, nob);
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
    public boolean saveNumberOfRooms(int nor) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean status = false;

        try {

            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(SAVE_NUMBER_OF_ROOMS);
            preparedStatement.setInt(1, nor);
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
    public List<ApartmentType> findAllApartmentTypes() throws DAOException {
        List<ApartmentType> apartmentTypeList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_APARTMENT_TYPES);
            while (resultSet.next()) {
                ApartmentType apartmentType =
                        new ApartmentType(resultSet.getInt(1), resultSet.getString(2));
                apartmentTypeList.add(apartmentType);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, statement, resultSet);
        }
        return apartmentTypeList;
    }

    @Override
    public List<NumberOfBeds> findAllNumberOfBeds() throws DAOException {
        List<NumberOfBeds> numberOfBedsList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_NUMBERS_OF_BEDS);
            while (resultSet.next()) {
                NumberOfBeds numberOfBeds =
                        new NumberOfBeds(resultSet.getInt(1), resultSet.getInt(2));
                numberOfBedsList.add(numberOfBeds);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, statement, resultSet);
        }
        return numberOfBedsList;
    }

    @Override
    public List<NumberOfRooms> findAllNumberOfRooms() throws DAOException {

        List<NumberOfRooms> numberOfRoomsList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_NUMBER_OF_ROOMS);
            while (resultSet.next()) {
                NumberOfRooms numberOfRooms =
                        new NumberOfRooms(resultSet.getInt(1), resultSet.getInt(2));
                numberOfRoomsList.add(numberOfRooms);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, statement, resultSet);
        }
        return numberOfRoomsList;
    }

    @Override
    public List<Apartment> findAllApartments() throws DAOException {
        List<Apartment> apartmentList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_APARTMENTS);
            while (resultSet.next()) {
                Apartment apartment =
                        new Apartment(resultSet.getInt(ID_COLUMN), resultSet.getInt(FLOOR_COLUMN),
                                resultSet.getInt(ROOM_NUMBER_COLUMN), resultSet.getString(APARTMENT_TYPE_COLUMN),
                                resultSet.getBoolean(BALCONY_COLUMN), resultSet.getInt(NUMBER_OF_ROOMS_COLUMN),
                                resultSet.getInt(NUMBER_OF_BEDS_COLUMN), resultSet.getDouble(PRICE_COLUMN),
                                resultSet.getString(STATUS_COLUMN));
                apartmentList.add(apartment);
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, statement, resultSet);
        }
        return apartmentList;
    }

    @Override
    public boolean saveApartment(Apartment apartment, ApartmentType apartmentType, NumberOfBeds numberOfBeds,
                                 NumberOfRooms numberOfRooms) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean status = false;

        try {

            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(FIND_NUMBER_OF_BEDS_BY_ID);
            preparedStatement.setInt(1, numberOfBeds.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ConnectionPool.getInstance().closeConnection(preparedStatement, resultSet);

                preparedStatement = connection.prepareStatement(FIND_NUMBER_OF_ROOMS_BY_ID);
                preparedStatement.setInt(1, numberOfRooms.getId());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    ConnectionPool.getInstance().closeConnection(preparedStatement, resultSet);

                    preparedStatement = connection.prepareStatement(FIND_APARTMENT_TYPE_BY_ID);
                    preparedStatement.setInt(1, apartmentType.getId());
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        ConnectionPool.getInstance().closeConnection(preparedStatement, resultSet);

                        preparedStatement = connection.prepareStatement(SAVE_APARTMENT);
                        preparedStatement.setInt(1, apartment.getFloor());
                        preparedStatement.setInt(2, apartment.getRoomNumber());
                        preparedStatement.setInt(3, numberOfBeds.getId());
                        preparedStatement.setInt(4, apartmentType.getId());
                        preparedStatement.setBoolean(5, apartment.isBalcony());
                        preparedStatement.setInt(6, numberOfRooms.getId());
                        preparedStatement.setDouble(7, apartment.getPrice());
                        int count = preparedStatement.executeUpdate();
                        if (count > 0) {
                            status = true;
                        }
                    }
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return status;
    }

    @Override
    public boolean updateApartmentType(ApartmentType apartmentType) throws DAOException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_APARTMENT_TYPE);
            preparedStatement.setInt(2, apartmentType.getId());
            preparedStatement.setString(1, apartmentType.getType());
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
    public boolean updateNumberOfRooms(NumberOfRooms numberOfRooms) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_NUMBER_OF_ROOMS);
            preparedStatement.setInt(2, numberOfRooms.getId());
            preparedStatement.setInt(1, numberOfRooms.getRooms());
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
    public boolean updateNumberOfBeds(NumberOfBeds numberOfBeds) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_NUMBER_OF_BEDS);
            preparedStatement.setInt(2, numberOfBeds.getId());
            preparedStatement.setInt(1, numberOfBeds.getBeds());
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                status = true;
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement);
        }
        return status;
    }

    @Override
    public ApartmentType findApartmentTypeById(int id) throws DAOException {
        ApartmentType apartmentType = new ApartmentType();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(FIND_APARTMENT_TYPE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                apartmentType.setId(resultSet.getInt(1));
                apartmentType.setType(resultSet.getString(2));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return apartmentType;
    }

    @Override
    public NumberOfRooms findNumberOfRoomsById(int id) throws DAOException {
        NumberOfRooms numberOfRooms = new NumberOfRooms();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(FIND_NUMBER_OF_ROOMS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfRooms.setId(resultSet.getInt(1));
                numberOfRooms.setRooms(resultSet.getInt(2));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return numberOfRooms;
    }

    @Override
    public NumberOfBeds findNumberOfBedsById(int id) throws DAOException {
        final String FIND_NUMBER_OF_BEDS_BY_ID = "select * from `apartment_type` where `apartment_type`.`id`=?";
        NumberOfBeds numberOfBeds = new NumberOfBeds();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(FIND_NUMBER_OF_BEDS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfBeds.setId(resultSet.getInt(1));
                numberOfBeds.setBeds(resultSet.getInt(2));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return numberOfBeds;
    }

    @Override
    public Apartment findApartmentById(int id) throws DAOException {
        Apartment apartment = new Apartment();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(FIND_APARTMENT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                apartment =
                        new Apartment(resultSet.getInt(ID_COLUMN), resultSet.getInt(FLOOR_COLUMN),
                                resultSet.getInt(ROOM_NUMBER_COLUMN), resultSet.getString(APARTMENT_TYPE_COLUMN),
                                resultSet.getBoolean(BALCONY_COLUMN), resultSet.getInt(NUMBER_OF_ROOMS_COLUMN),
                                resultSet.getInt(NUMBER_OF_BEDS_COLUMN), resultSet.getDouble(PRICE_COLUMN));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return apartment;
    }

    @Override
    public Apartment findApartmentByRoomNumber(int roomNumber) throws DAOException {
        Apartment apartment = new Apartment();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(FIND_APARTMENT_BY_ROOM_NUMBER);
            preparedStatement.setInt(1, roomNumber);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                apartment =
                        new Apartment(resultSet.getInt(ID_COLUMN), resultSet.getInt(FLOOR_COLUMN),
                                resultSet.getInt(ROOM_NUMBER_COLUMN), resultSet.getString(APARTMENT_TYPE_COLUMN),
                                resultSet.getBoolean(BALCONY_COLUMN), resultSet.getInt(NUMBER_OF_ROOMS_COLUMN),
                                resultSet.getInt(NUMBER_OF_BEDS_COLUMN), resultSet.getDouble(PRICE_COLUMN));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return apartment;
    }

    @Override
    public boolean updateApartment(Apartment apartment, ApartmentType apartmentType, NumberOfBeds numberOfBeds,
                                   NumberOfRooms numberOfRooms) throws DAOException {

        final String STATUS = "deleted";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {

            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_APARTMENT);
            preparedStatement.setString(1, STATUS);
            preparedStatement.setInt(2, apartment.getId());
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                if (saveApartment(apartment, apartmentType, numberOfBeds, numberOfRooms)) {
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
    public boolean deactivateApartment(int id) throws DAOException {
        final String STATUS = "deleted";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean status = false;

        try {

            connection = ConnectionPool.getInstance().takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_APARTMENT);
            preparedStatement.setString(1, STATUS);
            preparedStatement.setInt(2, id);
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
}
