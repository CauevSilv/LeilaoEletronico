package com.fatec.leilao.service;

import com.fatec.leilao.dto.InstituicaoDTO;
import com.fatec.leilao.entity.Instituicao;
import com.fatec.leilao.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    public InstituicaoDTO registrarInstituicao(InstituicaoDTO instituicaoDTO) {
        Instituicao instituicao = new Instituicao(instituicaoDTO.getNome(), instituicaoDTO.getCnpj());
        return instituicaoRepository.save(instituicao).toDTO();
    }

    public List<InstituicaoDTO> listarInstituicoes() {
        return instituicaoRepository.findAll().stream().map(Instituicao::toDTO).toList();
    }

    public InstituicaoDTO atualizarInstituicao(int idInstituicao, InstituicaoDTO instituicaoDTO) {
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(idInstituicao);
        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            instituicao.setNome(instituicaoDTO.getNome());
            instituicao.setCnpj(instituicaoDTO.getCnpj());
            return instituicaoRepository.save(instituicao).toDTO();
        }
        return null;
    }

    public void removerInstituicao(int idInstituicao) {
        instituicaoRepository.deleteById(idInstituicao);
    }
}

