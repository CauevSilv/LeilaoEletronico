package com.fatec.leilao.entities.vehicles;

import com.fatec.leilao.entities.AuctionItem;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends AuctionItem {

    private String model;
    private Integer year;
    private String brand;

}
