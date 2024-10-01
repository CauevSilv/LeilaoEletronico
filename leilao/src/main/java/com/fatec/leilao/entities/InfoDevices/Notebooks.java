package com.fatec.leilao.entities.InfoDevices;

import com.fatec.leilao.controller.AuctionController;
import com.fatec.leilao.services.AuctionService;

public class Notebooks extends AuctionController {
    public Notebooks(AuctionService auctionService) {
        super(auctionService);
    }
}
