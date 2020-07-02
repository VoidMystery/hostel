package by.jwd.lemesheuski.hostel.dao.impl;

import by.jwd.lemesheuski.hostel.dao.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOUtil {

    private DAOUtil(){}

    static void finallyClose(Connection con, Statement st, ResultSet rs){
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

    public static void finallyClose(Connection con, PreparedStatement st) {
        if (st == null) {
            if (con != null) {
                ConnectionPool.getInstance().closeConnection(con);
            }
        } else {
            ConnectionPool.getInstance().closeConnection(con, st);
        }
    }
}
