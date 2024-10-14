package com.fatec.leilao.controller;

import com.fatec.leilao.dto.LanceDTO;
import com.fatec.leilao.service.LanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lances")
public class LanceController {

    private final LanceService lanceService;

    public LanceController(LanceService lanceService) {
        this.lanceService = lanceService;
    }

    @PostMapping
    public ResponseEntity<LanceDTO> registrarLance(@RequestBody LanceDTO lanceDTO) {
        LanceDTO novoLance = lanceService.registrarLance(lanceDTO);
        return ResponseEntity.ok(novoLance);
    }

    @GetMapping("/lote/{idLote}")
    public ResponseEntity<List<LanceDTO>> consultarLancesPorLote(@PathVariable int idLote) {
        List<LanceDTO> lances = lanceService.consultarLancesPorLote(idLote);
        return ResponseEntity.ok(lances);
    }
}

