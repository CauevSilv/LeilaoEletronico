package com.fatec.leilao.entities.vehicles;

import com.fatec.leilao.entities.AuctionItem;
import com.fatec.leilao.services.AuctionService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utility extends AuctionItem {

    private String model;
    private String color;
    private Integer numberOfSeats;


    public Utility(AuctionItem auctionItem, String model, String color, Integer numberOfSeats) {
        this.model = model;
        this.color = color;
        this.numberOfSeats = numberOfSeats;
    }

}
