package com.caue.lpII.service;

import com.caue.lpII.entity.Dispositivo;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.dto.DispositivoDTO;
import com.caue.lpII.repository.DispositivoRepository;
import com.caue.lpII.repository.LeilaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DispositivoService{

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private LeilaoRepository leilaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<DispositivoDTO> criarDispositivos(List<DispositivoDTO> dispositivosDTO, Long leilaoId) {
        Leilao leilao = leilaoRepository.findById(leilaoId.intValue()).get();

        List<Dispositivo> dispositivos = dispositivosDTO.stream().map((element) -> modelMapper.map(element, Dispositivo.class)).collect(Collectors.toList());

        dispositivos = dispositivoRepository.saveAll(dispositivos);
        return dispositivos.stream().map((element) -> modelMapper.map(element, DispositivoDTO.class)).collect(Collectors.toList());
    }

    public Optional<DispositivoDTO> buscarDispositivo(Long id) {
        return Optional.ofNullable(dispositivoRepository.findById(id).map((element) -> modelMapper.map(element, DispositivoDTO.class)).orElse(null));
    }

    public Optional<DispositivoDTO> atualizarDispositivo(Long id, DispositivoDTO dispositivoDTO) {
        return dispositivoRepository.findById(id).map(dispositivoExistente -> {
            dispositivoDTO.setId(id);
            dispositivoRepository.updateDescricaoAndValorInicialAndVendidoAndLeilaoIdAndNomeAndTipoByIdAllIgnoreCase(dispositivoDTO.getDescricao(), dispositivoDTO.getValorInicial(), dispositivoDTO.isVendido(), dispositivoDTO.getLoteId().getId(), dispositivoDTO.getNome(), String.valueOf(dispositivoDTO.getTipo()), dispositivoDTO.getId());
            return modelMapper.map(dispositivoExistente, DispositivoDTO.class);
        });
    }

    public boolean removerDispositivo(Long id) {
        Optional<Dispositivo> dispositivoOpt = dispositivoRepository.findById(id);
        if (dispositivoOpt.isPresent()) {
            dispositivoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
