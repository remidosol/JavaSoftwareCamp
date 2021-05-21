package com.homework4.business.concretes;

import com.homework4.business.abstracts.GameService;
import com.homework4.entities.concretes.Game;

import java.util.List;

public class GameManager implements GameService {
    @Override
    public void create(Game game) {

    }

    @Override
    public void update(Game game, int id, String name, double unitPrice, int unitsInStock) {

    }

    @Override
    public void delete(Game game) {

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
