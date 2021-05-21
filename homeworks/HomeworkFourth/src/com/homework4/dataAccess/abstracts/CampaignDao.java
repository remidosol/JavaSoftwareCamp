package com.homework4.dataAccess.abstracts;


import com.homework4.entities.concretes.Campaign;

import java.util.List;

public interface CampaignDao {

    void create(Campaign campaign);
    void update(Campaign campaign, int id, String name, double discount);
    void delete(Campaign campaign);
    Campaign get(int id);

    List<Campaign> getAll();
}
