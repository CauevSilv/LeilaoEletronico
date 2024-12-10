package com.caue.lpII.entity.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeilaoTipoDto implements Serializable {
    Integer id;
    Integer idLeilao;
    Integer idVeiculo;
    Integer idDispositivo;
}