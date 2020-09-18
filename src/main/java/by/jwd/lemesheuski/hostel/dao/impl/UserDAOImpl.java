package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.bean.Apartment;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

import java.sql.*;
import java.time.Period;

public class UserDAOImpl implements UserDAO {

    private static final String USER_SURNAME_COLUMN_LABEL = "surname";
    private static final String USER_NAME_COLUMN_LABEL = "name";
    private static final String USER_PATRONYMIC_COLUMN_LABEL = "patronymic";
    private static final String USER_LOGIN_COLUMN_LABEL = "login";
    private static final String USER_DISCOUNT_COLUMN_LABEL = "discount";
    private static final String USER_MONEY_SPENT_COLUMN_LABEL = "money_spent";

    private static final String FIND_ROLE_BY_LOGIN_AND_PASSWORD = "select `role`.`role_name` as `role` from `role` inner join `user` " +
            "on `role`.`id` = `user`.`role_id` " +
            "where `user`.`login` = ? and `user`.`password` = ?";
    private static final String ADD_USER = "insert into `user`(`login`, `password`, `surname`, `name`, `patronymic`, `role_id`) values " +
            "(?, ?, ?, ?, ?, ?)";
    private static final String FIND_ROLE_BY_ROLE_NAME = "select `role`.`id` as `id` from `role` where `role`.`role_name` = ? limit 1";
    private static final String FIND_USER_BY_LOGIN = "select `id`, `surname`, `name`, `patronymic`, `login`, `discount`, `money_spent` from `user` " +
            "where `user`.`login` = ?";
    private static final String FIND_USER_BY_ID = "select `id`, `surname`, `name`, `patronymic`, `login`, `discount`, `money_spent` from `user` " +
            "where `user`.`id` = ?";
    private static final String UPDATE_USER_PASSWORD_BY_LOGIN = "update `user` `u` set `u`.`password`=? where `u`.`login`=?";
    private static final String UPDATE_USER_DISCOUNT_BY_LOGIN = "update `user` `u` set `u`.`discount`=? where `u`.`login`=?";
    private static final String UPDATE_USER_DISCOUNT_BY_ID = "update `user` `u` set `u`.`discount`=? " +
            "where `u`.`id`=?";
    private static final String UPDATE_USER_MONEY_SPENT_BY_ID = "update `user` `u` set `u`.`money_spent`=? " +
            "where `u`.`id`=?";

    @Override
    public String findRoleByLoginAndPassword(String login, String password) throws DAOException {
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

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            DAOUtil.finallyClose(connection, preparedStatement, resultSet);
        }
        return role;
    }

    @Override
    public int addUser(String login, String password, String role, String surname, String name, String patronymic) throws DAOException {
        final String ROLE_ID_COLUMN_LABEL = "id";
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

                if (!roleId.equals("0") && findUserByLogin(login) == null) {
                    preparedStatement = connection.prepareStatement(ADD_USER);
                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, surname);
                    preparedStatement.setString(4, name);
                    preparedStatement.setString(5, patronymic);
                    preparedStatement.setString(6, roleId);
                    count = preparedStatement.executeUpdate();

                }
            } catch(SQLException | ConnectionPoolException e){
                throw new DAOException(e);
            } finally{
                DAOUtil.finallyClose(connection, preparedStatement, resultSet);
            }
            return count;
        }

        public User findUserByLogin (String login) throws DAOException {

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
                    user = userFill(resultSet);
                }
            } catch (SQLException | ConnectionPoolException e) {
                throw new DAOException(e);
            } finally {
                DAOUtil.finallyClose(connection, preparedStatement, resultSet);
            }
            return user;
        }

        public User findUserById ( int id) throws DAOException {

            User user = null;
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = ConnectionPool.getInstance().takeConnection();
                preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = userFill(resultSet);
                }

            } catch (SQLException | ConnectionPoolException e) {
                throw new DAOException(e);
            } finally {
                DAOUtil.finallyClose(connection, preparedStatement, resultSet);
            }
            return user;
        }

        @Override
        public boolean updateUserPasswordByLogin (String login, String password) throws DAOException {
            boolean status = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = ConnectionPool.getInstance().takeConnection();
                preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_LOGIN);

                preparedStatement.setString(1, password);
                preparedStatement.setString(2, login);
                int count = preparedStatement.executeUpdate();
                if (count != 0) {
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
        public boolean updateUserDiscountByLogin (String login) throws DAOException {
            boolean status = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            User user = findUserByLogin(login);

            try {
                connection = ConnectionPool.getInstance().takeConnection();

                int discount = calcDiscount(user.getMoneySpent());

                preparedStatement = connection.prepareStatement(UPDATE_USER_DISCOUNT_BY_LOGIN);

                preparedStatement.setInt(1, discount);
                preparedStatement.setString(2, login);
                int count = preparedStatement.executeUpdate();
                if (count != 0) {
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
        public boolean updateUserDiscountById ( int id) throws DAOException {
            boolean status = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            User user = findUserById(id);

            try {
                connection = ConnectionPool.getInstance().takeConnection();

                int discount = calcDiscount(user.getMoneySpent());

                preparedStatement = connection.prepareStatement(UPDATE_USER_DISCOUNT_BY_ID);

                preparedStatement.setInt(1, discount);
                preparedStatement.setInt(2, id);
                int count = preparedStatement.executeUpdate();
                if (count != 0) {
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
        public boolean updateUserMoneySpentById ( int id, double money) throws DAOException {
            boolean status = false;

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = ConnectionPool.getInstance().takeConnection();

                preparedStatement = connection.prepareStatement(UPDATE_USER_MONEY_SPENT_BY_ID);

                preparedStatement.setDouble(1, money);
                preparedStatement.setInt(2, id);
                int count = preparedStatement.executeUpdate();
                if (count != 0) {
                    status = true;
                }
            } catch (SQLException | ConnectionPoolException e) {
                throw new DAOException(e);
            } finally {
                DAOUtil.finallyClose(connection, preparedStatement);
            }
            return status;
        }

        private int calcDiscount ( double moneySpent){
            int discount = (int) moneySpent / 1000;
            return Math.min(discount, 5);
        }

        private User userFill (ResultSet resultSet) throws SQLException {
            User user = new User();

            user.setId(resultSet.getInt(1));
            user.setSurname(resultSet.getString(USER_SURNAME_COLUMN_LABEL));
            user.setName(resultSet.getString(USER_NAME_COLUMN_LABEL));
            user.setPatronymic(resultSet.getString(USER_PATRONYMIC_COLUMN_LABEL));
            user.setLogin(resultSet.getString(USER_LOGIN_COLUMN_LABEL));
            user.setDiscount(resultSet.getInt(USER_DISCOUNT_COLUMN_LABEL));
            user.setMoneySpent(resultSet.getDouble(USER_MONEY_SPENT_COLUMN_LABEL));

            return user;
        }
    }
