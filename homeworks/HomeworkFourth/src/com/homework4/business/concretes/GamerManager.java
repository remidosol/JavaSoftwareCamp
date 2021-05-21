package com.homework4.business.concretes;

import com.homework4.business.abstracts.GamerService;
import com.homework4.core.ValidatorService;
import com.homework4.dataAccess.abstracts.GamerDao;
import com.homework4.entities.concretes.Gamer;

import java.util.List;

public class GamerManager implements GamerService {

    private GamerDao gamerDao;
    private ValidatorService validatorService;

    public GamerManager(GamerDao gamerDao, ValidatorService validatorService) {
        super();
        this.gamerDao = gamerDao;
        this.validatorService = validatorService;
    }

    @Override
    public void create(Gamer gamer) {
        if (gamer.getEmail().isEmpty()){
            System.out.println("You must provide an email.");
        }else if (!gamer.getEmail().matches("^(.+)@(.+)$"))
        {
            System.out.println("Please provide a valid email.");
        }else if (this.validatorService.validateUser(gamer.getNationalId(),
                gamer.getFullName(), gamer.getYearOfBirth()))
        {
            this.gamerDao.create(gamer);
        }else {
            System.out.println("The information that you gave is not valid!");
        }

    }

    @Override
    public void update(Gamer gamer) {

    }

    @Override
    public void delete(Gamer gamer) {

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
