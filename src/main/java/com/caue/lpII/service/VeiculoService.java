package com.caue.lpII.service;

import com.caue.lpII.entity.Dispositivo;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.Veiculo;
import com.caue.lpII.entity.dto.DispositivoDTO;
import com.caue.lpII.entity.dto.VeiculoDTO;
import com.caue.lpII.repository.LeilaoRepository;
import com.caue.lpII.repository.VeiculoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private LeilaoRepository leilaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<VeiculoDTO> criarVeiculos(List<VeiculoDTO> veiculosDTO, Long leilaoId) {
        Leilao leilao = leilaoRepository.findById(leilaoId.intValue()).get();

        List<Veiculo> veiculos = veiculosDTO.stream().map((element) -> modelMapper.map(element, Veiculo.class)).collect(Collectors.toList());

        veiculos = veiculoRepository.saveAll(veiculos);
        return veiculos.stream().map((element) -> modelMapper.map(element, VeiculoDTO.class)).collect(Collectors.toList());
    }


    public Optional<VeiculoDTO> buscarVeiculo(Long id) {
        return veiculoRepository.findById(id).map((element) -> modelMapper.map(element, VeiculoDTO.class));
    }

    public Optional<VeiculoDTO> atualizarVeiculo(Long id, VeiculoDTO veiculoDTO) {
        return veiculoRepository.findById(id).map(veiculoExistente -> {
            veiculoDTO.setId(id);
            veiculoRepository.updateById(veiculoDTO.getDescricao(),veiculoDTO.getValorInicial(),veiculoDTO.isVendido(),veiculoDTO.getLoteId().getId().longValue(),veiculoDTO.getModelo(),veiculoDTO.getMarca(),veiculoDTO.getTipo().toString(),veiculoDTO.getId());
            return veiculoDTO;
        });
    }

    public boolean removerVeiculo(Long id) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findById(id);
        if (veiculoOpt.isPresent()) {
            veiculoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
