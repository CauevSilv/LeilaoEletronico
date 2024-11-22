package com.caue.lpII.entity.dto;

import com.caue.lpII.entity.Leilao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteDTO {
    private Integer id;
    private String tipo;
    private String nome;
    private String descricao;
    private Double lanceInicial;
    private LeilaoDTO leilaoDTO;
}
