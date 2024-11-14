package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.LeilaoDTO;
import com.caue.lpII.service.LeilaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Registrar um novo leilão",
            description = "Adiciona um novo leilão ao sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instituição registrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar registrar a instituição.")
    })
    public ResponseEntity<LeilaoDTO> registrarLeilao(@RequestBody LeilaoDTO leilaoDTO) {
        LeilaoDTO novoLeilao = leilaoService.registrarLeilao(leilaoDTO);
        return ResponseEntity.status(201).body(novoLeilao);
    }

    @GetMapping
    @Operation(summary = "Listar todos os leilões",
            description = "Retorna uma lista de todos os leilões registrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de leilões obtida com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao obter a lista de leilões.")
    })
    public ResponseEntity<List<LeilaoDTO>> listarLeiloes() {
        List<LeilaoDTO> leiloes = leilaoService.listarLeiloes();
        return ResponseEntity.ok(leiloes);
    }

    @PutMapping("/{idLeilao}")
    @Operation(summary = "Atualizar um leilão existente",
            description = "Atualiza as informações de um leilão específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leilão atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Leilão não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao atualizar o leilão.")
    })
    public ResponseEntity<LeilaoDTO> atualizarLeilao(@PathVariable int idLeilao, @RequestBody LeilaoDTO leilaoDTO) {
        LeilaoDTO leilaoAtualizado = leilaoService.atualizarLeilao(idLeilao, leilaoDTO);
        return ResponseEntity.ok(leilaoAtualizado);
    }

    @DeleteMapping("/{idLeilao}")
    @Operation(summary = "Remover um leilão",
            description = "Remove um leilão do sistema com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Leilão removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Leilão não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao remover o leilão.")
    })
    public ResponseEntity<Void> removerLeilao(@PathVariable int idLeilao) {
        leilaoService.removerLeilao(idLeilao);
        return ResponseEntity.noContent().build();
    }
}

