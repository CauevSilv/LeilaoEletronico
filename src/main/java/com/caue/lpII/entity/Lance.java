package com.caue.lpII.entity;

import com.caue.lpII.entity.dto.LanceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLance;
    private Double valor;
    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente; // Relação com a entidade Cliente
    @ManyToOne(targetEntity = Lote.class)
    private Lote lote; // Relação com a entidade Lote

    public Lance(Double valor, Cliente cliente, Lote lote) {
        this.valor = valor;
        this.cliente = cliente;
        this.lote = lote;
    }

    public Lance(Double valor) {
        this.valor = valor;
    }

    public LanceDTO toDTO() {
        LanceDTO lanceDTO = new LanceDTO();
        lanceDTO.setValor(this.valor);
        lanceDTO.setCliente(this.cliente.toDTO());
        lanceDTO.setLote(this.lote.toDTO());
        return lanceDTO;
    }
}
