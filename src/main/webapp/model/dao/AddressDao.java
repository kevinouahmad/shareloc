package main.webapp.model.dao;

import main.webapp.model.Address;
import main.webapp.model.Colocation;
import main.webapp.model.Service;
import main.webapp.model.User;

import java.util.Collection;

public interface AddressDao {
    public void persist(Address address);
    public void remove(Address address);
    public void update(Address address);
    public Address find(int id);
    public Collection<Address> findByZipCode(int zipCode);
    public Collection<Address> findAll();
}
