package com.caue.lpII.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LOTE_TIPO")
public class LoteTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_LOTE")
    private Integer idLote;

    @Column(name = "ID_VEICULO")
    private Integer idVeiculo;

    @Column(name = "ID_DISPOSITIVO")
    private Integer idDispositivo;

}