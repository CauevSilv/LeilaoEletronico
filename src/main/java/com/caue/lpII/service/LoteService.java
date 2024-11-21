package com.caue.lpII.service;

import com.caue.lpII.entity.dto.LanceDTO;
import com.caue.lpII.entity.dto.LoteDTO;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.Lote;
import com.caue.lpII.repository.LanceRepository;
import com.caue.lpII.repository.LeilaoRepository;
import com.caue.lpII.repository.LoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final LeilaoRepository leilaoRepository;
    private final ModelMapper modelMapper;
    private final LeilaoService leilaoService;
    private final LanceRepository lanceRepository;

    public LoteService(LoteRepository loteRepository, LeilaoRepository leilaoRepository,
                       ModelMapper modelMapper, LeilaoService leilaoService, LanceRepository lanceRepository) {
        this.loteRepository = loteRepository;
        this.leilaoRepository = leilaoRepository;
        this.modelMapper = modelMapper;
        this.leilaoService = leilaoService;
        this.lanceRepository = lanceRepository;
    }

    public LoteDTO registrarLote(LoteDTO loteDTO) {
        if(loteDTO.getLeilao().getIdLeilao() != 0){
            Optional<Leilao> leilaoOpt = leilaoRepository.findById(loteDTO.getLeilao().getIdLeilao());
            if (leilaoOpt.isPresent()) {
                Lote lote = new Lote(loteDTO.getTipo(), loteDTO.getNome(), loteDTO.getDescricao(), loteDTO.getLanceInicial(), leilaoOpt.get());
                return modelMapper.map(loteRepository.save(lote), LoteDTO.class);
            }
            return null;
        }
        return null;
    }

    public List<LoteDTO> listarLotes() {
        return loteRepository.findAll().stream().map((element) -> modelMapper.map(element, LoteDTO.class)).toList();
    }

    public List<LoteDTO> listarLotesEntreLances(double min, double max, int idLeilao) {
        List<LoteDTO> loteDTOList = loteRepository.findByLanceInicialBetween(min,max,idLeilao).stream().map((element) -> modelMapper.map(element, LoteDTO.class)).toList();
        loteDTOList.stream().forEach((element) -> element.setLeilao(leilaoRepository.findById(idLeilao).get()));
        return loteDTOList;
    }

    public List<LoteDTO> listarLotesEntreLancesTotais(double min, double max, int idLeilao) {
        List<Lote> lotes = loteRepository.findLotesByLancesTotaisBetween(min, max, idLeilao);
        return lotes.stream()
                .map(lote -> modelMapper.map(lote, LoteDTO.class))
                .toList();
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

    public Optional<List<LoteDTO>> getByWord(String palavraBusca){
        return Optional.of(loteRepository.findByNomeContainingIgnoreCase(palavraBusca).stream().map((element) -> modelMapper.map(element, LoteDTO.class)).collect(Collectors.toList()));
    }

    public void removerLote(int idLote) {
        loteRepository.deleteById(idLote);
    }
}

