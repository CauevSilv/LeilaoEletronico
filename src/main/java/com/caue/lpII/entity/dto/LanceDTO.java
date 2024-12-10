package com.caue.lpII.entity.dto;

import com.caue.lpII.entity.Cliente;
import com.caue.lpII.entity.Lote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanceDTO {
    private Double valor;
    private ClienteDTO cliente;
    private LoteDTO lote;
}

