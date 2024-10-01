package com.fatec.leilao.entities.vehicles;

import com.fatec.leilao.controller.AuctionController;
import com.fatec.leilao.services.AuctionService;

public class Motorcicles extends AuctionController {
    public Motorcicles(AuctionService auctionService) {
        super(auctionService);
    }
}
