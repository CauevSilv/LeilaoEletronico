package com.fatec.leilao.controller;

import com.fatec.leilao.dto.InstituicaoDTO;
import com.fatec.leilao.service.InstituicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instituicoes")
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @PostMapping
    public ResponseEntity<InstituicaoDTO> registrarInstituicao(@RequestBody InstituicaoDTO instituicaoDTO) {
        InstituicaoDTO novaInstituicao = instituicaoService.registrarInstituicao(instituicaoDTO);
        return ResponseEntity.ok(novaInstituicao);
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoDTO>> listarInstituicoes() {
        List<InstituicaoDTO> instituicoes = instituicaoService.listarInstituicoes();
        return ResponseEntity.ok(instituicoes);
    }

    @PutMapping("/{idInstituicao}")
    public ResponseEntity<InstituicaoDTO> atualizarInstituicao(@PathVariable int idInstituicao, @RequestBody InstituicaoDTO instituicaoDTO) {
        InstituicaoDTO instituicaoAtualizada = instituicaoService.atualizarInstituicao(idInstituicao, instituicaoDTO);
        return ResponseEntity.ok(instituicaoAtualizada);
    }

    @DeleteMapping("/{idInstituicao}")
    public ResponseEntity<Void> removerInstituicao(@PathVariable int idInstituicao) {
        instituicaoService.removerInstituicao(idInstituicao);
        return ResponseEntity.noContent().build();
    }
}

