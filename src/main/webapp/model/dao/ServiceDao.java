package main.webapp.model.dao;

import main.webapp.model.Colocation;
import main.webapp.model.Service;
import main.webapp.model.User;

import java.util.Collection;

public interface ServiceDao {
    public void persist(Service service);
    public void remove(Service service);
    public void update(Service Service);
    public void achieve(User from, User to);
    public Service find(int id);
    public Collection<Service> find(Colocation col);
    public Collection<Service> findAll();
}
