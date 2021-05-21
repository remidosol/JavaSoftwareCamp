package com.homework4.dataAccess.abstracts;

import com.homework4.entities.concretes.Game;
import com.homework4.entities.concretes.Gamer;

import java.util.List;

public interface GamerDao{

    void create(Gamer gamer);
    void update(Gamer gamer);
    void delete(Gamer gamer);
    Gamer get(int id);
    List<Gamer> getAll();
}
