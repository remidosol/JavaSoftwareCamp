package com.homework4.business.abstracts;

import com.homework4.entities.concretes.Campaign;
import com.homework4.entities.concretes.Game;
import com.homework4.entities.concretes.Gamer;

public interface SellService {

    void buyGame(Gamer gamer, Game game);
    void buyGameWithCampaign(Gamer gamer, Game game, Campaign campaign);

    void sellGame(Gamer gamer, Game game);
    void sellGameWithCampaign(Gamer gamer, Game game, Campaign campaign);
}
