package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.LanceDTO;
import com.caue.lpII.entity.dto.LanceHistoricoDTO;
import com.caue.lpII.service.LanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/{valor}/{clienteId}/{loteId}")
    @Operation(summary = "Registrar um novo lance", description = "Adiciona um novo lance ao sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "lance registrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar registrar a instituição.")
    })
    public HttpStatus registrarLance(@PathVariable double valor, @PathVariable int clienteId, @PathVariable int loteId) {
        lanceService.registrarLance(valor, clienteId, (long) loteId);
        return HttpStatus.CREATED;
    }
    @GetMapping("/lote/{idLote}")
    @Operation(summary = "Consultar lances por lote",
            description = "Retorna a lista de todos os lances associados a um lote específico, com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lances obtidos com sucesso."),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao consultar os lances.")
    })
    public ResponseEntity<List<LanceDTO>> consultarLancesPorLote(@PathVariable int idLote) {
        List<LanceDTO> lances = lanceService.consultarLancesPorLote(idLote);
        return ResponseEntity.ok(lances);
    }

    @GetMapping("/historico/{idLote}")
    @Operation(summary = "Consultar histórico de lances por lote",
            description = "Retorna o histórico detalhado de todos os lances realizados para um lote específico, com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico de lances obtido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao consultar o histórico de lances.")
    })
    public ResponseEntity<List<LanceHistoricoDTO>> consultarHistorico(@PathVariable int idLote) {
        return ResponseEntity.ok(lanceService.consultarLanceHistorico(idLote));
    }

}

