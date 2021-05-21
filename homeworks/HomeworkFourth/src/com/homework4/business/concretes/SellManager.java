package com.homework4.business.concretes;

import com.homework4.business.abstracts.SellService;
import com.homework4.entities.concretes.Campaign;
import com.homework4.entities.concretes.Game;
import com.homework4.entities.concretes.Gamer;

public class SellManager implements SellService {
    @Override
    public void buyGame(Gamer gamer, Game game) {
        System.out.println(game.getName() + " has been bought by " + gamer.getFullName());
    }

    @Override
    public void buyGameWithCampaign(Gamer gamer, Game game, Campaign campaign) {
        double priceWithDiscount = game.getUnitPrice() - (game.getUnitPrice() * campaign.getDiscount() / 100);

        System.out.println("The game named as " + game.getName() + " has been bought for $" + priceWithDiscount + " with " + campaign.getName() + " campaign by "
                + gamer.getFullName());
    }

    @Override
    public void sellGame(Gamer gamer, Game game) {
        System.out.println(game.getName() + " has been sold by " + gamer.getFullName());
    }

    @Override
    public void sellGameWithCampaign(Gamer gamer, Game game, Campaign campaign) {
        double priceWithDiscount = game.getUnitPrice() - (game.getUnitPrice() * campaign.getDiscount() / 100);

        System.out.println("The game named as " + game.getName() + " has been sold for $" + priceWithDiscount + " with " + campaign.getName() + " campaign by "
                + gamer.getFullName());
    }
}
