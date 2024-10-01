package com.fatec.leilao.entities.vehicles.dto;

import com.fatec.leilao.entities.vehicles.Utility;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
public class UtilityDto implements Serializable {
    Long id;
    String name;
    String description;
    double startingPrice;
    LocalDateTime auctionEndTime;
    String model;
    String color;
    Integer numberOfSeats;
}