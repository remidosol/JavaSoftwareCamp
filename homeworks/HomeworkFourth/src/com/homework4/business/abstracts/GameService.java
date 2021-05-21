package com.homework4.business.abstracts;

import com.homework4.entities.concretes.Game;

import java.util.List;

public interface GameService {

    void create(Game game);
    void update(Game game, int id, String name, double unitPrice, int unitsInStock);
    void delete(Game game);
    Game get(int id);

    List<Game> getAll();
}
