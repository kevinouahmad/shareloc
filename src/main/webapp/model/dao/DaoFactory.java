package main.webapp.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
    Connection getConnection() throws SQLException;
    public UserDao getUserDao();
}