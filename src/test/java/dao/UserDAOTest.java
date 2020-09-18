package dao;

import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.UserDAO;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDAOTest {

    private static UserDAO userDAO;

    @BeforeClass
    public static void initBeforeTests() throws ConnectionPoolException{
        ConnectionPool.getInstance().initPoolDataForTest();
        userDAO = DAOProvider.getInstance().getUserDAO();
    }

    @Test(timeout = 10000)
    public void addUserTest() throws DAOException {
        int expected = 0;
        int actual = userDAO.addUser("Void", "qwerty123", "ROLE_ADMIN",
                "Lem", "Art", "Val");
        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 10000)
    public void findUserByLoginOrIdTest() throws DAOException {
        User expected = userDAO.findUserByLogin("Void");
        User actual = userDAO.findUserById(1);
        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 10000)
    public void findUserRoleTestAdmin() throws DAOException {
        String expected = "ROLE_ADMIN";
        String actual = userDAO.findRoleByLoginAndPassword("Void", "qwerty123");
        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 10000)
    public void findUserRoleTestUser() throws DAOException {
        String expected = "ROLE_USER";
        String actual = userDAO.findRoleByLoginAndPassword("Void123", "qwerty123");
        Assert.assertEquals(expected, actual);
    }

    @AfterClass
    public static void doAfterTests(){
        ConnectionPool.getInstance().dispose();
    }
}
