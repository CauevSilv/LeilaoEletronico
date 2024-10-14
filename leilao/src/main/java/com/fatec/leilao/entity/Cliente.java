package com.fatec.leilao.entity;

import com.fatec.leilao.dto.ClienteDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}

