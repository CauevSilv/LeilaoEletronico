package com.caue.lpII.entity;

import com.caue.lpII.entity.dto.ClienteDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente(String nome, String cpf, String email, String telefone) {
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.telefone = telefone;
    }

    public ClienteDTO toDTO() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(this.nome);
        clienteDTO.setCpf(this.cpf);
        clienteDTO.setEmail(this.email);
        clienteDTO.setTelefone(this.telefone);
        return clienteDTO;
    }
}

