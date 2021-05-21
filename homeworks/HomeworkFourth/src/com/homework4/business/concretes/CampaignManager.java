package com.homework4.business.concretes;

import com.homework4.business.abstracts.CampaignService;
import com.homework4.entities.concretes.Campaign;

import java.util.List;

public class CampaignManager implements CampaignService {
    @Override
    public void create(Campaign campaign) {

    }

    @Override
    public void update(Campaign campaign, int id, String name, double discount) {

    }

    @Override
    public void delete(Campaign campaign) {

    }

    @Override
    public Campaign get(int id) {
        return null;
    }

    @Override
    public List<Campaign> getAll() {
        return null;
    }
}
