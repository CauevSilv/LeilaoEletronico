package com.caue.lpII.controller;

import com.caue.lpII.entity.dto.InstituicaoDTO;
import com.caue.lpII.service.InstituicaoService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Registrar uma nova instituição", description = "Adiciona uma nova instituição ao sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "instituição registrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar registrar a instituição.")
    })
    public ResponseEntity<InstituicaoDTO> registrarInstituicao(@RequestBody InstituicaoDTO instituicaoDTO) {
        InstituicaoDTO novaInstituicao = instituicaoService.registrarInstituicao(instituicaoDTO);
        return ResponseEntity.ok(novaInstituicao);
    }
    @GetMapping
    @Operation(summary = "Listar todas as instituiçãos", description = "Retorna uma lista com todas as instituiçãos registradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instituição encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "instituição com o ID fornecido não foi encontrada."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar buscar a instituição.")
    })

    public ResponseEntity<List<InstituicaoDTO>> listarInstituicoes() {
        List<InstituicaoDTO> instituicoes = instituicaoService.listarInstituicoes();
        return ResponseEntity.ok(instituicoes);
    }

    @PutMapping("/{idInstituicao}")
    @Operation(summary = "Atualizar instituição", description = "Atualiza as informações de uma instituição existente com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instituição atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "instituição com o ID fornecido não foi encontrada."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar atualizar a instituição.")
    })

    public ResponseEntity<InstituicaoDTO> atualizarInstituicao(@PathVariable int idInstituicao, @RequestBody InstituicaoDTO instituicaoDTO) {
        InstituicaoDTO instituicaoAtualizada = instituicaoService.atualizarInstituicao(idInstituicao, instituicaoDTO);
        return ResponseEntity.ok(instituicaoAtualizada);
    }

    @DeleteMapping("/{idInstituicao}")
    @Operation(summary = "Deletar instituição", description = "Remove uma instituição do sistema com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instituição deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "instituição com o ID fornecido não foi encontrada."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar deletar a instituição.")
    })
    public ResponseEntity<Void> removerInstituicao(@PathVariable int idInstituicao) {
        instituicaoService.removerInstituicao(idInstituicao);
        return ResponseEntity.noContent().build();
    }
}

