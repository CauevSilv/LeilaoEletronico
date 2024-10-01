package com.fatec.leilao.entities.InfoDevices;

import com.fatec.leilao.controller.AuctionController;
import com.fatec.leilao.services.AuctionService;

public class Monitor extends AuctionController {
    public Monitor(AuctionService auctionService) {
        super(auctionService);
    }
}
