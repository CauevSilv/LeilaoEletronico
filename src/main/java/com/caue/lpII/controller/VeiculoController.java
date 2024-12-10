package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.VeiculoDTO;
import com.caue.lpII.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @Operation(summary = "Cria um novo veículo e o associa a um leilão")
    @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Leilão não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/{leilaoId}")
    public ResponseEntity<List<VeiculoDTO>> criarVeiculos(@RequestBody List<VeiculoDTO> veiculosDTO, @PathVariable Long leilaoId) {
        List<VeiculoDTO> criados = veiculoService.criarVeiculos(veiculosDTO, leilaoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(criados);
    }

    @Operation(summary = "Busca um veículo por ID")
    @ApiResponse(responseCode = "200", description = "Veículo encontrado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarVeiculo(@PathVariable Long id) {
        return veiculoService.buscarVeiculo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um veículo existente")
    @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO) {
        return veiculoService.atualizarVeiculo(id, veiculoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(summary = "Remove um veículo por ID")
    @ApiResponse(responseCode = "204", description = "Veículo removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerVeiculo(@PathVariable Long id) {
        if (veiculoService.removerVeiculo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
