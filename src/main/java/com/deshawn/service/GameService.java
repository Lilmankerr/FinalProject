package com.deshawn.service;

import com.deshawn.entity.Game;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class GameService {
    @PersistenceContext(unitName = "gamePersistenceUnit")
    private EntityManager manager;

    public List<Game> getAll() {
        return manager.createNamedQuery("findAllGames", Game.class).getResultList();
    }


    public Game findById(long id) {
        return manager.find(Game.class, id);
    }

    @Transactional
    public void update(Game game) {
        manager.getTransaction().begin();
        manager.merge(game);
        manager.getTransaction().commit();
    }

    @Transactional
    public void create(Game game) {
        manager.getTransaction().begin();
        manager.persist(game);
        manager.getTransaction().commit();
    }

    @Transactional
    public void delete(Game game) {
        manager.getTransaction().begin();
        if (!manager.contains(game)) {
            game = manager.merge(game);
        }

        manager.remove(game);
        manager.getTransaction().commit();
    }
}
