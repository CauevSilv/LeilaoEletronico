package com.fatec.leilao.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InfoDevices extends AuctionItem{

    private String deviceName;
    private String deviceType;
    private String deviceDescription;
}
