package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.LoteDTO;
import com.caue.lpII.service.LoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping
    @Operation(summary = "Registrar um novo lote",
            description = "Adiciona um novo lote ao sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote registrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao registrar o lote.")
    })
    public ResponseEntity<LoteDTO> registrarLote(@RequestBody LoteDTO loteDTO) {
        LoteDTO novoLote = loteService.registrarLote(loteDTO);
        return ResponseEntity.ok(novoLote);
    }

    @GetMapping
    @Operation(summary = "Listar todos os lotes",
            description = "Retorna uma lista de todos os lotes registrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de lotes obtida com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao obter a lista de lotes.")
    })
    public ResponseEntity<List<LoteDTO>> listarLotes() {
        List<LoteDTO> lotes = loteService.listarLotes();
        return ResponseEntity.ok(lotes);
    }

    @PutMapping("/{idLote}")
    @Operation(summary = "Atualizar um lote existente",
            description = "Atualiza as informações de um lote específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao atualizar o lote.")
    })
    public ResponseEntity<LoteDTO> atualizarLote(@PathVariable int idLote, @RequestBody LoteDTO loteDTO) {
        LoteDTO loteAtualizado = loteService.atualizarLote(idLote, loteDTO);
        return ResponseEntity.ok(loteAtualizado);
    }

    @DeleteMapping("/{idLote}")
    @Operation(summary = "Remover um lote",
            description = "Remove um lote do sistema com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lote removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Lote não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao remover o lote.")
    })
    public ResponseEntity<Void> removerLote(@PathVariable int idLote) {
        loteService.removerLote(idLote);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{lanceMin}/{lanceMax}/{idLeilao}")
    @Operation(summary = "Listar lotes por faixa de lance inicial",
            description = "Retorna os lotes dentro de um intervalo de valores de lance inicial para um leilão específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotes listados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum lote encontrado no intervalo especificado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao listar os lotes.")
    })
    public ResponseEntity<List<LoteDTO>> listarLotesPorLanceInicial(
            @PathVariable double lanceMin,
            @PathVariable double lanceMax,
            @PathVariable int idLeilao) {
        List<LoteDTO> lotes = loteService.listarLotesEntreLances(lanceMin, lanceMax, idLeilao);
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/busca/{palavraBusca}")
    @Operation(summary = "Listar lotes por palavra-chave",
            description = "Retorna os lotes que correspondem a uma palavra-chave fornecida.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotes encontrados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum lote encontrado para a palavra-chave especificada."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao buscar lotes.")
    })
    public ResponseEntity<List<LoteDTO>> listarLotesPorPalavra(@PathVariable String palavraBusca) {
        return ResponseEntity.ok(loteService.getByWord(palavraBusca).get());
    }

}

