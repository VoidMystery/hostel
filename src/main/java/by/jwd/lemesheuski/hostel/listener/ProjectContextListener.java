package by.jwd.lemesheuski.hostel.listener;

import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;
import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProjectContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            //log
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
