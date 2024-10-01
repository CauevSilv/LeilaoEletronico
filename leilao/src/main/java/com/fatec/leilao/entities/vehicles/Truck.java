package com.fatec.leilao.entities.vehicles;

import com.fatec.leilao.controller.AuctionController;
import com.fatec.leilao.services.AuctionService;

public class Truck extends AuctionController {
    public Truck(AuctionService auctionService) {
        super(auctionService);
    }
}
