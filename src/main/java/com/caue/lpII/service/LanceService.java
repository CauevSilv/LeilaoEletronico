package com.caue.lpII.service;

import com.caue.lpII.entity.dto.*;
import com.caue.lpII.entity.Lance;
import com.caue.lpII.repository.ClienteRepository;
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
public class LanceService {

    private final LanceRepository lanceRepository;
    private final ClienteRepository clienteRepository;
    private final LoteRepository loteRepository;
    private final ModelMapper modelMapper;
    private final LeilaoRepository leilaoRepository;
    private final LoteService loteService;
    private final LeilaoService leilaoService;

    public LanceService(LanceRepository lanceRepository, ClienteRepository clienteRepository, LoteRepository loteRepository,
                        ModelMapper modelMapper, LeilaoRepository leilaoRepository, LoteService loteService, LeilaoService leilaoService) {
        this.lanceRepository = lanceRepository;
        this.clienteRepository = clienteRepository;
        this.loteRepository = loteRepository;
        this.modelMapper = modelMapper;
        this.leilaoRepository = leilaoRepository;
        this.loteService = loteService;
        this.leilaoService = leilaoService;
    }

    public void registrarLance(double valor, int clienteId, Long loteId) {
        Optional<ClienteDTO> clienteOpt = clienteRepository.findById(clienteId).map((element) -> modelMapper.map(element, ClienteDTO.class));
        Optional<LoteDTO> loteOpt = Optional.ofNullable(loteService.getLoteById(loteId));
        loteOpt.get().setLeilaoDTO(leilaoRepository.findById(loteRepository.findLeilaoByLoteId(loteId)).map((element) -> modelMapper.map(element, LeilaoDTO.class)).get());
        if (clienteOpt.isPresent() && loteOpt.isPresent()) {
            LanceDTO lanceDTO = new LanceDTO(valor, modelMapper.map(clienteRepository.findById(clienteId).get(), ClienteDTO.class), modelMapper.map(loteRepository.findById(Math.toIntExact(loteId)).get(), LoteDTO.class));
            Lance lance = modelMapper.map(lanceDTO, Lance.class);
            lanceRepository.save(lance);
        }
    }

    public List<LanceDTO> consultarLancesPorLote(int idLote) {
        return lanceRepository.findByLoteId(idLote).stream().map((element) -> modelMapper.map(element, LanceDTO.class)).toList();
    }

    public LanceHistoricoDTO criarLanceHistorico(Lance lance) {
        LanceHistoricoDTO lanceHistoricoDTO = new LanceHistoricoDTO();
        lanceHistoricoDTO.setClienteid(modelMapper.map(lance.getIdCliente(), ClienteDTO.class));
        lanceHistoricoDTO.setLote(modelMapper.map(lance.getIdLote(), LoteDTO.class));
        lanceHistoricoDTO.setValor(lance.getValor().doubleValue());
    return lanceHistoricoDTO;
    }
    public List<LanceHistoricoDTO> consultarLanceHistorico(int idLote) {
        List<LanceHistoricoDTO>  lanceHistoricoDTOList = new ArrayList<>();
        LanceHistoricoDTO lanceHistDto  = new LanceHistoricoDTO();
        lanceRepository.findByLoteId(idLote).forEach(lance -> lanceHistoricoDTOList.add(criarLanceHistorico(lance)));
        return lanceHistoricoDTOList;
    }
    public Optional<List<LanceDTO>> getByType(Long idLeilao,String tipoBusca){
        if (tipoBusca.contains("ulo") || tipoBusca.contains("Ve")){
            List<Lance> lances = lanceRepository.findByTipoContainingIgnoreCaseVeiculo(idLeilao);
            List<LanceDTO> lanceDTO = lances.stream().map((element) -> modelMapper.map(element, LanceDTO.class)).collect(Collectors.toList());
            return Optional.of(lanceDTO);
        } else if (tipoBusca.contains("Dispositivo") || tipoBusca.contains("dispositivo")){
            return Optional.of(lanceRepository.findByTipoContainingIgnoreCaseDispositivo(idLeilao).stream().map((element) -> modelMapper.map(element, LanceDTO.class)).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

}

