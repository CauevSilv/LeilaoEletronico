package com.fatec.leilao.entities.InfoDevices;

import com.fatec.leilao.controller.AuctionController;
import com.fatec.leilao.services.AuctionService;

public class Hub extends AuctionController {
    public Hub(AuctionService auctionService) {
        super(auctionService);
    }
}
