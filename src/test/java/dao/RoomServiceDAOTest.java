package dao;

import by.jwd.lemesheuski.hostel.bean.RoomService;
import by.jwd.lemesheuski.hostel.bean.User;
import by.jwd.lemesheuski.hostel.dao.DAOException;
import by.jwd.lemesheuski.hostel.dao.DAOProvider;
import by.jwd.lemesheuski.hostel.dao.RoomServiceDao;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class RoomServiceDAOTest {

    private static RoomServiceDao roomServiceDao;

    @BeforeClass
    public static void initBeforeTests() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolDataForTest();
        roomServiceDao = DAOProvider.getInstance().getRoomServiceDao();
    }

    @Test(timeout = 10000)
    public void saveRoomServiceTest() throws DAOException {
        boolean actual = roomServiceDao.saveRoomService("Завтрак в номер", 20.0);
        Assert.assertFalse(actual);
    }

    @Test(timeout = 10000)
    public void findAllRoomServicesTest() throws DAOException {
        List<RoomService> roomServiceList;
        roomServiceList = roomServiceDao.findAllRoomServices();
        int actual = roomServiceList.size();
        int expected = 2;
        Assert.assertEquals(actual, expected);
    }

    @AfterClass
    public static void doAfterTests() {
        ConnectionPool.getInstance().dispose();
    }
}
