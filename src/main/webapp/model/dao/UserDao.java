package main.webapp.model.dao;

import main.webapp.model.User;

import java.security.NoSuchProviderException;
import java.util.Collection;

public interface UserDao {
    public void persist(User user);
    public void remove(User user);
    public void update(User user);
    public void grantAdmin(User user);
    public User find(String mail);
    public Collection<User> findAll();
}