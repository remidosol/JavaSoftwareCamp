package com.homework4.dataAccess.concretes;

import com.homework4.dataAccess.abstracts.GameDao;
import com.homework4.entities.concretes.Game;

import java.util.List;

public class HibernateGameDao implements GameDao {
    @Override
    public void create(Game game) {
        System.out.println(game.getName() + " has been created by Hibernate.");
    }

    @Override
    public void update(Game game, int id, String name, double unitPrice, int unitsInStock) {
        game.setId(id);
        game.setName(name);
        game.setUnitPrice(unitPrice);
        game.setUnitsInStock(unitsInStock);

        System.out.println(game.getName() + " has been updated by Hibernate.");
    }

    @Override
    public void delete(Game game) {
        System.out.println(game.getName() + " has been deleted by Hibernate.");
    }

    @Override
    public Game get(int id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }
}
