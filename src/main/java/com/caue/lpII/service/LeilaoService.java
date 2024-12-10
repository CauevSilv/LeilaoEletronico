package com.caue.lpII.service;
import com.caue.lpII.entity.Lance;
import com.caue.lpII.entity.Lote;
import com.caue.lpII.entity.dto.*;
import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.enums.LeilaoStatusTypes;
import com.caue.lpII.repository.InstituicaoRepository;
import com.caue.lpII.repository.LanceRepository;
import com.caue.lpII.repository.LeilaoRepository;
import com.caue.lpII.repository.LoteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;
    private final LoteRepository loteRepository;
    private final InstituicaoRepository InstituicaoRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final LanceRepository lanceRepository;
    private final InstituicaoService instituicaoService;
    private final InstituicaoRepository instituicaoRepository;

    public LeilaoDTO registrarLeilao(LeilaoDTO leilaoDTO) {
        Leilao leilao = modelMapper.map(leilaoDTO, Leilao.class);
        return modelMapper.map(leilaoRepository.save(leilao), LeilaoDTO.class);
    }

    public List<LeilaoDTO> listarLeiloes() {
        return leilaoRepository.findAllByOrderByDataOcorrenciaAsc().stream().map((element) -> modelMapper.map(element, LeilaoDTO.class)).collect(Collectors.toList());
    }
    public LeilaoDTO getLeilaoById(Integer idLeilao){
        return leilaoRepository.findById(idLeilao).map((element) -> modelMapper.map(element, LeilaoDTO.class)).get();
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
            leilao.setStatus(String.valueOf(leilaoDTO.getStatus()));
            return modelMapper.map(leilaoRepository.save(leilao), LeilaoDTO.class);
        }
        return null;
    }

    public Object getLeilaoDetalhado(Integer idLeilao){
        LeilaoDetalhadoDto leilaoDetalhadoDto = new LeilaoDetalhadoDto();
        Leilao leilaoBase = leilaoRepository.findById(idLeilao).orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado."));

        ZoneId brasiliaZoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime nowBrasilia = ZonedDateTime.now(brasiliaZoneId);
        ZonedDateTime dataOcorrenciaBrasilia = leilaoBase.getDataOcorrencia().atZone(brasiliaZoneId);
        ZonedDateTime dataVisitaBrasilia = leilaoBase.getDataVisitacao().atZone(brasiliaZoneId);

        if (nowBrasilia.isBefore(dataOcorrenciaBrasilia)) {
            leilaoBase.setStatus(String.valueOf(LeilaoStatusTypes.EM_ABERTO));
        } else if (nowBrasilia.isAfter(dataOcorrenciaBrasilia) && nowBrasilia.isBefore(dataVisitaBrasilia)) {
            leilaoBase.setStatus(String.valueOf(LeilaoStatusTypes.EM_ANDAMENTO));
        } else {
            leilaoBase.setStatus(String.valueOf(LeilaoStatusTypes.FINALIZADO));
            List<WinnersLance> lancesVencedores = new ArrayList<>();

            List<Lote> lotes = loteRepository.findAllByLeilaoId(idLeilao);
            for (Lote lote : lotes) {
                Lance lanceVencedor = lanceRepository.findLanceByValorGreater(lote.getId());
                if (lanceVencedor != null) {
                    WinnersLance lanceGanhadorDTO = new WinnersLance();
                    lanceGanhadorDTO.setLanceDto(modelMapper.map(lanceVencedor, LanceDTO.class));
                    lanceGanhadorDTO.setDescricao(lote.getDescricao());
                    lanceGanhadorDTO.setValor(lanceVencedor.getValor().doubleValue());
                    lanceGanhadorDTO.setClienteName(lanceVencedor.getIdCliente().getNome());
                    lancesVencedores.add(lanceGanhadorDTO);
                }
            }
            return new LeilaoEnded(leilaoBase.getId().longValue(),LeilaoStatusTypes.FINALIZADO,lancesVencedores);
        }


        List<LoteDTO> lotesFromLeilao = loteRepository.findAllByLeilaoId(leilaoBase.getId()).stream().map((element) -> modelMapper.map(element, LoteDTO.class)).collect(Collectors.toList());
        leilaoDetalhadoDto.setProdutos(lotesFromLeilao);
        leilaoDetalhadoDto.setInstituicoesFinanceiras(InstituicaoRepository.findAllByLeilaoId(idLeilao).stream().map((element) -> modelMapper.map(element, InstituicaoDTO.class)).collect(Collectors.toList()));
        leilaoDetalhadoDto.setId(leilaoBase.getId());
        leilaoDetalhadoDto.setDataOcorrencia(leilaoBase.getDataOcorrencia());
        leilaoDetalhadoDto.setDataVisita(leilaoBase.getDataVisitacao());
        leilaoDetalhadoDto.setEndereco(leilaoBase.getEndereco());
        leilaoDetalhadoDto.setCidade(leilaoBase.getCidade());
        leilaoDetalhadoDto.setEstado(leilaoBase.getEstado());
        leilaoDetalhadoDto.setStatus(LeilaoStatusTypes.valueOf(leilaoBase.getStatus()));

        return leilaoDetalhadoDto;
    }

    public Optional<LoteDTO> detalharItemLeilao(Integer idLeilao, Integer idLote){
        Optional<LoteDTO> loteDto = Optional.ofNullable(modelMapper.map(loteRepository.findByLeilaoIdAndLoteId(idLeilao, idLote), LoteDTO.class));
        if(loteDto.isPresent()){
            return loteDto;
        }
        return Optional.empty();
    }

    public LeilaoDETDTO montarLeilaoDet(Long idLeilao){
        Leilao leilaoBase = leilaoRepository.findById(idLeilao.intValue()).orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado."));
        LeilaoDETDTO leilaoDetalhadoDto = new LeilaoDETDTO();
        leilaoDetalhadoDto.setStatus(String.valueOf(leilaoBase.getId()));
        leilaoDetalhadoDto.setEndereco(leilaoBase.getEndereco());
        leilaoDetalhadoDto.setCidade(leilaoBase.getCidade());
        leilaoDetalhadoDto.setEstado(leilaoBase.getEstado());
        leilaoDetalhadoDto.setStatus(leilaoBase.getStatus());
        leilaoDetalhadoDto.setLotes(loteRepository.findAllByLeilaoId(leilaoBase.getId()).stream().map((element) -> modelMapper.map(element, LoteDTO.class)).collect(Collectors.toList()));
        leilaoDetalhadoDto.setInstituicoesFinanceiras(instituicaoRepository.findAllByLeilaoId(leilaoBase.getId()).stream().map((element) -> modelMapper.map(element, InstituicaoDTO.class)).collect(Collectors.toList()));
        leilaoDetalhadoDto.setLancesHistorico(lanceRepository.findAllByLeilaoId(leilaoBase.getId()).stream().map((element) -> modelMapper.map(element, LanceDTO.class)).collect(Collectors.toList()));
        return leilaoDetalhadoDto;
    }

    public void removerLeilao(int idLeilao) {
        leilaoRepository.deleteById(idLeilao);
    }
}
