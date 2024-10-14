package com.fatec.leilao.controller;

import com.fatec.leilao.dto.LeilaoDTO;
import com.fatec.leilao.service.LeilaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leiloes")
public class LeilaoController {

    private final LeilaoService leilaoService;

    public LeilaoController(LeilaoService leilaoService) {
        this.leilaoService = leilaoService;
    }

    @PostMapping
    public ResponseEntity<LeilaoDTO> registrarLeilao(@RequestBody LeilaoDTO leilaoDTO) {
        LeilaoDTO novoLeilao = leilaoService.registrarLeilao(leilaoDTO);
        return ResponseEntity.ok(novoLeilao);
    }

    @GetMapping
    public ResponseEntity<List<LeilaoDTO>> listarLeiloes() {
        List<LeilaoDTO> leiloes = leilaoService.listarLeiloes();
        return ResponseEntity.ok(leiloes);
    }

    @PutMapping("/{idLeilao}")
    public ResponseEntity<LeilaoDTO> atualizarLeilao(@PathVariable int idLeilao, @RequestBody LeilaoDTO leilaoDTO) {
        LeilaoDTO leilaoAtualizado = leilaoService.atualizarLeilao(idLeilao, leilaoDTO);
        return ResponseEntity.ok(leilaoAtualizado);
    }

    @DeleteMapping("/{idLeilao}")
    public ResponseEntity<Void> removerLeilao(@PathVariable int idLeilao) {
        leilaoService.removerLeilao(idLeilao);
        return ResponseEntity.noContent().build();
    }
}

