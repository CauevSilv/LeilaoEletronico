package com.caue.lpII.service;

import com.caue.lpII.entity.dto.LoteDTO;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.Lote;
import com.caue.lpII.repository.LeilaoRepository;
import com.caue.lpII.repository.LoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final LeilaoRepository leilaoRepository;
    private final ModelMapper modelMapper;

    public LoteService(LoteRepository loteRepository, LeilaoRepository leilaoRepository,
                       ModelMapper modelMapper) {
        this.loteRepository = loteRepository;
        this.leilaoRepository = leilaoRepository;
        this.modelMapper = modelMapper;
    }

    public LoteDTO registrarLote(LoteDTO loteDTO) {
        if(loteDTO.getLeilao().getIdLeilao().isPresent()){
            Optional<Leilao> leilaoOpt = leilaoRepository.findById(loteDTO.getLeilao().getIdLeilao().get());
            if (leilaoOpt.isPresent()) {
                Lote lote = new Lote(loteDTO.getTipo(), loteDTO.getNome(), loteDTO.getDescricao(), loteDTO.getLanceInicial(), leilaoOpt.get());
                return modelMapper.map(loteRepository.save(lote), LoteDTO.class);
            }
            return null;
        }
        return null;
    }

    public List<LoteDTO> listarLotes() {
        List<LoteDTO> auuu = loteRepository.findAll().stream().map((element) -> modelMapper.map(element, LoteDTO.class)).toList();
        return loteRepository.findAll().stream().map((element) -> modelMapper.map(element, LoteDTO.class)).toList();
    }

    public LoteDTO atualizarLote(int idLote, LoteDTO loteDTO) {
        Optional<Lote> loteOpt = loteRepository.findById(idLote);
        if (loteOpt.isPresent()) {
            Lote lote = loteOpt.get();
            lote.setTipo(loteDTO.getTipo());
            lote.setNome(loteDTO.getNome());
            lote.setDescricao(loteDTO.getDescricao());
            lote.setLanceInicial(loteDTO.getLanceInicial());
            return modelMapper.map(loteRepository.save(lote), LoteDTO.class);
        }
        return null;
    }

    public void removerLote(int idLote) {
        loteRepository.deleteById(idLote);
    }
}

