package com.caue.lpII.service;
import com.caue.lpII.entity.dto.LeilaoDTO;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.repository.LeilaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;

    public LeilaoService(LeilaoRepository leilaoRepository) {
        this.leilaoRepository = leilaoRepository;
    }

    // Registro de um novo leilão
    public LeilaoDTO registrarLeilao(LeilaoDTO leilaoDTO) {
        Leilao leilao = new Leilao(leilaoDTO.getDataOcorrencia(), leilaoDTO.getDataVisitacao(),
                leilaoDTO.getLocal(), leilaoDTO.getEndereco(), leilaoDTO.getCidade(), leilaoDTO.getEstado(), "EM ABERTO");
        return leilaoRepository.save(leilao).toDTO();
    }

    // Consulta de todos os leilões
    public List<LeilaoDTO> listarLeiloes() {
        return leilaoRepository.findAll().stream().map(Leilao::toDTO).toList();
    }

    // Atualização de um leilão existente
    public LeilaoDTO atualizarLeilao(int idLeilao, LeilaoDTO leilaoDTO) {
        Optional<Leilao> leilaoOpt = leilaoRepository.findById(idLeilao);
        if (leilaoOpt.isPresent()) {
            Leilao leilao = leilaoOpt.get();
            leilao.setDataOcorrencia(leilaoDTO.getDataOcorrencia());
            leilao.setDataVisitacao(leilaoDTO.getDataVisitacao());
            leilao.setLocal(leilaoDTO.getLocal());
            leilao.setEndereco(leilaoDTO.getEndereco());
            leilao.setCidade(leilaoDTO.getCidade());
            leilao.setEstado(leilaoDTO.getEstado());
            leilao.setStatus(leilaoDTO.getStatus());
            return leilaoRepository.save(leilao).toDTO();
        }
        return null; // Ou lançar exceção
    }

    // Remoção de um leilão
    public void removerLeilao(int idLeilao) {
        leilaoRepository.deleteById(idLeilao);
    }
}
