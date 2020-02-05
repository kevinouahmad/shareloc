package main.webapp.model.dao;

import main.webapp.model.Address;
import main.webapp.model.Colocation;

import java.util.Collection;

public interface ColocationDao {
    public void persist(Colocation col);
    public void remove(Colocation col);
    public void update(Colocation col);
    public Colocation find(int id);
    public Collection<Colocation> findAll();
}
