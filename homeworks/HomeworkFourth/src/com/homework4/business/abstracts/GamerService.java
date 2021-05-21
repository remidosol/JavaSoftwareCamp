package com.homework4.business.abstracts;

import com.homework4.entities.concretes.Gamer;

import java.util.List;

public interface GamerService {

    void create(Gamer gamer);
    void update(Gamer gamer);
    void delete(Gamer gamer);
    Gamer get(int id);
    List<Gamer> getAll();
}
