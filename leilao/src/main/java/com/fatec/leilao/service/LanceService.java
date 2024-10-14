package com.fatec.leilao.service;

import com.fatec.leilao.dto.LanceDTO;
import com.fatec.leilao.entity.Cliente;
import com.fatec.leilao.entity.Lance;
import com.fatec.leilao.entity.Lote;
import com.fatec.leilao.repository.ClienteRepository;
import com.fatec.leilao.repository.LanceRepository;
import com.fatec.leilao.repository.LoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Lance lance = new Lance(lanceDTO.getValor(), clienteOpt.get(), loteOpt.get());
            return modelMapper.map(lanceRepository.save(lance), LanceDTO.class);
        }
        return null;
    }

    public List<LanceDTO> consultarLancesPorLote(int idLote) {
        return lanceRepository.findByLoteId(idLote).stream().map((element) -> modelMapper.map(element, LanceDTO.class)).collect(Collectors.toList());
    }
}

