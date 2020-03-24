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

        String sql = "select `role`.`role_name` as `role` from `role` inner join `user` on `role`.`id` = `user`.`id` " +
                "where `user`.`login` = ? and `user`.`password` = ?";

        String role = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("role");
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }finally {
            finallyClose(connection, preparedStatement, resultSet);
        }
        return role;
    }

    @Override
    public int addUser(String login, String password, String role, String surname, String name, String patronymic) throws DAOException {
        String sql = "insert into `user`(`login`, `password`, `surname`, `name`, `partonymic`, `role_id`) values " +
                "(?, ?, ?, ?, ?, ?)";
        String roleSql = "select `role`.`id` as `id` from `role` where `role`.`role_name` = ? limit 1";
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(roleSql);
            preparedStatement.setString(1, role);
            resultSet = preparedStatement.executeQuery();
            String role_id = "0";
            if (resultSet.next()) {
                role_id = resultSet.getString("role");
            }
            resultSet.close();
            preparedStatement.close();

            if (!role_id.equals("0")) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, surname);
                preparedStatement.setString(4, name);
                preparedStatement.setString(5, patronymic);
                preparedStatement.setString(6, role_id);
                count = preparedStatement.executeUpdate();
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }finally {
            finallyClose(connection, preparedStatement, resultSet);
        }
        return count;
    }

    public User findUserByLogin(String login) throws DAOException {
        String sql = "select `surname`, `name`, `patronymic`, `login`, `discount` from `user` " +
                "where `user`.`login` = ?";
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setLogin(resultSet.getString("login"));
                user.setDiscount(resultSet.getString("discount"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            finallyClose(connection, preparedStatement, resultSet);
        }
        return user;
    }

    private void finallyClose(Connection con, Statement st, ResultSet rs){
        if (rs == null) {
            if (st == null) {
                if (con != null) {
                    ConnectionPool.getInstance().closeConnection(con);
                }
            } else {
                ConnectionPool.getInstance().closeConnection(con, st);
            }
        } else {
            ConnectionPool.getInstance().closeConnection(con, st, rs);
        }
    }
}
