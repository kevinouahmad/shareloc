package main.webapp.model.dao;

import main.webapp.model.AchievedService;
import main.webapp.model.Address;
import main.webapp.model.Colocation;
import main.webapp.model.User;

import java.util.Collection;

public interface AchievedServiceDao {
    public void persist(AchievedService service);
    public void remove(AchievedService service);
    public void update(AchievedService service);
    public AchievedService find(int id);
    public Collection<AchievedService> find(Colocation col);
    public Collection<AchievedService> findAll();
}
