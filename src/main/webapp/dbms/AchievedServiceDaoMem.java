package main.webapp.dbms;

import main.webapp.model.AchievedService;
import main.webapp.model.Colocation;
import main.webapp.model.dao.AchievedServiceDao;
import main.webapp.model.dao.DaoFactory;

import java.util.Collection;

public class AchievedServiceDaoMem implements AchievedServiceDao {
    private DaoFactory daoFactory;

    public AchievedServiceDaoMem(DaoFactoryImpl daoFactory) {
        this.daoFactory = daoFactory;
    }

    // not implemented yet
    @Override
    public void persist(AchievedService service) {

    }

    @Override
    public void remove(AchievedService service) {

    }

    @Override
    public void update(AchievedService service) {

    }

    @Override
    public AchievedService find(int id) {
        return null;
    }

    @Override
    public Collection<AchievedService> find(Colocation col) {
        return null;
    }

    @Override
    public Collection<AchievedService> findAll() {
        return null;
    }
}
