package com.caue.lpII.service;
import com.caue.lpII.entity.dto.DispositivoDTO;
import com.caue.lpII.entity.dto.LeilaoDTO;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.dto.LeilaoDetalhadoDto;
import com.caue.lpII.repository.LeilaoRepository;
import com.caue.lpII.repository.LoteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;
    private final LoteRepository loteRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public LeilaoDTO registrarLeilao(LeilaoDTO leilaoDTO) {
        Leilao leilao = modelMapper.map(leilaoDTO, Leilao.class);
        return leilaoRepository.save(leilao).toDTO();
    }

    public List<LeilaoDTO> listarLeiloes() {
        return leilaoRepository.findAllByOrderByDataOcorrenciaAsc().stream().map(Leilao::toDTO).toList();
    }

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
        return null;
    }

    public LeilaoDetalhadoDto getLeilaoDetalhado(Integer idLeilao){
        LeilaoDetalhadoDto leilaoDetalhadoDto = new LeilaoDetalhadoDto();
        Leilao leilaoBase = leilaoRepository.findById(idLeilao).get();
//        List<DispositivoDTO> dispositivosFromLeilao = loteRepository.

        leilaoDetalhadoDto.setId(leilaoBase.getIdLeilao());
        leilaoDetalhadoDto.setDataOcorrencia(leilaoBase.getDataOcorrencia());
        leilaoDetalhadoDto.setDataVisita(leilaoBase.getDataVisitacao());
        leilaoDetalhadoDto.setEndereco(leilaoBase.getEndereco());
        leilaoDetalhadoDto.setCidade(leilaoBase.getCidade());
        leilaoDetalhadoDto.setEstado(leilaoBase.getEstado());

        return leilaoDetalhadoDto;
    }

    public void removerLeilao(int idLeilao) {
        leilaoRepository.deleteById(idLeilao);
    }
}
