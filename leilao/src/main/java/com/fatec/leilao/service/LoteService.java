package com.fatec.leilao.service;

import com.fatec.leilao.dto.LoteDTO;
import com.fatec.leilao.entity.Leilao;
import com.fatec.leilao.entity.Lote;
import com.fatec.leilao.repository.LeilaoRepository;
import com.fatec.leilao.repository.LoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final LeilaoRepository leilaoRepository;

    public LoteService(LoteRepository loteRepository, LeilaoRepository leilaoRepository) {
        this.loteRepository = loteRepository;
        this.leilaoRepository = leilaoRepository;
    }

    public LoteDTO registrarLote(LoteDTO loteDTO) {
        Optional<Leilao> leilaoOpt = leilaoRepository.findById(loteDTO.getLeilao().getId());
        if (leilaoOpt.isPresent()) {
            Lote lote = new Lote(loteDTO.getTipo(), loteDTO.getNome(), loteDTO.getDescricao(), loteDTO.getLanceInicial(), leilaoOpt.get());
            return loteRepository.save(lote).toDTO();
        }
        return null;
    }

    public List<LoteDTO> listarLotes() {
        return loteRepository.findAll().stream().map(Lote::toDTO).toList();
    }

    public LoteDTO atualizarLote(int idLote, LoteDTO loteDTO) {
        Optional<Lote> loteOpt = loteRepository.findById(idLote);
        if (loteOpt.isPresent()) {
            Lote lote = loteOpt.get();
            lote.setTipo(loteDTO.getTipo());
            lote.setNome(loteDTO.getNome());
            lote.setDescricao(loteDTO.getDescricao());
            lote.setLanceInicial(loteDTO.getLanceInicial());
            return loteRepository.save(lote).toDTO();
        }
        return null;
    }

    public void removerLote(int idLote) {
        loteRepository.deleteById(idLote);
    }
}

