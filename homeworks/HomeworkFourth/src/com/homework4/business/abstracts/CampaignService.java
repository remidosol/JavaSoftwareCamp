package com.homework4.business.abstracts;

import com.homework4.entities.concretes.Campaign;

import java.util.List;

public interface CampaignService {

    void create(Campaign campaign);
    void update(Campaign campaign, int id, String name, double discount);
    void delete(Campaign campaign);
    Campaign get(int id);

    List<Campaign> getAll();
}
