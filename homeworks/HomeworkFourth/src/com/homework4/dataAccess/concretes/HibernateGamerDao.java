package com.homework4.dataAccess.concretes;

import com.homework4.dataAccess.abstracts.GamerDao;
import com.homework4.entities.concretes.Gamer;

import java.util.List;

public class HibernateGamerDao implements GamerDao {

    @Override
    public void create(Gamer gamer) {
        System.out.println("Gamer has been created: " + gamer.getFullName());
    }

    @Override
    public void update(Gamer gamer) {
        System.out.println("Gamer has been updated: " + gamer.getFullName());
    }

    @Override
    public void delete(Gamer gamer) {
        System.out.println("Gamer has been deleted: " + gamer.getFullName());
    }

    @Override
    public Gamer get(int id) {
        return null;
    }

    @Override
    public List<Gamer> getAll() {
        return null;
    }
}
