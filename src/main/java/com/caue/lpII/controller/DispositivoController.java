package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.DispositivoDTO;
import com.caue.lpII.service.DispositivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @Operation(summary = "Cria um novo dispositivo e associa a um leilão")
    @ApiResponse(responseCode = "201", description = "Dispositivo criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Leilão não encontrado ou dados inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/{leilaoId}")
    public ResponseEntity<List<DispositivoDTO>> criarDispositivos(@RequestBody List<DispositivoDTO> dispositivosDTO, @PathVariable Long leilaoId) {
        List<DispositivoDTO> criados = dispositivoService.criarDispositivos(dispositivosDTO, leilaoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(criados);
    }

    @Operation(summary = "Busca um dispositivo por ID")
    @ApiResponse(responseCode = "200", description = "Dispositivo encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DispositivoDTO.class)))
    @ApiResponse(responseCode = "404", description = "Dispositivo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> buscarDispositivo(@PathVariable Long id) {
        return dispositivoService.buscarDispositivo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @Operation(summary = "Atualiza um dispositivo existente")
    @ApiResponse(responseCode = "200", description = "Dispositivo atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DispositivoDTO.class)))
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Dispositivo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping("/{id}")
    public ResponseEntity<DispositivoDTO> atualizarDispositivo(@PathVariable Long id, @RequestBody DispositivoDTO dispositivoDTO) {
        return dispositivoService.atualizarDispositivo(id, dispositivoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @Operation(summary = "Remove um dispositivo por ID")
    @ApiResponse(responseCode = "204", description = "Dispositivo removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Dispositivo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDispositivo(@PathVariable Long id) {
        if (dispositivoService.removerDispositivo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(404).build();
    }
}
