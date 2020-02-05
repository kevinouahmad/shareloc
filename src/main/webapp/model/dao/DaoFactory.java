package main.webapp.model.dao;

import main.webapp.model.AchievedService;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
    Connection getConnection() throws SQLException;
    public UserDao getUserDao();
    public ColocationDao getColocationDao();
    public AddressDao getAddressDao();
    public ServiceDao getServiceDao();
    public AchievedServiceDao getAchievedServiceDao();
}