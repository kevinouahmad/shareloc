package main.webapp.dbms;

import main.webapp.model.Colocation;
import main.webapp.model.Service;
import main.webapp.model.User;
import main.webapp.model.dao.DaoFactory;
import main.webapp.model.dao.ServiceDao;

import java.util.Collection;

public class ServiceDaoMem implements ServiceDao {
    // not implemented yet

    private DaoFactory daoFactory;

    public ServiceDaoMem(DaoFactoryImpl daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void persist(Service service) {

    }

    @Override
    public void remove(Service service) {

    }

    @Override
    public void update(Service Service) {

    }

    @Override
    public void achieve(User from, User to) {

    }

    @Override
    public Service find(int id) {
        return null;
    }

    @Override
    public Collection<Service> find(Colocation col) {
        return null;
    }

    @Override
    public Collection<Service> findAll() {
        return null;
    }
}
