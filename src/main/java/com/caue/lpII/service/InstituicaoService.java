package com.caue.lpII.service;

import com.caue.lpII.entity.dto.InstituicaoDTO;
import com.caue.lpII.entity.Instituicao;
import com.caue.lpII.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    // Registrar uma nova instituição financeira
    public InstituicaoDTO registrarInstituicao(InstituicaoDTO instituicaoDTO) {
        Instituicao instituicao = new Instituicao(instituicaoDTO.getNome(), instituicaoDTO.getCnpj());
        return instituicaoRepository.save(instituicao).toDTO();
    }

    // Consulta de todas as instituições financeiras
    public List<InstituicaoDTO> listarInstituicoes() {
        return instituicaoRepository.findAll().stream().map(Instituicao::toDTO).toList();
    }

    // Atualização de uma instituição financeira existente
    public InstituicaoDTO atualizarInstituicao(int idInstituicao, InstituicaoDTO instituicaoDTO) {
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(idInstituicao);
        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            instituicao.setNome(instituicaoDTO.getNome());
            instituicao.setCnpj(instituicaoDTO.getCnpj());
            return instituicaoRepository.save(instituicao).toDTO();
        }
        return null; // Ou lançar exceção
    }

    // Remoção de uma instituição financeira
    public void removerInstituicao(int idInstituicao) {
        instituicaoRepository.deleteById(idInstituicao);
    }
}

