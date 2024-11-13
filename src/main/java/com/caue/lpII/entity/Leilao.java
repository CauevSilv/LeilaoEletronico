package com.caue.lpII.entity;

import com.caue.lpII.entity.dto.LeilaoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Leilao {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLeilao;
    private LocalDate dataOcorrencia;
    private LocalDate dataVisitacao;
    private String local;
    private String endereco;
    private String cidade;
    private String estado;
    private String status; // EM ABERTO, EM ANDAMENTO, FINALIZADO

  public Leilao(LocalDate dataOcorrencia, LocalDate dataVisitacao, String local, String endereco, String cidade, String estado, String status) {
    this.dataOcorrencia = dataOcorrencia;
    this.dataVisitacao = dataVisitacao;
    this.local = local;
    this.endereco = endereco;
    this.cidade = cidade;
    this.estado = estado;
    this.status = status;
  }

    public LeilaoDTO toDTO() {
        return new LeilaoDTO(dataOcorrencia, dataVisitacao, local, endereco, cidade, estado, status);
    }
}
