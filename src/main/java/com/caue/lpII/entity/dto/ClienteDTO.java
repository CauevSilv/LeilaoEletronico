package com.caue.lpII.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
