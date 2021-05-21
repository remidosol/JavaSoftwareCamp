package com.homework4.dataAccess.concretes;

import com.homework4.dataAccess.abstracts.CampaignDao;
import com.homework4.entities.concretes.Campaign;

import java.util.List;

public class HibernateCampaignDao implements CampaignDao {
    @Override
    public void create(Campaign campaign) {
        System.out.println(campaign.getName() + " has been created by Hibernate.");
    }

    @Override
    public void update(Campaign campaign, int id, String name, double discount) {
        campaign.setId(id);
        campaign.setName(name);
        campaign.setDiscount(discount);

        System.out.println(campaign.getName() + " has been updated by Hibernate.");
    }

    @Override
    public void delete(Campaign campaign) {
        System.out.println(campaign.getName() + " has been deleted by Hibernate.");
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
