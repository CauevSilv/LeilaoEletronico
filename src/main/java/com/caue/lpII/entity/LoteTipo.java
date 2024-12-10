package com.caue.lpII.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "LOTE_TIPO")
public class LoteTipo {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_LOTE")
    private Integer idLote;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_VEICULO")
    private Veiculo idVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_DISPOSITIVO")
    private Dispositivo idDispositivo;

}