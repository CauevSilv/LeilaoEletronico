package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.LoteDTO;
import com.caue.lpII.service.LoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    // Registrar um novo lote
    @PostMapping
    public ResponseEntity<LoteDTO> registrarLote(@RequestBody LoteDTO loteDTO) {
        LoteDTO novoLote = loteService.registrarLote(loteDTO);
        return ResponseEntity.ok(novoLote);
    }

    // Listar todos os lotes
    @GetMapping
    public ResponseEntity<List<LoteDTO>> listarLotes() {
        List<LoteDTO> lotes = loteService.listarLotes();
        return ResponseEntity.ok(lotes);
    }

    // Atualizar um lote existente
    @PutMapping("/{idLote}")
    public ResponseEntity<LoteDTO> atualizarLote(@PathVariable int idLote, @RequestBody LoteDTO loteDTO) {
        LoteDTO loteAtualizado = loteService.atualizarLote(idLote, loteDTO);
        return ResponseEntity.ok(loteAtualizado);
    }

    // Remover um lote
    @DeleteMapping("/{idLote}")
    public ResponseEntity<Void> removerLote(@PathVariable int idLote) {
        loteService.removerLote(idLote);
        return ResponseEntity.noContent().build();
    }
}

