package com.caue.lpII.service;

import com.caue.lpII.entity.dto.LanceDTO;
import com.caue.lpII.entity.Cliente;
import com.caue.lpII.entity.Lance;
import com.caue.lpII.entity.Lote;
import com.caue.lpII.repository.ClienteRepository;
import com.caue.lpII.repository.LanceRepository;
import com.caue.lpII.repository.LoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanceService {

    private final LanceRepository lanceRepository;
    private final ClienteRepository clienteRepository;
    private final LoteRepository loteRepository;
    private final ModelMapper modelMapper;

    public LanceService(LanceRepository lanceRepository, ClienteRepository clienteRepository, LoteRepository loteRepository,
                        ModelMapper modelMapper) {
        this.lanceRepository = lanceRepository;
        this.clienteRepository = clienteRepository;
        this.loteRepository = loteRepository;
        this.modelMapper = modelMapper;
    }

    public LanceDTO registrarLance(LanceDTO lanceDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findByCpf(lanceDTO.getCliente().getCpf());
        Optional<Lote> loteOpt = loteRepository.findById(lanceDTO.getLote().getId());
        if (clienteOpt.isPresent() && loteOpt.isPresent()) {
            Lance lance = modelMapper.map(lanceDTO, Lance.class);
            return modelMapper.map(lanceRepository.save(lance), LanceDTO.class);
        }
        return null;
    }

    public List<LanceDTO> consultarLancesPorLote(int idLote) {
        return lanceRepository.findByLoteId(idLote).stream().map((element) -> modelMapper.map(element, LanceDTO.class)).toList();
    }
}

