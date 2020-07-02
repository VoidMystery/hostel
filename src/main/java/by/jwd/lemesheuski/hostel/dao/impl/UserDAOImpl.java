package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public String findRoleByLoginAndPassword(String login, String password) throws DAOException {
        final String FIND_ROLE_BY_LOGIN_AND_PASSWORD = "select `role`.`role_name` as `role` from `role` inner join `user` " +
                "on `role`.`id` = `user`.`role_id` " +
                "where `user`.`login` = ? and `user`.`password` = ?";
        final String ROLE_COLUMN = "role";

        String role = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(FIND_ROLE_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString(ROLE_COLUMN);
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return role;
    }

    @Override
    public int addUser(String login, String password, String role, String surname, String name, String patronymic) throws DAOException {
        final String ADD_USER = "insert into `user`(`login`, `password`, `surname`, `name`, `patronymic`, `role_id`) values " +
                "(?, ?, ?, ?, ?, ?)";
        final String FIND_ROLE_BY_ROLE_NAME = "select `role`.`id` as `id` from `role` where `role`.`role_name` = ? limit 1";
        final String ROLE_ID_COLUMN_LABEL="id";
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(FIND_ROLE_BY_ROLE_NAME);
            preparedStatement.setString(1, role);
            resultSet = preparedStatement.executeQuery();
            String roleId = "0";
            if (resultSet.next()) {
                roleId = resultSet.getString(ROLE_ID_COLUMN_LABEL);
            }
            resultSet.close();
            preparedStatement.close();

            if (!roleId.equals("0")) {
                preparedStatement = connection.prepareStatement(ADD_USER);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, surname);
                preparedStatement.setString(4, name);
                preparedStatement.setString(5, patronymic);
                preparedStatement.setString(6, roleId);
                count = preparedStatement.executeUpdate();
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return count;
    }

    public User findUserByLogin(String login) throws DAOException {
        final String FIND_USER_BY_LOGIN = "select `id`, `surname`, `name`, `patronymic`, `login`, `discount` from `user` " +
                "where `user`.`login` = ?";
        final String USER_SURNAME_COLUMN_LABEL = "surname";
        final String USER_NAME_COLUMN_LABEL = "name";
        final String USER_PATRONYMIC_COLUMN_LABEL = "patronymic";
        final String USER_LOGIN_COLUMN_LABEL = "login";
        final String USER_DISCOUNT_COLUMN_LABEL = "discount";

        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setSurname(resultSet.getString(USER_SURNAME_COLUMN_LABEL));
                user.setName(resultSet.getString(USER_NAME_COLUMN_LABEL));
                user.setPatronymic(resultSet.getString(USER_PATRONYMIC_COLUMN_LABEL));
                user.setLogin(resultSet.getString(USER_LOGIN_COLUMN_LABEL));
                user.setDiscount(resultSet.getString(USER_DISCOUNT_COLUMN_LABEL));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return user;
    }

}
