package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public String findRoleByLoginAndPassword(String login, String password) throws DAOException {

        String sql = "select `role`.`role_name` as `role` from `role` inner join `user` on `role`.`id` = `user`.`id` " +
                "where `user`.`login` = ? and `user`.`password` = ?";

        String role = null;

        try {

            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("role");
            }

            ConnectionPool.getInstance().closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return role;
    }

    @Override
    public int addUser(String login, String password, String role, String surname, String name, String patronymic) throws DAOException {
        String sql = "insert into `user`(`login`, `password`, `surname`, `name`, `partonymic`, `role_id`) values " +
                "(?, ?, ?, ?, ?, ?)";
        String roleSql = "select `role`.`id` as `id` from `role` where `role`.`role_name` = ? limit 1";
        int count = 0;

        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(roleSql);
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            String role_id = "0";
            while (resultSet.next()) {
                role_id = resultSet.getString("role");
            }
            resultSet.close();
            preparedStatement.close();

            if(!role_id.equals("0")){
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
        }
        return count;
    }
}
